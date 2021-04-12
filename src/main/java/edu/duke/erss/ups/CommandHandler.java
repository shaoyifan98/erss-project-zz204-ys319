package edu.duke.erss.ups;

import edu.duke.erss.ups.proto.UPStoAmazon.UACommands;

import java.util.ArrayList;


public class CommandHandler {

    public CommandHandler() {

    }

    public void handleUACommandRequest(UACommands uaCommands) {

    }

    public void handleUACommandResponse(UACommands uaCommands, long seqNum) {
        if (uaCommands.getAcks(0) != seqNum) {
            throw new RuntimeException("Wrong seq number matched");
        }
    }
}
