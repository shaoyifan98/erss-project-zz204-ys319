package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;

import java.io.IOException;
import java.util.HashMap;

public class DistanceTracker extends Thread {
    private HashMap<Long, Integer> trackingTruckMap;
    private WorldController worldController;

    DistanceTracker(HashMap<Long, Integer> trackingTruckMap, TrackingShipDao trackingShipDao, WorldController worldController) {
        this.trackingTruckMap = trackingTruckMap;
        this.worldController = worldController;
    }

    @Override
    public void run() {
        System.out.println("Running distance checker ... ");
        while (true) {
            try {
                Thread.sleep(1000);
                if (trackingTruckMap.isEmpty()) {
                    continue;
                }
                for (Long trackingNum : trackingTruckMap.keySet()) {
                    worldController.queryWorld(trackingTruckMap.get(trackingNum));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
