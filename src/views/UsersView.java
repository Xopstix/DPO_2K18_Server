package views;

import com.sun.security.ntlm.Server;
import controller.ServerController;
import model.ProjectManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by checho on 19/05/2018.
 */
public class UsersView extends JFrame {

    private ProjectManager projectManager;
    private JList<String> jlUsuaris;
    private JButton jbContinua;
    private JButton jbMostra;
    private DefaultListModel<String> dataUsuaris;
    private JPanel jpLeft;
    private JPanel jpRight;
    private JPanel jpCentral;
    private ArrayList<String> usuaris;

    public UsersView() {

        jlUsuaris = new JList<String>();
        jbContinua = new JButton("Continuar");
        jbMostra = new JButton("Mostra Usuaris");
        dataUsuaris = new DefaultListModel<String>();
        jpLeft = new JPanel(new FlowLayout());
        jpRight = new JPanel(new FlowLayout());
        jpCentral = new JPanel(new GridLayout(1,2));
        usuaris = new ArrayList<String>();

        this.jpLeft.add(jbMostra);
        this.jpLeft.add(jbContinua);
        this.jpRight.add(jlUsuaris);
        this.jpCentral.add(jpLeft);
        this.jpCentral.add(jpRight);

        this.add(jpCentral);

        this.setSize(600, 300);
        this.setTitle("Evolucio Usuaris");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void registrarControladorBoton(ServerController serverController){

        this.jbMostra.setActionCommand("BTN_MOSTRA");
        this.jbContinua.setActionCommand("BTN_CONTINUA");

        this.jbMostra.addActionListener(serverController);
        this.jbContinua.addActionListener(serverController);

    }

    public void actualitzarVista(ArrayList<String> usuaris){

        for(int i = 0; i < usuaris.size(); i++){

            dataUsuaris.addElement(usuaris.get(i));

        }
        jlUsuaris = new JList<String>(dataUsuaris);

    }






}
