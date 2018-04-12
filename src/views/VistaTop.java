package views;

import controller.ServerController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by checho on 08/04/2018.
 */
public class VistaTop extends JFrame{

    private JPanel jpCentral;
    private JPanel jpTop;
    private JPanel jpBottom;
    private JButton jbSetmana;
    private JButton jbMes;
    private JButton jbAny;

    public VistaTop() throws IOException{

        this.jpCentral = new JPanel();
        this.jpTop = new JPanel();
        this.jpBottom = new JPanel();
        this.jbSetmana = new JButton("Setmana");
        this.jbMes = new JButton("Mes");
        this.jbAny = new JButton("Any");

        this.jpCentral.setBorder(BorderFactory.createTitledBorder("Top 10 usuaris"));
        this.jpCentral.setLayout(new GridLayout(2, 1));
        this.jpTop.add(jbSetmana);
        this.jpTop.add(jbMes);
        this.jpTop.add(jbAny);
        this.jpCentral.add(jpTop);
        this.jpCentral.add(jpBottom);
        this.add(jpCentral);


        this.setSize(800, 800);
        this.setResizable(true);
        this.setTitle("Top 10 usuaris");
        this.setLocationRelativeTo(null);

    }

    public void registrarControladorBoton(ServerController serverController){

        this.jbAny.setActionCommand("BTN_ANY2");
        this.jbMes.setActionCommand("BTN_MES2");
        this.jbSetmana.setActionCommand("BTN_SETMANA2");

        this.jbAny.addActionListener(serverController);
        this.jbMes.addActionListener(serverController);
        this.jbSetmana.addActionListener(serverController);

    }
}
