package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.TruckDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.TimerTask;

public class PickUpHandler extends WorldCommandHandler {
    ShipInfo shipInfo;
    TrackingShipDao trackingShipDao;
    TruckDao truckDao;

    PickUpHandler(long seq, int truckID, ShipInfo shipInfo, WorldController worldController,
                  TrackingShipDao trackingShipDao, TruckDao truckDao) {
        super(seq, truckID, worldController);
        this.shipInfo = shipInfo;
        this.trackingShipDao = trackingShipDao;
        this.truckDao = truckDao;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Resending pick up seq=" + seq + ", tracking=" + shipInfo.getTrackingID());
                    if (seq == -1) {
                        worldController.pickUp(truckID, shipInfo);
                    }
                    else {
                        worldController.pickUp(seq, truckID, shipInfo);
                    }

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
    }
}
