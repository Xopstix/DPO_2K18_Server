package network;

//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import controller.ServerController;
import model.Columna;
import model.ProjectManager;
import model.Project;
import model.Tasca;
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
    private boolean status;
    private String mode;
    private Object newObject;
    private ServerController serverController;

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
                                int j = 0;
                                while(prueba.next()){

                                    Project proyecto = new Project();
                                    proyecto.setName(prueba.getString("nombre"));
                                    proyecto.setUsername(prueba.getString("username"));
                                    proyecto.setDay(prueba.getInt("dia_proyecto"));
                                    proyecto.setMonth(prueba.getInt("mes_proyecto"));
                                    proyecto.setYear(prueba.getInt("year_proyecto"));
                                    proyecto.setIdProyecto(prueba.getInt("id_proyecto"));
                                    projectManager.getYourProjects().add(proyecto);
                                }
                                for (int i = 0; i < projectManager.getYourProjects().size(); i++) {
                                    prueba = conn.selectQuery("SELECT * FROM Columna AS Co, Proyecto AS Pr WHERE Pr.id_proyecto = Co.id_proyecto AND Pr.id_proyecto = "+ projectManager.getYourProjects().get(i).getIdProyecto()+"");
                                    //System.out.println("SELECT * FROM Columna AS Co, Proyecto AS Pr WHERE Pr.id_proyecto = Co.id_proyecto AND Pr.id_proyecto = "+ projectManager.getYourProjects().get(i).getIdProyecto()+"");
                                    while (prueba.next()) {
                                        Columna columna = new Columna();
                                        columna.setNom(prueba.getString("nombre"));
                                        columna.setOrdre(prueba.getInt("orden"));
                                        columna.setId_columna(prueba.getInt("id_columna"));
                                        projectManager.getYourProjects().get(i).getColumnes().add(columna);
                                    }
                                }
                                for (int i = 0; i < projectManager.getYourProjects().size(); i++){
                                    for (int k = 0; k < projectManager.getYourProjects().get(i).getColumnes().size(); k++){
                                        prueba = conn.selectQuery("SELECT * FROM Tarea AS Ta, Columna AS Co WHERE Co.id_columna = Ta.id_columna AND Co.id_columna = "+projectManager.getYourProjects().get(i).getColumnes().get(k).getId_columna()+"");
                                        //System.out.println("SELECT * FROM Tarea AS Ta, Columna AS Co WHERE Co.id_columna = Ta.id_columna AND Co.id_columna = "+projectManager.getYourProjects().get(i).getColumnes().get(k).getId_columna()+"");
                                        while (prueba.next()){
                                            Tasca tasca = new Tasca();
                                            tasca.setNom(prueba.getString("nombre"));
                                            tasca.setDescripcio(prueba.getString("descripcion"));
                                            tasca.setOrdre(prueba.getInt("orden"));
                                            projectManager.getYourProjects().get(i).getColumnes().get(k).getTasques().add(tasca);
                                        }
                                    }
                                }
                                //oos.writeObject(projectManager);
                                //dos.writeUTF("Logged");
                                for (int i = 0; i < projectManager.getYourProjects().size(); i++){
                                        System.out.println("Proyecto " + (i + 1) + ": " + projectManager.getYourProjects().get(i).getName());
                                    try {
                                        if(!(projectManager.getYourProjects().get(i).getColumnes().size() < 1)){
                                            System.out.println("       - Columna 1: " + projectManager.getYourProjects().get(i).getColumnes().get(0).getNom());
                                            if(!(projectManager.getYourProjects().get(i).getColumnes().get(0).getTasques().size() < 1)){
                                                System.out.println("            - Tasca 1: " + projectManager.getYourProjects().get(i).getColumnes().get(0).getTasques().get(0).getNom());
                                            }
                                        }

                                    }catch (IndexOutOfBoundsException e){
                                        e.printStackTrace();
                                    }
                                }

                                prueba = conn.selectQuery("SELECT DISTINCT Po.* FROM proyecto AS Po, usuarioproyecto AS Up, usuario AS Us WHERE Us.username LIKE '" + projectManager.getUsuari().getCorreu() + "' AND Us.username = Up.username AND Up.id_proyecto = Po.id_proyecto AND Po.username NOT LIKE '" + projectManager.getUsuari().getCorreu() + "';");

                                while(prueba.next()){

                                    Project proyecto = new Project();
                                    proyecto.setName(prueba.getString("nombre"));
                                    proyecto.setUsername(prueba.getString("username"));
                                    proyecto.setDay(prueba.getInt("dia_proyecto"));
                                    proyecto.setMonth(prueba.getInt("mes_proyecto"));
                                    proyecto.setYear(prueba.getInt("year_proyecto"));
                                    projectManager.getSharedProjects().add(proyecto);

                                }
                                //oos.writeObject(projectManager);
                                //dos.writeUTF("Logged");
                                for (int i = 0; i < projectManager.getSharedProjects().size(); i++){

                                    System.out.println("Proyecto compartido " + (i+1) + ": " + projectManager.getSharedProjects().get(i).getName());
                                }

                                prueba = conn.selectQuery("SELECT * FROM Usuario;");

                                while(prueba.next()){

                                    String nombre;
                                    nombre = prueba.getString("username");
                                    projectManager.getUsuarios().add(nombre);

                                }
                                oos.writeObject(projectManager);
                                dos.writeUTF("Logged");
                                for (int i = 0; i < projectManager.getUsuarios().size(); i++){

                                    System.out.println("Usuario " + (i+1) + ": " + projectManager.getUsuarios().get(i));
                                }

                            } else {
                                oos.writeObject(projectManager);
                                dos.writeUTF("Pass or Login incorrect");
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            System.out.println("Problema al recuperar les dades de la BBDD 2...");
                        }

                        System.out.println("Imagen: " + projectManager.getProject().getBackground());


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

                        String query = "INSERT INTO Proyecto(username, nombre, year_proyecto, mes_proyecto, dia_proyecto) VALUES ('" + projectManager.getUsuari().getCorreu() + "', '" + projectManager.getProject().getName() + "', '" + projectManager.getProject().getYear() + "', '" + projectManager.getProject().getMonth() + "', '" + projectManager.getProject().getDay() + "')";
                        System.out.println(query);
                        conn.insertQuery(query);
                        System.out.println("hola 1");
                        prueba = conn.selectQuery("SELECT id_proyecto FROM Proyecto ORDER BY id_proyecto DESC LIMIT 1");
                        prueba.next();
                        int id_projecte = prueba.getInt("id_proyecto");
                        //System.out.println("PATATA " + projectManager.getProject().getMembres().size());
                        for(int i = 0; i < projectManager.getProject().getMembres().size() ; i++) {
                            conn.insertQuery("INSERT INTO `UsuarioProyecto`(`username`,`id_proyecto`) VALUES ('" + projectManager.getProject().getMembres().get(i) + "', '" + id_projecte + "')");
                            //System.out.println("Ur mom gay");
                        }
                        oos.writeObject(projectManager);
                        dos.writeUTF("VALORES RECOGIDOS");

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


