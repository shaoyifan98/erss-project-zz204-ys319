package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.TruckDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.proto.UPStoWorld.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Component
public class WorldController {
    private final String HOST = "67.159.88.254";
    private final int PORT = 12345;
    private final Socket connection;
    private static long seq;

    public static long worldID = -1;
    public CyclicBarrier worldIDAmazonConnectBa;

    HashMap<Long, WorldCommandHandler> seqHandlerMap;
    TrackingShipDao trackingShipDao;
    TruckDao truckDao;
    AmazonController amazonController;

    ArrayList<Long> truckIDList;
    private final int TRUCK_CNT = 200;
    private final int TRUCK_X = 1;
    private final int TRUCK_Y = 1;
    private static int truck_alloc = 0;

    @Autowired
    public void setAmazonController(AmazonController amazonController) {
        this.amazonController = amazonController;
    }

    @Autowired
    WorldController(TrackingShipDao trackingShipDao, TruckDao truckDao) throws IOException {
        System.out.println("Starting world controller...");
        this.worldIDAmazonConnectBa = new CyclicBarrier(2);
        this.connection = new Socket(HOST, PORT);
        this.truckIDList = new ArrayList<>();
        this.seqHandlerMap = new HashMap<>();
        this.trackingShipDao = trackingShipDao;
        this.truckDao = truckDao;
        seq = 0;
        initialize();
    }

