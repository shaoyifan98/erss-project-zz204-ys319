package edu.duke.erss.ups;

import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.TimerTask;

public class DeliveryHandler extends WorldCommandHandler {
    ShipInfo shipInfo;

    DeliveryHandler(long seq, WorldController worldController, ShipInfo shipInfo) {
        super(seq, shipInfo.getTruckID(), worldController);
        this.shipInfo = shipInfo;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Resend go deliver seq=" + seq + ", tracking=" + shipInfo.getTrackingID());
                    worldController.goDeliver(seq, shipInfo);
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
