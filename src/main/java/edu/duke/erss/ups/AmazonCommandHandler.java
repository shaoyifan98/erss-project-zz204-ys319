package edu.duke.erss.ups;

import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.proto.UPStoAmazon.UAResponses;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AmazonCommandHandler {
    protected final Long TIME_OUT = 1000 * 10L;
    protected Timer timer;
    protected TimerTask resend;

    AmazonController amazonController;

    AmazonCommandHandler(AmazonController amazonController) {
        this.amazonController = amazonController;
        timer = new Timer("Resend");
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public abstract void setTimerAndTask();

    public void onReceive() {
        cancelTimer();
    }
}
