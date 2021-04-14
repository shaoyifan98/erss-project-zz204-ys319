package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.proto.UPStoWorld.UFinished;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;
import edu.duke.erss.ups.proto.UPStoWorld.UTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.TimerTask;

@Component
public class PickUpHandler extends WorldCommandHandler {

    Integer whID;

    @Autowired
    TrackingShipDao trackingShipDao;

    PickUpHandler() {

    }

    PickUpHandler(long seq, int truckID, int whID, WorldController worldController) {
        super(seq, truckID, worldController);
        this.whID = whID;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    worldController.allocateAvailableTrucks(seq, whID);
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

                //TODO database operation : truck arrive, waiting for package


                //TODO inform amazon to load
            }
            catch (IOException e) {
                System.out.println("sending ack of response of pick IO: " + e.getMessage());
            }

        }).start();
    }
}
