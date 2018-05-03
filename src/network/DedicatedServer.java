package network;

import model.ProjectManager;
import model.Project;
import utility.ConectorDB;

import java.io.*;
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
    private DataInputStream dis;
    private ArrayList<DedicatedServer> clients;
    private ConectorDB conn;
    private  boolean status;
    private String mode;
    private Object newObject;

    public DedicatedServer(Socket sClient, ArrayList<DedicatedServer> clients, ConectorDB conn) {
        this.sClient = sClient;
        this.clients = clients;
        this.conn = conn;

        try {

            ois = new ObjectInputStream(sClient.getInputStream());
            oos = new ObjectOutputStream(sClient.getOutputStream());
            dos = new DataOutputStream(sClient.getOutputStream());
            dis = new DataInputStream(sClient.getInputStream());

        } catch (IOException e) {
            System.out.println("Socket problem!");
        }
    }

    /**
     * Llegeix un objecte Usuari infinitament i mostra el seu nom per consola
     */
    public synchronized void run(){

        ProjectManager projectManager = new ProjectManager() ;
        status = false;

        while (true) {

            try {

                newObject = ois.readObject();
                if(newObject instanceof ProjectManager) {
                    projectManager = (ProjectManager) newObject;
                }


                if (projectManager.getMode() == 1) {

                    System.out.println(projectManager.getUsuari().getNom());
                    System.out.println(projectManager.getUsuari().getPassword());
                    System.out.println(projectManager.getUsuari().getCorreu());

                    if (!projectManager.getUsuari().getNom().equals("entrar")) {
                        //BBDD
                        ResultSet prueba;
                        status = false;

                        //Login BBDD
                        //ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
                        conn.connect();
                        prueba = conn.selectQuery("SELECT * FROM Usuario");

                        try {
                            //Recorremos toda la tabla de usuarios de la BBDD.
                            while (prueba.next()) {
                                //Especificamente le ponenmos que campos queremos leer de la BBDD
                                if (prueba.getObject("username").equals(projectManager.getUsuari().getNom())) {
                                    dos.writeUTF("INVALID");
                                    status = true;
                                }
                            }
                            if (!status) {
                                conn.insertQuery("INSERT INTO `Usuario` (`username`, `contrasena`, `email`) VALUES ('" + projectManager.getUsuari().getNom() + "', '" + projectManager.getUsuari().getPassword() + "', '" + projectManager.getUsuari().getCorreu() + "')");
                                dos.writeUTF("REGISTERED");

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
                        status = false;

                        //Login BBDD
                        //ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
                        conn.connect();
                        prueba = conn.selectQuery("SELECT * FROM Usuario");

                        try {
                            //Recorremos toda la tabla de usuarios de la BBDD.
                            while (prueba.next()) {
                                //Especificamente le ponenmos que campos queremos leer de la BBDD
                                if (prueba.getObject("username").equals(projectManager.getUsuari().getCorreu()) || prueba.getObject("email").equals(projectManager.getUsuari().getCorreu())) {
                                    if (prueba.getObject("contrasena").equals(projectManager.getUsuari().getPassword())) {
                                        status = true;
                                    }
                                }
                            }
                            if (status) {
                                dos.writeUTF("Logged");
                            } else {
                                dos.writeUTF("Pass or Login incorrect");
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            System.out.println("Problema al recuperar les dades de la BBDD 2...");
                        }

                    }
                }

                if (projectManager.getMode() == 2){
                    System.out.println(projectManager.getProject().getName());
                }

            } catch(IOException | ClassNotFoundException e){
                clients.remove(this);
            }
        }

    }
}


