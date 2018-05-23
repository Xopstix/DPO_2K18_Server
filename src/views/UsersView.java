package views;

import controller.ServerController;
import model.ProjectManager;

import javax.swing.*;
import java.awt.*;
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

        initComponents();
        createView();

        this.setSize(600, 300);
        this.setTitle("Evolucio Usuaris");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void initComponents(){

        dataUsuaris = new DefaultListModel<>();
        jlUsuaris = new JList<>(dataUsuaris);

        jbContinua = new JButton("Continuar");
        jbMostra = new JButton("Mostra Usuaris");

        jpLeft = new JPanel(new FlowLayout());
        jpRight = new JPanel(new FlowLayout());
        jpCentral = new JPanel(new GridLayout(1,2));

        usuaris = new ArrayList<>();
    }

    public void createView (){

        this.jpLeft.add(jbMostra);
        this.jpLeft.add(jbContinua);
        this.jpRight.add(jlUsuaris);

        this.jpCentral.add(jpLeft);
        this.jpCentral.add(jpRight);

        this.getContentPane().add(jpCentral);
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
