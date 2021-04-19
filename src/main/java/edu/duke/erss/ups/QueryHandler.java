package edu.duke.erss.ups;

import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

public class QueryHandler extends WorldCommandHandler {
    ShipInfo info;

    QueryHandler(long seq, int truckID, WorldController worldController) {
        super(seq, truckID, worldController);
    }

    QueryHandler(long seq, int truckID, WorldController worldController,ShipInfo shipInfo) {
        this(seq, truckID, worldController);
        this.info = shipInfo;
    }

    @Override
    public void setTimerAndTask() {
    }

    @Override
    public void onReceive(UResponses uResponses, int index) throws RuntimeException {
    }
}
