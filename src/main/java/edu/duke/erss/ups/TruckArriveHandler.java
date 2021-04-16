package edu.duke.erss.ups;

import java.util.ArrayList;
import java.util.TimerTask;

public class TruckArriveHandler extends AmazonCommandHandler{

    int truckID;
    int whID;
    ArrayList<Long> shipIDs;

    TruckArriveHandler(AmazonController amazonController, int truckID, int whID, ArrayList<Long> shipIDs) {
        super(amazonController);
        this.truckID = truckID;
        this.whID = whID;
        this.shipIDs = shipIDs;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                //TODO : change checking
                amazonController.sendTruckArrive(-1, truckID, whID, shipIDs);
            }
        };
        timer.schedule(resend, TIME_OUT);
    }
}
