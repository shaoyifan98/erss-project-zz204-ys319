package edu.duke.erss.ups;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.proto.UPStoAmazon.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class AmazonController {
    private String host;
    private int port;
    private static AtomicLong seqNum;
    private final int TIME_OUT = 10 * 1000;

    @Autowired
    WorldController worldController;

    AmazonController() {
        this("127.0.0.1", 12348);
    }

    AmazonController(String host, int port) {
        this.host = host;
        this.port = port;
        seqNum = new AtomicLong(0);
    }

    /**
     * Send world id to amazon (UAConnect), no seq
     * @param worldID world id
     * @throws IOException
     */
    public void sendWorld(long worldID) throws IOException {
        Socket socket;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(TIME_OUT);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //writing
        CodedOutputStream output = CodedOutputStream.newInstance(socket.getOutputStream());
        UAConnect.Builder uaConnectBuilder = UAConnect.newBuilder();
        uaConnectBuilder.setWorldid(worldID);
        UAConnect uaConnect = uaConnectBuilder.build();
        byte[] data = uaConnect.toByteArray();
        output.writeUInt32NoTag(data.length);
        uaConnect.writeTo(output);
        output.flush();

        //no reading?
        socket.close();
    }

    /**
     * Sending truck arrive message (UPSTruckArrive)
     * @param truckID  truckID
     * @param whID warehouse id
     * @param shipIDs ship id / package id / order id
     */
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
                sendResponse(output, responses);

                handleAck(socket, seq);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendPackageDelivered(ShipInfo shipInfo) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(host, port);
                socket.setSoTimeout(TIME_OUT);
                //writing
                CodedOutputStream output = CodedOutputStream.newInstance(socket.getOutputStream());
                UAResponses.Builder uaResponseB = UAResponses.newBuilder();
                UPSDelivered.Builder uDeliverB = UPSDelivered.newBuilder();
                long seq = seqNum.getAndAdd(1);
                uDeliverB.setSeq(seq);
                uDeliverB.setShipid(shipInfo.getShipID());
                uaResponseB.addDeliver(uDeliverB.build());
                UAResponses responses = uaResponseB.build();
                sendResponse(output, responses);

                handleAck(socket, seq);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void sendResponse(CodedOutputStream output, UAResponses responses) throws IOException{
        byte[] data = responses.toByteArray();
        output.writeUInt32NoTag(data.length);
        responses.writeTo(output);
        output.flush();
    }

    void handleAck(Socket socket, long seq) throws IOException {
        //reading
        CodedInputStream input = CodedInputStream.newInstance(socket.getInputStream());
        int size = input.readUInt32();
        int limit = input.pushLimit(size);
        UACommands uaCommands = UACommands.parseFrom(input);
        long ack = uaCommands.getAcks(0);
        input.popLimit(limit);
        socket.close();
        if (ack != seq) {
            throw new RuntimeException("Receive mismatched seq and ack :(" + seq + "," + ack + ")");
        }
    }
}
