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
        Project proyecto = new Project();
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
                                    oos.writeObject(projectManager);
                                    dos.writeUTF("INVALID");
                                    status = true;
                                }
                            }
                            if (!status) {
                                conn.insertQuery("INSERT INTO `Usuario` (`username`, `contrasena`, `email`) VALUES ('" + projectManager.getUsuari().getNom() + "', '" + projectManager.getUsuari().getPassword() + "', '" + projectManager.getUsuari().getCorreu() + "')");
                                oos.writeObject(projectManager);
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
                                prueba = conn.selectQuery("SELECT * FROM Proyecto AS Po, Usuario AS Us WHERE Po.username = '" + projectManager.getUsuari().getCorreu() + "' AND Po.username = Us.username");
                                //projectManager.getProject().setName(prueba.getObject("nombre"));
                                while(prueba.next()){

                                    proyecto.setName(prueba.getString("nombre"));
                                    proyecto.setUsername(prueba.getString("username"));
                                    projectManager.getProjects().add(proyecto);

                                }
                                oos.writeObject(projectManager);
                                dos.writeUTF("Logged");
                                for (int i = 0; i < projectManager.getProjects().size(); i++){

                                    System.out.println(projectManager.getProjects().get(i).getName());
                                }
                            } else {
                                oos.writeObject(projectManager);
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
                    //BBDD
                    ResultSet prueba;
                    status = false;

                    //Login BBDD
                    //ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
                    conn.connect();
                    System.out.println(projectManager.getUsuari().getNom() + projectManager.getProject().getName() + " año: " + projectManager.getProject().getYear() + " mes: " + projectManager.getProject().getMonth() + " dia: " + projectManager.getProject().getDay());
                    try {

                        String query = "INSERT INTO Proyecto(username, nombre, year_proyecto, mes_proyecto, dia_proyecto) VALUES ('" + "manusahun" + "', '" + projectManager.getProject().getName() + "', '" + projectManager.getProject().getYear() + "', '" + projectManager.getProject().getMonth() + "', '" + projectManager.getProject().getDay() + "')";
                        System.out.println(query);
                        conn.insertQuery(query);
                        System.out.println("hola 1");
                        prueba = conn.selectQuery("SELECT id_proyecto FROM Proyecto ORDER BY id_proyecto DESC LIMIT 1");
                        prueba.next();
                        int id_projecte = prueba.getInt("id_proyecto");
                        for(int i = 0; i < projectManager.getProject().getMembres().size() ; i++) {
                            conn.insertQuery("INSERT INTO `UsuarioProyecto`(`username`,`id_proyecto`) VALUES ('" + projectManager.getProject().getMembres().get(i) + "', '" + id_projecte + "')");
                        }
                        oos.writeObject(projectManager);
                        dos.writeUTF("REGISTERED");

                    } catch (SQLException e) {
                        e.printStackTrace();
                        // TODO Auto-generated catch block
                        System.out.println("Problema al recuperar les dades de la BBDD 2...");
                    }

                }

            } catch(IOException | ClassNotFoundException e){
                clients.remove(this);
            }
        }

    }
}


