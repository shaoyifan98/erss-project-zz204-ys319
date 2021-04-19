package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.dao.ProductDao;
import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.dao.UserTrackingDao;
import edu.duke.erss.ups.entity.Product;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.User;
import edu.duke.erss.ups.proto.UPStoAmazon.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

@Service
public class UPSServer {
    private final int PORT = 13579;
    private ServerSocket serverSocket;
    private AmazonController amazonController;
    private WorldController worldController;
    private TrackingShipDao trackingShipDao;
    private UserTrackingDao userTrackingDao;
    private UserDao userDao;
    private ProductDao productDao;
    private Socket amazonSocket;

    @Autowired
    UPSServer(AmazonController amazonController, WorldController worldController, TrackingShipDao trackingShipDao,
              UserDao userDao, UserTrackingDao userTrackingDao, ProductDao productDao) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.amazonController = amazonController;
        this.worldController = worldController;
        this.trackingShipDao = trackingShipDao;
        this.userDao = userDao;
        this.userTrackingDao = userTrackingDao;
        this.productDao = productDao;
        System.out.println("Start running server ... ");
        listen();
    }

    /**
     * Handling receiving messages happen in a separate thread
     * @param socket
     * @throws IOException
     */
    void handle(Socket socket) throws IOException {
        System.out.println("@Amazon: Start handling request from " + socket.getInetAddress());
        CodedInputStream input = CodedInputStream.newInstance(socket.getInputStream());
        try {
            worldController.worldIDAmazonConnectBa.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        amazonController.sendWorld(WorldController.worldID, socket);
        while (true) {
            int size = input.readUInt32();
            int limit = input.pushLimit(size);
            UACommands uaCommands = UACommands.parseFrom(input);
            input.popLimit(limit);
            handleAcks(uaCommands.getAcksList());
            handlePickUp(uaCommands.getPickList());
            handlerLoaded(uaCommands.getLoadList());
        }
    }

    void handleAcks(List<Long> acks) {
        new Thread(() -> {
            for (long ack : acks) {
                if (amazonController.seqHandlerMap.containsKey(ack)) {
                    System.out.println("@Amazon: Receive from amazon ack = " + ack);
                    AmazonCommandHandler commandHandler = amazonController.seqHandlerMap.get(ack);
                    commandHandler.onReceive();
                    amazonController.seqHandlerMap.remove(ack);
                }
            }
        }).start();
    }

    void handlePickUp(List<AmazonPick> pickUps) throws IOException {
        new Thread(() -> {
            try {
                for (AmazonPick pick : pickUps) {
                    System.out.println("@Amazon: Receive from amazon pick up = " + pick.getShipid());
                    ShipInfo shipInfo = new ShipInfo();
                    shipInfo.setShipID(pick.getShipid());
                    shipInfo.setStatus(ShipStatus.CREATED.getText());
                    shipInfo.setWhID(pick.getWhnum());
                    shipInfo.setDestX(pick.getX());
                    shipInfo.setDestY(pick.getY());

                    // save to product table
                    storeProductInfo(pick);
                    int truckID = worldController.allocateAvailableTrucks(shipInfo);
                    trackingShipDao.insertNewTracking(shipInfo); // update db & get tracking ID
                    associateWithAccount(pick, shipInfo);
                    sendAck(pick.getSeq()); // send back to amazon
                    worldController.trackingRecords.put(shipInfo.getTrackingID(), shipInfo.getTruckID());
                    worldController.pickUp(truckID, shipInfo);
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void associateWithAccount(AmazonPick pick, ShipInfo shipInfo) {
        String account = pick.getUpsAccount();
        if (!account.isEmpty()) {
            System.out.println("Account : " + account);
            List<User> result = userDao.getUserByName(account);
            if (result != null && !result.isEmpty()) {
                User user = result.get(0);
                userTrackingDao.insertTracking(user.getId(), shipInfo.getTrackingID());
                return;
            }
            System.out.println("@Amazon: User not found: " + account);
        }
    }

    void storeProductInfo(AmazonPick pick) {
        Product product = new Product();
        product.setShipID(pick.getShipid());
        //iterate product list
        for (UAProduct uaProduct : pick.getThingsList()) {
            product.setPid(uaProduct.getId());
            product.setName(uaProduct.getName());
            product.setCount(uaProduct.getCount());
            product.setDescription(uaProduct.getDescription());
            productDao.insertNewProduct(product);
        }
    }

    void handlerLoaded(List<AmazonLoaded> loadeds) throws IOException {
        new Thread(() -> {
            try {
                for (AmazonLoaded loaded : loadeds) {
                    System.out.println("@Amazon: Receive from amazon loaded = " + loaded.getShipid() + " truck " + loaded.getTruckID());
                    ShipInfo shipInfo = trackingShipDao.getShipInfoByShipID(loaded.getShipid()).get(0);
                    sendAck(loaded.getSeq()); // send back to amazon
                    worldController.goDeliver(null, shipInfo);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void sendAck(long ack) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(amazonSocket.getOutputStream());
        UAResponses.Builder uaResponse = UAResponses.newBuilder();
        uaResponse.addAcks(ack);
        UAResponses responses = uaResponse.build();
        byte[] data = responses.toByteArray();
        output.writeUInt32NoTag(data.length);
        responses.writeTo(output);
        output.flush();
    }

    void listen() throws IOException {
        new Thread(() -> {
            try {
                amazonSocket = serverSocket.accept();
                handle(amazonSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        System.out.println("Spring Container is destroyed!");
        serverSocket.close();
    }
}
