package edu.duke.erss.ups;

import java.io.IOException;
import java.net.ServerSocket;

public class UPSServer {

    private ServerSocket serverSocket;

    UPSServer(int port) throws IOException {
        serverSocket = new ServerSocket(port, 100);
    }
}
