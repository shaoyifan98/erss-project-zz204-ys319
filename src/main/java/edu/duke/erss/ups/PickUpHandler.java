package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.proto.UPStoWorld.UFinished;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class PickUpHandler extends WorldCommandHandler {

    ShipInfo shipInfo;

    TrackingShipDao trackingShipDao;

    PickUpHandler(long seq, int truckID, ShipInfo shipInfo, WorldController worldController, TrackingShipDao trackingShipDao) {
        super(seq, truckID, worldController);
        this.shipInfo = shipInfo;
        this.trackingShipDao = trackingShipDao;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    worldController.allocateAvailableTrucks(seq, shipInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(resend, TIME_OUT);
    }

    @Override
    public void onReceive(UResponses uResponses, int index) {
        cancelTimer();
        new Thread(() -> {
            try {
                UFinished uFinished = uResponses.getCompletions(index);
                System.out.println("Truck " + uFinished.getTruckid() + " status: " + uFinished.getStatus());
                worldController.sendAckCommand(uFinished.getSeqnum());

                //database operation : truck arrive, waiting for package
                shipInfo.setStatus(ShipStatus.WAITING.getText());
                trackingShipDao.updateTracking(shipInfo);

                //inform amazon to load
                ArrayList<Long> shipIDs = new ArrayList<>();
                shipIDs.add(shipInfo.getShipID());
                worldController.amazonController.sendTruckArrive(shipInfo.getTrackingID(), shipInfo.getTruckID(), shipInfo.getWhID(), shipIDs);
            }
            catch (IOException e) {
                System.out.println("sending ack of response of pick IO: " + e.getMessage());
            }

        }).start();
    }
}
