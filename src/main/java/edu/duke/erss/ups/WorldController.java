package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.TruckDao;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.entity.User;
import edu.duke.erss.ups.proto.UPStoWorld.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
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
    UserDao userDao;
    AmazonController amazonController;

    HashMap<Long, Integer> trackingRecords;
    DistanceTracker distanceTracker;

    ArrayList<Long> truckIDList;
    private final int TRUCK_CNT = 10000;
    private final int TRUCK_X = 1;
    private final int TRUCK_Y = 1;
    private static int truck_alloc = 0;

    @Autowired
    public void setAmazonController(AmazonController amazonController) {
        this.amazonController = amazonController;
    }

    @Autowired
    WorldController(TrackingShipDao trackingShipDao, TruckDao truckDao, UserDao userDao) throws IOException {
        System.out.println("Starting world controller...");
        this.worldIDAmazonConnectBa = new CyclicBarrier(2);
        this.connection = new Socket(HOST, PORT);
        this.truckIDList = new ArrayList<>();
        this.seqHandlerMap = new HashMap<>();
        this.trackingRecords = new HashMap<>();
        this.trackingShipDao = trackingShipDao;
        this.truckDao = truckDao;
        this.userDao = userDao;
        seq = 0;
        this.distanceTracker = new DistanceTracker(trackingRecords, trackingShipDao, this);
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
                distanceTracker.start();
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

        System.out.println("Received a UResponse: len of acks=" + uResponses.getAcksCount() + " uf=" + uResponses.getCompletionsCount()
                + " truckStatus=" + uResponses.getTruckstatusCount() + " delieverd=" + uResponses.getDeliveredCount() + " err=" + uResponses.getErrorCount());

        handleUFinish(uResponses);
        handleQuery(uResponses);
        handleDelivered(uResponses);
        handleAcks(uResponses);
    }

    private void handleUFinish(UResponses uResponses) throws IOException {
        for (int i = 0; i < uResponses.getCompletionsCount(); ++i) {
            UFinished uFinished = uResponses.getCompletions(i);
            System.out.println("--- Truck " + uFinished.getTruckid() + " status: " + uFinished.getStatus());
            sendAckCommand(uFinished.getSeqnum());
            //database operation : truck arrive, waiting for package
            List<ShipInfo> shipInfos = trackingShipDao.getShipInforByTruckID(uFinished.getTruckid());
            int count = 0;
            for (ShipInfo shipInfo : shipInfos) {
                System.out.println("ship status : " + shipInfo.getStatus());
                if (shipInfo.getStatus().equals(ShipStatus.INROUTE.getText())) {
                    ++count;
                    shipInfo.setStatus(ShipStatus.WAITING.getText());
                    trackingShipDao.updateStatus(shipInfo.getShipID(), ShipStatus.WAITING.getText());
                    truckDao.updateTruckStatus(uFinished.getTruckid(), Truck.Status.ARR_WH.getText());
                    //inform amazon to load
                    ArrayList<Long> shipIDs = new ArrayList<>();
                    shipIDs.add(shipInfo.getShipID());
                    amazonController.sendTruckArrive(shipInfo.getTrackingID(), shipInfo.getTruckID(), shipInfo.getWhID(), shipIDs);
                }
            }
            if (count == 0) {
                truckDao.updateTruckStatus(uFinished.getTruckid(), Truck.Status.IDLE.getText());
            }
        }
    }

    private void handleQuery(UResponses uResponses) throws IOException {
        for (int i = 0; i < uResponses.getTruckstatusCount(); ++i) {
            UTruck uTruck = uResponses.getTruckstatus(i);
            sendAckCommand(uTruck.getSeqnum()); // acking
            // update distance
            updateDistance(uTruck);
        }
    }

    private void handleAcks(UResponses uResponses) {
        int len = uResponses.getAcksCount();
        for (int i = 0; i < len; ++i) {
            long ack = uResponses.getAcks(i);
            System.out.println("Received ack = " + ack + " at index = " + i);
            if (seqHandlerMap.containsKey(ack)) {
                WorldCommandHandler handler = seqHandlerMap.get(ack);
                handler.onReceive(uResponses, i);
                if (!handler.getClass().equals(DeliveryHandler.class)) {
                    System.out.println("@Sequence: resolving other " + ack);
                    seqHandlerMap.remove(ack);
                }
                continue; // skipping the loop
            }
            System.out.println("[DEBUG] ack already handled");
        }
    }

    private void handleDelivered(UResponses uResponses) throws IOException {
        for (int i = 0; i < uResponses.getDeliveredCount(); ++i) {
            UDeliveryMade uDeliveryMade = uResponses.getDelivered(i);
            System.out.println("--- Package " + uDeliveryMade.getPackageid() + " of truck " + uDeliveryMade.getTruckid() + " delivered");
            sendAckCommand(uDeliveryMade.getSeqnum()); // acking
            sendAckCommand(uDeliveryMade.getSeqnum());

            // database: Package delivered
            trackingShipDao.updateStatus(uDeliveryMade.getPackageid(), ShipStatus.DELIVERED.getText());
            ShipInfo shipInfo = trackingShipDao.getShipInfoByShipID(uDeliveryMade.getPackageid()).get(0);
            //stop tracking
            trackingRecords.remove(shipInfo.getTrackingID());
            // inform amazon
            amazonController.sendPackageDelivered(shipInfo);
            try {
                emailInform(shipInfo.getTrackingID());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void queryWorld(int truckID) throws IOException {
        sendQuery(seq++, truckID);
    }

    double calcDistance(double dx, double dy, double sx, double sy) {
        double deltaX = Math.abs(dx - sx);
        double deltaY = Math.abs(dy - sy);
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    void updateDistance(UTruck currTruck) {
        List<ShipInfo> shipInfos = trackingShipDao.getShipInforByTruckID(currTruck.getTruckid());
        for (ShipInfo shipInfo : shipInfos) {
            int destX = shipInfo.getDestX();
            int destY = shipInfo.getDestY();
            double distance = calcDistance(destX, destY, currTruck.getX(), currTruck.getY());
            System.out.println("Update distance from Truck " + currTruck.getTruckid() + " of tracking "
                    + shipInfo.getTrackingID() + ": " + distance);
            trackingShipDao.updateDistance(shipInfo.getShipID(), distance);
        }
    }

    /**
     * Send query commands
     * @param seqNum sequence number of the command
     * @param truckID truck id
     * @throws IOException
     */
    public void sendQuery(long seqNum, int truckID) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UQuery.Builder uQueryB = UQuery.newBuilder();
        uQueryB.setTruckid(truckID).setSeqnum(seqNum);
        uCommandB.addQueries(uQueryB.build());
        UCommands commands = uCommandB.build();

        //putting in the map
        QueryHandler queryHandler = new QueryHandler(seqNum, truckID, this);
        System.out.println("@Sequence: start listen to query " + seqNum);
        seqHandlerMap.put(seqNum, queryHandler);

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
    public int allocateAvailableTrucks(ShipInfo shipInfo) throws IOException {
        int truckID = truck_alloc++;
        if (truck_alloc >= TRUCK_CNT) {
            truck_alloc %= TRUCK_CNT;
        }
        shipInfo.setTruckID(truckID);
        return truckID;
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

        //putting in the map
        PickUpHandler pickUpHandler = new PickUpHandler(seqNum, truckID, shipInfo, this,
                trackingShipDao, truckDao);
        pickUpHandler.setTimerAndTask();
        System.out.println("@Sequence: start listen to pick up " + seqNum);
        seqHandlerMap.put(seqNum, pickUpHandler);

        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
        // update truck status
        truckDao.updateTruckStatus(truckID, Truck.Status.TRAVELING.getText());
        trackingShipDao.updateStatus(shipInfo.getShipID(), ShipStatus.INROUTE.getText());
    }

    public void goDeliver(Long oldSeq, ShipInfo shipInfo) throws IOException {
        CodedOutputStream output = CodedOutputStream.newInstance(connection.getOutputStream());
        UCommands.Builder uCommandB = UCommands.newBuilder();
        UGoDeliver.Builder uGoDeliverB = UGoDeliver.newBuilder();
        // Build the location
        ArrayList<UDeliveryLocation> locations = new ArrayList<>();
        UDeliveryLocation.Builder locationB = UDeliveryLocation.newBuilder();
        locationB.setX(shipInfo.getDestX()).setY(shipInfo.getDestY()).setPackageid(shipInfo.getShipID());
        locations.add(locationB.build());
        long seqNum = oldSeq == null ? seq++ : oldSeq;
        // Build command
        uGoDeliverB.setSeqnum(seqNum).addAllPackages(locations).setTruckid(shipInfo.getTruckID());
        uCommandB.addDeliveries(uGoDeliverB.build());

        //putting in the map
        DeliveryHandler deliveryHandler = new DeliveryHandler(seqNum,this, shipInfo);
        deliveryHandler.setTimerAndTask();
        System.out.println("@Sequence: start listen to deliver " + seqNum);
        seqHandlerMap.put(seqNum, deliveryHandler);

        UCommands commands = uCommandB.build();
        byte[] data = commands.toByteArray();
        output.writeUInt32NoTag(data.length);
        commands.writeTo(output);
        output.flush();
        // Update DB
        shipInfo.setStatus(ShipStatus.DELIVERING.getText());
        trackingShipDao.updateStatus(shipInfo.getShipID(), ShipStatus.DELIVERING.getText());
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

    private void emailInform(Long tracking) {
        List<User> users = this.userDao.getUserByTrackingID(tracking);
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            String from = "shaoyf98@gmail.com";
            String to = user.getEmail();
            String subject = "Your package has been delivered!";
            String msg = "Dear " + user.getName() + ", your shipment has been delivered!";
            sendEmail(from, to, subject, msg);
            return;
        }
        throw new IllegalArgumentException("User not exist! email not sent.");
    }

    public static void sendEmail(String from, String to, String subject, String msg) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "rqahmutmfvbzdpmt");
            }
        });
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);
            // send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
