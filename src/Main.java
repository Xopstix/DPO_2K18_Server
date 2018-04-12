import controller.ServerController;
import utility.ConectorDB;
import views.VistaEvolucio;
import views.VistaPrincipal;
import network.Server;
import views.VistaTop;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xavipargela on 12/3/18.
 */
public class Main {
    public static void main(String[] args) {


        VistaPrincipal vistaPrincipal = null;
        VistaEvolucio vistaEvolucio = null;
        VistaTop vistaTop = null;
        //Gestion BBDD
        ResultSet prueba;

        //Login BBDD
        ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
        conn.connect();

        prueba = conn.selectQuery("SELECT * FROM usuarios");

        try {
            //Recorremos toda la tabla de usuarios de la BBDD.
            while (prueba.next())
            {
                //Especificamente le ponenmos que campos queremos leer de la BBDD
                System.out.println (prueba.getObject("Login") + " " + prueba.getObject("Contraseña"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problema al recuperar les dades...");
        }
        //Desconexion BBDD
        conn.disconnect();

        try{

            vistaPrincipal = new VistaPrincipal();
            vistaEvolucio = new VistaEvolucio();
            vistaTop = new VistaTop();

        }catch (IOException e){

            e.printStackTrace();
        }

        Server server = new Server();

        ServerController serverController = new ServerController(vistaPrincipal, vistaEvolucio, vistaTop);
        vistaPrincipal.registrarControladorBoton(serverController);
        vistaEvolucio.registrarControladorBoton(serverController);
        vistaTop.registrarControladorBoton(serverController);
        vistaPrincipal.setVisible(true);

        server.run();

    }
}
// check
