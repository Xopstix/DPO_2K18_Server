package network;

import model.ProjectManager;
import utility.ConectorDB;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servidor dedicat per a cada client que es connecta
 * Created by Marc on 15/3/18.
 */
public class DedicatedServer extends Thread{

    private Socket sClient;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private DataOutputStream dos;
    //private DataInputStream dis;
    private ArrayList<DedicatedServer> clients;
    private ConectorDB conn;

    public DedicatedServer(Socket sClient, ArrayList<DedicatedServer> clients, ConectorDB conn) {
        this.sClient = sClient;
        this.clients = clients;
        this.conn = conn;

        try {
            /*dis = new DataInputStream(sClient.getInputStream());
            dos = new DataOutputStream(sClient.getOutputStream());*/

            ois = new ObjectInputStream(sClient.getInputStream());
            oos = new ObjectOutputStream(sClient.getOutputStream());
            dos = new DataOutputStream(sClient.getOutputStream());

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

                if (!projectManager.getUsuari().getNom().equals("entrar")) {
                    //BBDD
                    ResultSet prueba;

                    //Login BBDD
                    //ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
                    conn.connect();
                    prueba = conn.selectQuery("SELECT * FROM usuarios");

                    try {
                        //Recorremos toda la tabla de usuarios de la BBDD.
                        while (prueba.next()) {
                            //Especificamente le ponenmos que campos queremos leer de la BBDD
                            if (prueba.getObject("Login").equals(projectManager.getUsuari().getNom())) {
                                dos.writeUTF("INVALID");
                            } else {
                                //registrar datos BBDD
                                dos.writeUTF("VALID");
                            }
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Problema al recuperar les dades de la BBDD 1...");
                    }
                    //Desconexion BBDD
                    //conn.disconnect();
                }
                if (projectManager.getUsuari().getNom().equals("entrar")) {
                    //BBDD
                    ResultSet prueba;

                    //Login BBDD
                    //ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
                    conn.connect();
                    prueba = conn.selectQuery("SELECT * FROM usuarios");

                    try {
                        //Recorremos toda la tabla de usuarios de la BBDD.
                        while (prueba.next()) {
                            //Especificamente le ponenmos que campos queremos leer de la BBDD
                            if (prueba.getObject("Login").equals(projectManager.getUsuari().getCorreu()) || prueba.getObject("Mail").equals(projectManager.getUsuari().getCorreu())) {
                                if(prueba.getObject("Contraseña").equals(projectManager.getUsuari().getPassword())){
                                    dos.writeUTF("OK");
                                }
                                else {
                                    dos.writeUTF("NONOK");
                                }
                            } else {
                                dos.writeUTF("NONOK");
                            }
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Problema al recuperar les dades de la BBDD 2...");
                    }
                    //Desconexion BBDD
                    //conn.disconnect();
                }

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


