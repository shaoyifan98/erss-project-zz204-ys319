package edu.duke.erss.ups;

import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;
import edu.duke.erss.ups.proto.UPStoWorld.UTruck;

import java.io.IOException;
import java.util.TimerTask;

public class QueryHandler extends WorldCommandHandler {

    Boolean goPickUp;

    Integer whID;

    QueryHandler(long seq, int truckID, WorldController worldController, boolean goPickUp) {
        super(seq, truckID, worldController);
        this.goPickUp = goPickUp;
    }

    QueryHandler(long seq, int truckID, WorldController worldController, boolean goPickUp, int whID) {
        this(seq, truckID, worldController, goPickUp);
        this.whID = whID;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    worldController.sendQuery(seq, truckID, goPickUp, whID);
                }
                catch (IOException e) {
                    System.out.println("Timer task IO exception: " + e.getMessage());
                }
            }
        };
        timer.schedule(resend, TIME_OUT);
    }

    @Override
    public void onReceive(UResponses uResponses, int index) throws RuntimeException {
        cancelTimer();
        new Thread(() -> {
            UTruck uTruck = uResponses.getTruckstatus(index);

            // if the truck is busy
            if (uTruck.getStatus().equals(Truck.Status.TRAVELING.getText())) {
                worldController.allocateAvailableTrucks(whID);
                return;
            }

            // not busy
            System.out.println("Truck " + truckID + " status: " + uTruck.getStatus());
            if (goPickUp) {
                try {
                    worldController.sendAckCommand(uTruck.getSeqnum()); //send back ack of the seq of the query
                    worldController.pickUp(truckID, whID); // send truck pick up
                }
                catch (IOException e) {
                    System.out.println("Picking up IO exception ... " + e.getMessage());
                    return;
                }
            }

            // TODO update database order

        }).start();
    }
}
