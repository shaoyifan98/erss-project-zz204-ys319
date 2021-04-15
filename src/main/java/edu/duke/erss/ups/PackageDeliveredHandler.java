package edu.duke.erss.ups;

import edu.duke.erss.ups.entity.ShipInfo;

import java.util.TimerTask;

public class PackageDeliveredHandler extends AmazonCommandHandler{
    ShipInfo shipInfo;

    PackageDeliveredHandler(AmazonController amazonController, ShipInfo shipInfo) {
        super(amazonController);
        this.shipInfo = shipInfo;
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                amazonController.sendPackageDelivered(shipInfo);
            }
        };
        timer.schedule(resend, TIME_OUT);
    }
}
