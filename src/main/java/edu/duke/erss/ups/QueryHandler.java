package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;
import edu.duke.erss.ups.proto.UPStoWorld.UTruck;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

public class QueryHandler extends WorldCommandHandler {

    Boolean goPickUp;

    Long pickSeq;

    ShipInfo info;

    TrackingShipDao trackingShipDao;

    QueryHandler(long seq, int truckID, WorldController worldController, boolean goPickUp, TrackingShipDao trackingShipDao) {
        super(seq, truckID, worldController);
        this.goPickUp = goPickUp;
        this.trackingShipDao = trackingShipDao;
    }

    QueryHandler(long seq, int truckID, WorldController worldController,
                 boolean goPickUp, long pickSeq, ShipInfo shipInfo, TrackingShipDao trackingShipDao) {
        this(seq, truckID, worldController, goPickUp, trackingShipDao);
        this.pickSeq = pickSeq;
        this.info = shipInfo;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    worldController.sendQuery(seq, truckID, goPickUp, pickSeq, info);
                }
                catch (IOException e) {
                    System.out.println("Timer task IO exception: " + e.getMessage());
                }
            }
        };
        timer.schedule(resend, TIME_OUT);
    }

    double calcDistance(double dx, double dy, double sx, double sy) {
        double deltaX = Math.abs(dx - sx);
        double deltaY = Math.abs(dy - sy);
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    void updateDistance(UTruck currTruck) {
        List<ShipInfo> shipInfos = trackingShipDao.getShipInforByTruckID(truckID);
        for (ShipInfo shipInfo : shipInfos) {
            int destX = shipInfo.getDestX();
            int destY = shipInfo.getDestY();
            double distance = calcDistance(destX, destY, currTruck.getX(), currTruck.getY());
            System.out.println("Update distance from Truck " + currTruck.getTruckid() + " of tracking "
                    + shipInfo.getTrackingID() + ": " + distance);
            trackingShipDao.updateDistance(shipInfo.getShipID(), distance);
        }
    }

    @Override
    public void onReceive(UResponses uResponses, int index) throws RuntimeException {
        cancelTimer();
        new Thread(() -> {
            UTruck uTruck = uResponses.getTruckstatus(index);
            updateDistance(uTruck);
            if (goPickUp) {
                // if the truck is busy
                if (uTruck.getStatus().equals(Truck.Status.TRAVELING.getText()) || uTruck.getStatus().equals(Truck.Status.LOADING)) {
                    worldController.allocateAvailableTrucks(info);
                    return;
                }
                // not busy : allocated and start tracking
                System.out.println("Truck " + truckID + " status: " + uTruck.getStatus());
                worldController.trackingRecords.put(info.getTrackingID(), truckID);
                try {
                    worldController.sendAckCommand(uTruck.getSeqnum()); //send back ack of the seq of the query
                    worldController.pickUp(truckID, info); // send truck pick up
                    info.setTruckID(truckID);
                    info.setStatus(ShipStatus.INROUTE.getText());
                    trackingShipDao.updateTracking(info);
                }
                catch (IOException e) {
                    System.out.println("Picking up IO exception ... " + e.getMessage());
                    return;
                }
            }

        }).start();
    }
}
