package network;

import utility.ConectorDB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Conjunt de servidors dedicats
 * Created by Marc on 15/3/18.
 */
public class Server extends Thread{

    private ServerSocket sServer;
    private ArrayList<DedicatedServer> dServers;
    private ConectorDB conn;

    public Server(ConectorDB conn){

        this.dServers = new ArrayList<>();
        this.conn = conn;
    }

    /**
     * Accepta els diferents sockets dels clients que entren i els emmagatzema en un Arraylist
     */
    public synchronized void run() {
        try {
            sServer = new ServerSocket(12345);
            while (true) {
                Socket sClient = sServer.accept();
                DedicatedServer sd = new DedicatedServer(sClient, dServers, conn);
                dServers.add(sd);
                sd.start();
            }
        } catch (IOException e) {
            System.out.println("Socket problem!");
        } finally {
            if (sServer != null && !sServer.isClosed()) {
                try {
                    sServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
