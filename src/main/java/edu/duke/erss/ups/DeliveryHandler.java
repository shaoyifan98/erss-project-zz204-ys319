package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.proto.UPStoWorld.UDeliveryLocation;
import edu.duke.erss.ups.proto.UPStoWorld.UDeliveryMade;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class DeliveryHandler extends WorldCommandHandler {

    ArrayList<UDeliveryLocation> locations;

    ShipInfo shipInfo;

    private TrackingShipDao trackingShipDao;

    DeliveryHandler(long seq, WorldController worldController, ShipInfo shipInfo, TrackingShipDao trackingShipDao) {
        super(seq, shipInfo.getTruckID(), worldController);
        this.locations = new ArrayList<>();
        this.shipInfo = shipInfo;
        this.trackingShipDao = trackingShipDao;
    }

    public void addLocations(ArrayList<UDeliveryLocation> locations) {
        this.locations.addAll(locations);
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    worldController.goDeliver(shipInfo);
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
                UDeliveryMade uDeliveryMade = uResponses.getDelivered(index);
                System.out.println("Package " + uDeliveryMade.getPackageid() + " of truck " + uDeliveryMade.getTruckid() + " delivered");
                UDeliveryLocation toDelete = null;
                for (UDeliveryLocation location : locations) {
                    if (location.getPackageid() == uDeliveryMade.getPackageid()) {
                        toDelete = location;
                        break;
                    }
                }
                locations.remove(toDelete);
                if (locations.isEmpty()) {
                    worldController.seqHandlerMap.remove(seq);
                }
                worldController.sendAckCommand(uDeliveryMade.getSeqnum());

                // TODO database Package delivered
                shipInfo.setStatus(ShipStatus.DELIVERED.getText());
                trackingShipDao.updateTracking(shipInfo);

                // TODO inform amazon
                worldController.amazonController.sendPackageDelivered(shipInfo);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }
}
