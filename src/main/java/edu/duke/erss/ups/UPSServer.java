package edu.duke.erss.ups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class UPSServer {
    private ServerSocket serverSocket;
    private AmazonController amazonController;
    private WorldController worldController;

    @Autowired
    UPSServer(AmazonController amazonController, WorldController worldController) throws IOException {
        serverSocket = new ServerSocket(12350, 100);
        this.amazonController = amazonController;
        this.worldController = worldController;
        System.out.println("Start running server ... ");
        listen();
    }

    void handle(Socket socket) {
        System.out.println("Start handling request from " + socket.getInetAddress());
        //TODO: ..
    }

    void listen() throws IOException {
        new Thread(() -> {
            while (true) {
                Socket client = null;
                try {
                    client = serverSocket.accept();
                    handle(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @PreDestroy
    public void onDestroy() throws IOException {
        System.out.println("Spring Container is destroyed!");
        serverSocket.close();
    }
}