    /**
     * Send UInit & UConnect
     */
    void initialize() {
        new Thread(() -> {
            try {
                //sending to world
                sendInitialize();
                //reading from world
                readInitialize();
                worldIDAmazonConnectBa.await();
                //start listen world messages
                startListener();
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void sendInitialize() throws IOException {
        //writing
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UConnect.Builder uConnectBuilder = UConnect.newBuilder();
        truckDao.deleteAll();
        for (int i = 0; i < TRUCK_CNT; ++i) {
            UInitTruck.Builder uInitBuilder = UInitTruck.newBuilder();
            uInitBuilder.setId(i).setX(TRUCK_X).setY(TRUCK_Y);
            uConnectBuilder.addTrucks(uInitBuilder.build());
            Truck truck = new Truck();
            truck.setPosX(TRUCK_X);
            truck.setPosY(TRUCK_Y);
            truck.setStatus(Truck.Status.IDLE.getText());
            truck.setId(i);
            truckDao.insertTruck(truck);
        }
        uConnectBuilder.setIsAmazon(false);
        UConnect request = uConnectBuilder.build();
        byte[] data = request.toByteArray();
        output.writeUInt32NoTag(data.length);
        request.writeTo(output);
        output.flush();
        System.out.println("sent UConnect ... size=" + data.length);
    }

    void readInitialize() throws IOException {
        CodedInputStream input = CodedInputStream.newInstance(connection.getInputStream());
        int size = input.readUInt32();
        int limit = input.pushLimit(size);
        UConnected uConnected = UConnected.parseFrom(input);
        String result = uConnected.getResult();
        this.worldID = uConnected.getWorldid();
        if (!result.equals("connected!")) {
            System.err.println("World creating connection error:\n" + result);
            connection.close();
            System.exit(1);
        }
        System.out.println("UConnect: " + result + ", world id = " + worldID);
        input.popLimit(limit);
    }

    /**
     * Keep receiving responses
     */
    void startListener() {
        new Thread(() -> {
            while (true) {
                try {
                    readUResponses();
                }
                catch (IOException e) {
                }
            }
        }).start();
    }

    /**
     * Got a response / responses and dispatch to correspond handler with the seq number
     * @throws IOException
     */
    void readUResponses() throws IOException {
        CodedInputStream input = CodedInputStream.newInstance(connection.getInputStream());
        int size = input.readUInt32();
        int limit = input.pushLimit(size);
        UResponses uResponses = UResponses.parseFrom(input);
        input.popLimit(limit);
        int len = uResponses.getAcksCount();
        for (int i = 0; i < len; ++i) {
            long ack = uResponses.getAcks(i);
            if (seqHandlerMap.containsKey(ack)) {
                WorldCommandHandler handler = seqHandlerMap.get(ack);
                handler.onReceive(uResponses, i);
                if (!handler.getClass().equals(DeliveryHandler.class)) {
                    seqHandlerMap.remove(ack);
                }
                continue; // skipping the loop
            }
            System.out.println("[DEBUG] ack already handled");
            if (uResponses.getErrorCount() > 0) {
                sendAckCommand(uResponses.getError(i).getSeqnum());
            }
            else if (uResponses.getCompletionsCount() > 0) {
                sendAckCommand(uResponses.getCompletions(i).getSeqnum());
            }
            else if (uResponses.getDeliveredCount() > 0) {
                sendAckCommand(uResponses.getDelivered(i).getSeqnum());
            }
            else if (uResponses.getTruckstatusCount() > 0) {
                sendAckCommand(uResponses.getTruckstatus(i).getSeqnum());
            }
        }

        //UFinish of a truck completion of all packages
        if (uResponses.getAcksCount() == 0) {
            UFinished uFinished = uResponses.getCompletions(0);
            System.out.println("Truck " + uFinished.getTruckid() + " status = " + uFinished.getStatus());
            sendAckCommand(uFinished.getSeqnum());
        }
    }

    public void queryWorld(int truckID) {
        queryWorld(truckID, false);
    }

    public void queryWorld(int truckID, boolean goPickUp) {
        queryWorld(truckID, goPickUp, null);
    }

    public void queryWorld(int truckID, boolean goPickUp, ShipInfo shipInfo) {
        new Thread(() -> {
            try {
                sendQuery(seq++, truckID, goPickUp, -1, shipInfo);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void queryWorldWithSeq(long pickSeq, int truckID, boolean goPickUp, ShipInfo shipInfo) throws IOException {
        sendQuery(seq++, truckID, goPickUp, pickSeq, shipInfo);
    }

    /**
     * Send query commands
     * @param seqNum sequence number of the command
     * @param truckID truck id
     * @param goPickUp go pick up flag
     * @param shipInfo warehouse id if go pick up
     * @throws IOException
     */
    public void sendQuery(long seqNum, int truckID, boolean goPickUp, long pickSeq, ShipInfo shipInfo) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UQuery.Builder uQueryB = UQuery.newBuilder();

        uQueryB.setTruckid(truckID).setSeqnum(seqNum);
        uCommandB.addQueries(uQueryB.build());
        UCommands commands = uCommandB.build();

        if (!seqHandlerMap.containsKey(seqNum)) {
            //putting in the map
            QueryHandler queryHandler;
            if (goPickUp) {
                queryHandler = new QueryHandler(seqNum, truckID, this, goPickUp, pickSeq, shipInfo, trackingShipDao);
            }
            else {
                queryHandler = new QueryHandler(seqNum, truckID, this, goPickUp);
            }
            queryHandler.setTimerAndTask();
            seqHandlerMap.put(seqNum, queryHandler);
        }

        //sending
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
    }

    /**
     * Call this to send truck to pick up
     * @param shipInfo info about the shipment
     */
    public void allocateAvailableTrucks(ShipInfo shipInfo) {
        int truckID = truck_alloc++;
        if (truck_alloc >= TRUCK_CNT) {
            truck_alloc %= TRUCK_CNT;
        }
        queryWorld(truckID, true, shipInfo);
    }

    /**
     * Call this to send truck to pick up
     * @param seqNum
     * @param shipInfo
     * @throws IOException
     */
    public void allocateAvailableTrucks(long seqNum, ShipInfo shipInfo) throws IOException {
        int truckID = truck_alloc++;
        if (truck_alloc >= TRUCK_CNT) {
            truck_alloc %= TRUCK_CNT;
        }
        queryWorldWithSeq(seqNum, truckID, true, shipInfo);
    }

    /**
     * Send pick up instructions
     * Note: call this only after making sure truck is available
     * @param truckID truck id
     * @param shipInfo warehouse id
     * @throws IOException
     */
    public void pickUp(int truckID, ShipInfo shipInfo) throws IOException {
        long seqNum = seq++;
        pickUp(seqNum, truckID, shipInfo);
    }

    public void pickUp(long seqNum, int truckID, ShipInfo shipInfo) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UGoPickup.Builder uGoPickB = UGoPickup.newBuilder();
        uGoPickB.setSeqnum(seqNum).setTruckid(truckID).setWhid(shipInfo.getWhID());
        uCommandB.addPickups(uGoPickB.build());
        if (!seqHandlerMap.containsKey(seqNum)) {
            //putting in the map
            PickUpHandler pickUpHandler = new PickUpHandler(seqNum, truckID, shipInfo, this,
                    trackingShipDao, truckDao);
            pickUpHandler.setTimerAndTask();
            seqHandlerMap.put(seqNum, pickUpHandler);
        }
        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
        // update truck status
        truckDao.updateTruckStatus(truckID, Truck.Status.TRAVELING.getText());
    }

    public void goDeliver(ShipInfo shipInfo) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UGoDeliver.Builder uGoDeliverB = UGoDeliver.newBuilder();
        // Build the location
        ArrayList<UDeliveryLocation> locations = new ArrayList<>();
        UDeliveryLocation.Builder locationB = UDeliveryLocation.newBuilder();
        locationB.setX(shipInfo.getDestX()).setY(shipInfo.getDestY()).setPackageid(shipInfo.getShipID());
        locations.add(locationB.build());
        long seqNum = seq++;
        // Build command
        uGoDeliverB.setSeqnum(seqNum).addAllPackages(locations).setTruckid(shipInfo.getTruckID());
        uCommandB.addDeliveries(uGoDeliverB.build());
        if (!seqHandlerMap.containsKey(seqNum)) {
            //putting in the map
            DeliveryHandler deliveryHandler = new DeliveryHandler(seqNum,this, shipInfo, trackingShipDao);
            deliveryHandler.addLocations(locations);
            deliveryHandler.setTimerAndTask();
            seqHandlerMap.put(seqNum, deliveryHandler);
        }
        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
        // Update DB
        shipInfo.setStatus(ShipStatus.DELIVERING.getText());
        trackingShipDao.updateTracking(shipInfo);
        // update truck status
        truckDao.updateTruckStatus(shipInfo.getTruckID(), Truck.Status.DELIVERING.getText());
    }

    public void sendAckCommand(long ack) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        uCommandB.addAcks(ack);
        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
    }
}
