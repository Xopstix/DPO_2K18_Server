package views;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by checho on 14/03/2018.
 */
public class VistaPrincipal extends JFrame {

    private JPanel jpCentral;
    private JButton jbEvolucion;
    private JButton jbTop10;
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JPanel jp4;

    public VistaPrincipal() throws IOException{

        this.jpCentral = new JPanel();
        this.jbEvolucion = new JButton("Mostrar evolución Charizard");
        this.jbTop10 = new JButton("Top 10 anime betrayals");
        this.jp1 = new JPanel();
        this.jp2 = new JPanel();
        this.jp3 = new JPanel();
        this.jp4 = new JPanel();


        this.jpCentral.setBorder(BorderFactory.createTitledBorder("LSOrganizer SERVER)"));
        this.jpCentral.setLayout(new GridLayout(3, 2));
        this.jpCentral.add(jp1);
        this.jpCentral.add(jp2);
        this.jpCentral.add(jbEvolucion);
        this.jpCentral.add(jbTop10);
        this.jpCentral.add(jp3);
        this.jpCentral.add(jp4);
        this.add(jpCentral);

        this.setSize(400, 200);
        this.setResizable(true);
        this.setTitle("LS Organizer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    /*public void registrarControladorBoton(ControladorBoton controladorBoton){

        this.jbTop10.setActionCommand("BTN_TOP10");
        this.jbEvolucion.setActionCommand("BTN_EVOLUCION");

        this.jbTop10.addActionListener(controladorBoton);
        this.jbEvolucion.addActionListener(controladorBoton);

    }*/
}
