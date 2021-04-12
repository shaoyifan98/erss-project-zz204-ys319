package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.proto.UPStoAmazon.UACommands;
import edu.duke.erss.ups.proto.UPStoAmazon.UAResponses;
import edu.duke.erss.ups.proto.UPStoAmazon.UPSTruckArrive;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


public class AmazonController {
    private String host;
    private int port;
    private static AtomicLong seqNum;
    private final int TIME_OUT = 10 * 1000;
    private CommandHandler commandHandler;

    AmazonController() {
        this("127.0.0.1", 12348);
    }

    AmazonController(String host, int port) {
        this.host = host;
        this.port = port;
        seqNum = new AtomicLong(0);
        this.commandHandler = new CommandHandler();
    }

    public void sendTruckArrive(int truckID, int whID, ArrayList<Long> shipIDs) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(host, port);
                socket.setSoTimeout(TIME_OUT);

                //writing
                CodedOutputStream output = CodedOutputStream.newInstance(socket.getOutputStream());
                UAResponses.Builder uaResponse = UAResponses.newBuilder();
                UPSTruckArrive.Builder uaTruckArrive = UPSTruckArrive.newBuilder();
                long seq = seqNum.getAndAdd(1);
                uaTruckArrive.setSeq(seq).setTruckID(truckID).setWhnum(whID);
                uaTruckArrive.addAllShipid(shipIDs);
                uaResponse.addArrive(uaTruckArrive);
                UAResponses responses = uaResponse.build();
                byte[] data = responses.toByteArray();

                output.writeUInt32NoTag(data.length);
                responses.writeTo(output);

                //reading
                CodedInputStream input = CodedInputStream.newInstance(socket.getInputStream());
                int size = input.readUInt32();
                int limit = input.pushLimit(size);
                UACommands uaCommands = UACommands.parseFrom(input);
                input.popLimit(limit);
                //handling response
                commandHandler.handleUACommandResponse(uaCommands, seq);
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendPackageDelivered() {

    }
}
