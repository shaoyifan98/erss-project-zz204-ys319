package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.proto.UPStoWorld.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Component
public class WorldController{

    private final String HOST = "67.159.88.254";
    private final int PORT = 12345;
    private final Socket connection;
    private static long seq;

    public long worldID;

    HashMap<Long, WorldCommandHandler> seqHandlerMap;

    @Autowired
    AmazonController amazonController;

    ArrayList<Long> truckIDList;
    private static final int TRUCK_CNT = 100;
    private static final int TRUCK_X = 1;
    private static final int TRUCK_Y = 1;

    WorldController() throws IOException {
        System.out.println("Starting world controller...");
        this.connection = new Socket(HOST, PORT);
        this.truckIDList = new ArrayList<>();
        this.seqHandlerMap = new HashMap<>();
        seq = 0;
        initialize();
        startListener();
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
                continue;
            }
            System.out.println("[DEBUG] ack already handled");
        }

        //UFinish of a truck completion of all packages
        if (uResponses.getAcksCount() == 0) {
            UFinished uFinished = uResponses.getCompletions(0);
            System.out.println("Truck " + uFinished.getTruckid() + " status = " + uFinished.getStatus());
            sendAckCommand(uFinished.getSeqnum());
        }
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
                //send to amazon
                informAmazon();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void sendInitialize() throws IOException {
        //writing
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UConnect.Builder uConnectBuilder = UConnect.newBuilder();
        for (int i = 0; i < TRUCK_CNT; ++i) {
            UInitTruck.Builder uInitBuilder = UInitTruck.newBuilder();
            uInitBuilder.setId(i).setX(TRUCK_X).setY(TRUCK_Y);
            uConnectBuilder.addTrucks(uInitBuilder.build());
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

    private void informAmazon() throws IOException {
        amazonController.sendWorld(worldID);
    }

    public void queryWorld(int truckID) {
        queryWorld(truckID, false);
    }

    public void queryWorld(int truckID, boolean goPickUp) {
        queryWorld(truckID, goPickUp, -1);
    }

    public void queryWorld(int truckID, boolean goPickUp, int whID) {
        new Thread(() -> {
            try {
                //writing
                Long seqNum = seq++;
                sendQuery(seqNum, truckID, goPickUp, whID);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Send query commands
     * @param seqNum sequence number of the command
     * @param truckID truck id
     * @param goPickUp go pick up flag
     * @param whID warehouse id if go pick up
     * @throws IOException
     */
    public void sendQuery(long seqNum, int truckID, boolean goPickUp, int whID) throws IOException {
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
                queryHandler = new QueryHandler(seqNum, truckID, this, goPickUp, whID);
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
     * @param whID warehouse to pick up
     */
    public void allocateAvailableTrucks(int whID) {
        int randId = new Random().nextInt(TRUCK_CNT);
        queryWorld(randId, true, whID);
    }

    /**
     * Send pick up instructions
     * Note: call this only after making sure truck is available
     * @param truckID truck id
     * @param whID warehouse id
     * @throws IOException
     */
    void pickUp(int truckID, int whID) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UGoPickup.Builder uGoPickB = UGoPickup.newBuilder();
        long seqNum = seq++;
        uGoPickB.setSeqnum(seqNum).setTruckid(truckID).setWhid(whID);
        uCommandB.addPickups(uGoPickB.build());
        if (!seqHandlerMap.containsKey(seqNum)) {
            //putting in the map
            PickUpHandler pickUpHandler = new PickUpHandler(seqNum, truckID, whID, this);
            pickUpHandler.setTimerAndTask();
            seqHandlerMap.put(seqNum, pickUpHandler);
        }
        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
    }

    public void goDeliver(int truckID, ArrayList<UDeliveryLocation> locations) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UGoDeliver.Builder uGoDeliverB = UGoDeliver.newBuilder();
        long seqNum = seq++;
        uGoDeliverB.setSeqnum(seqNum).addAllPackages(locations);
        uCommandB.addDeliveries(uGoDeliverB.build());
        if (!seqHandlerMap.containsKey(seqNum)) {
            //putting in the map
            DeliveryHandler deliveryHandler = new DeliveryHandler(seqNum, truckID, this);
            deliveryHandler.addLocations(locations);
            deliveryHandler.setTimerAndTask();
            seqHandlerMap.put(seqNum, deliveryHandler);
        }
        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
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
