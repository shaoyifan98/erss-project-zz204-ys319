package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.dao.UserTrackingDao;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class UPSServer {
    private ServerSocket serverSocket;
    private AmazonController amazonController;
    private WorldController worldController;
    private TrackingShipDao trackingShipDao;
    private UserTrackingDao userTrackingDao;
    private UserDao userDao;

    @Autowired
    UPSServer(AmazonController amazonController, WorldController worldController, TrackingShipDao trackingShipDao,
              UserDao userDao, UserTrackingDao userTrackingDao) throws IOException {
        serverSocket = new ServerSocket(12350, 100);
        this.amazonController = amazonController;
        this.worldController = worldController;
        this.trackingShipDao = trackingShipDao;
        this.userDao = userDao;
        this.userTrackingDao = userTrackingDao;
        System.out.println("Start running server ... ");
        listen();
    }

    void handle(Socket socket) throws IOException {
        System.out.println("Start handling request from " + socket.getInetAddress());

        CodedInputStream input = CodedInputStream.newInstance(socket.getInputStream());
        int size = input.readUInt32();
        int limit = input.pushLimit(size);
        UACommands uaCommands = UACommands.parseFrom(input);
        handlePickUp(uaCommands.getPickList(), socket);
        handlerLoaded(uaCommands.getLoadList(), socket);
    }

    void handlePickUp(List<AmazonPick> pickUps, Socket socket) throws IOException {
        for (AmazonPick pick : pickUps) {
            ShipInfo shipInfo = new ShipInfo();
            shipInfo.setShipID(pick.getShipid());
            shipInfo.setStatus(ShipStatus.CREATED.getText());
            shipInfo.setWhID(pick.getWhnum());
            shipInfo.setDestX(pick.getX());
            shipInfo.setDestY(pick.getY());

            trackingShipDao.insertNewTracking(shipInfo); // update db
            // TODO associate with user_account
            associateWithAccount(pick, shipInfo);
            // TODO save to product table
            storeProductInfo(pick);


            sendAck(socket, pick.getSeq()); // send back to amazon
            worldController.allocateAvailableTrucks(shipInfo); // get to the world
        }
    }

    void associateWithAccount(AmazonPick pick, ShipInfo shipInfo) {
        String account = pick.getUpsAccount();
        if (!account.isEmpty()) {
            List<User> result = userDao.getUserByName(account);
            if (!result.isEmpty()) {
                User user = result.get(0);
                userTrackingDao.insertTracking(user.getId(), shipInfo.getTrackingID());
                return;
            }
            System.out.println("User not found: " + account);
        }
    }

    void storeProductInfo(AmazonPick pick) {

    }

    void handlerLoaded(List<AmazonLoaded> loadeds, Socket socket) throws IOException {
        for (AmazonLoaded loaded : loadeds) {
            ShipInfo shipInfo = trackingShipDao.getShipInfoByShipID(loaded.getShipid()).get(0);
            sendAck(socket, loaded.getSeq()); // send back to amazon

            worldController.goDeliver(shipInfo);
        }
    }

    void sendAck(Socket socket, long ack) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(socket.getOutputStream());
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
            while (true) {
                Socket client = null;
                try {
                    client = serverSocket.accept();
                    handle(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        System.out.println("Spring Container is destroyed!");
        serverSocket.close();
    }
}
