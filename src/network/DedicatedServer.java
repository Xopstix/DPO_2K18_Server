package network;

import model.ProjectManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Servidor dedicat per a cada client que es connecta
 * Created by Marc on 15/3/18.
 */
public class DedicatedServer extends Thread{

    private Socket sClient;
    private ObjectInputStream ois;
    //private DataInputStream dis;
    private ArrayList<DedicatedServer> clients;

    public DedicatedServer(Socket sClient, ArrayList<DedicatedServer> clients) {
        this.sClient = sClient;
        this.clients = clients;

        try {
            /*dis = new DataInputStream(sClient.getInputStream());
            dos = new DataOutputStream(sClient.getOutputStream());*/

            ois = new ObjectInputStream(sClient.getInputStream());

        } catch (IOException e) {
            System.out.println("Socket problem!");
        }
    }

    /**
     * Llegeix un objecte Usuari infinitament i mostra el seu nom per consola
     */
    public synchronized void run(){

        ProjectManager projectManager;

        while (true) {

            try {
                projectManager = (ProjectManager) ois.readObject();
                System.out.println(projectManager.getUsuari().getNom());
                System.out.println(projectManager.getUsuari().getPassword());
                System.out.println(projectManager.getUsuari().getCorreu());
            } catch (IOException | ClassNotFoundException e) {
                clients.remove(this);
            } finally {
                /*try {
                    ois.close();
                } catch (IOException e) {}
                try {
                    sClient.close();
                } catch (IOException e) {}*/
            }
        }

    }
}
