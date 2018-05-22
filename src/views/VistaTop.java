package views;

import controller.ServerController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by checho on 08/04/2018.
 */
public class VistaTop extends JFrame{

    private JPanel jpCentral;
    //private JPanel jpTop;
    //private JPanel jpBottom;
    /*private JButton jbSetmana;
    private JButton jbMes;
    private JButton jbAny;*/

    public VistaTop() throws IOException{

        //headers for the table
        String[] columns = new String[] {
                "User", "Tasks Uncompleted", "Total Tasks"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {

                {"marcL", 1, 12},
                {"manusahun", 3, 25},
                {"albertR", 2, 60},
                {"xaviA", 0, 8},
                {"alonsez", 4, 67},
        };
        //create table with data
        JTable table = new JTable(data, columns);

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
