import utility.ConectorDB;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xavipargela on 12/3/18.
 */
public class Main {
    public static void main(String[] args) {

        //Gestion BBDD
        ResultSet prueba;

        //Login BBDD
        ConectorDB conn = new ConectorDB("adminOrg", "cartofen", "organizerDB", 8889);
        conn.connect();

        prueba = conn.selectQuery("SELECT * FROM usuarios");

        try {
            //Recorremos toda la tabla de usuarios de la BBDD
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

    }
}
