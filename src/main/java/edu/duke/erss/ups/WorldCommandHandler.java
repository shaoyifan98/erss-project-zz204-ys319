package edu.duke.erss.ups;

import edu.duke.erss.ups.proto.UPStoWorld.UCommands;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.util.Timer;
import java.util.TimerTask;

public abstract class WorldCommandHandler {

    protected final Long TIME_OUT = 1000 * 30L;

    WorldController worldController;

    Long seq;

    Integer truckID;

    Timer timer;

    TimerTask resend;

    WorldCommandHandler() {

    }

    WorldCommandHandler(long seq, int truckID, WorldController worldController) {
        this.seq = seq;
        this.truckID = truckID;
        this.worldController = worldController;
        timer = new Timer("Resend");
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public abstract void setTimerAndTask();

    public abstract void onReceive(UResponses uResponses, int index);
}
