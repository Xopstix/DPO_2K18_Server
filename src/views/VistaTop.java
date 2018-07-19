package views;

import controller.ServerController;
import utility.ConectorDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by checho on 08/04/2018.
 */
public class VistaTop extends JFrame{

    private JPanel jpCentral;
    private ConectorDB conn;

    public VistaTop(ConectorDB conn) throws IOException{
        this.conn = conn;

        //headers for the table
        String[] columns = new String[] {
                "User", "Tasks Uncompleted", "Total Tasks"
        };
        //actual data for the table in a 2d array
        Object[][] data = new Object[][]{
        };
        DefaultTableModel dtm= new DefaultTableModel(data, columns);
        JTable table = new JTable(dtm);

        ResultSet prueba;
        ResultSet tasquesCompletes;
        ResultSet tasquesIncompletes;
        conn.connect();

        prueba = conn.selectQuery("SELECT * FROM Usuario");
        try {
            //Recorremos toda la tabla de usuarios de la BBDD.
            while (prueba.next()) {
                System.out.println("1");
                tasquesCompletes = conn.selectQuery("SELECT COUNT(*) AS completes FROM Tarea WHERE Completa = 1 AND username = '" + prueba.getString("username") +"';");
                while(tasquesCompletes.next()) {

                    tasquesIncompletes = conn.selectQuery("SELECT COUNT(*) AS incompletes FROM Tarea WHERE Completa = 0 AND username = '" + prueba.getString("username") + "';");
                    while(tasquesIncompletes.next()){

                        Object[] newRow ={prueba.getString("username"), tasquesIncompletes.getInt("incompletes"), tasquesCompletes.getInt("completes") };
                        dtm.addRow(newRow);

                    }

                }


            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Problema al recuperar les dades de la BBDD 1234...");
        }

        //create table with data


        table.setEnabled(false); //para que no se pueda modificar

        //add the table to the frame
        this.add(new JScrollPane(table));

        this.setSize(400, 200);
        this.setTitle("Top 10 Users");
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void registrarControladorBoton(ServerController serverController){

        //Vacía por el momento

    }
}
