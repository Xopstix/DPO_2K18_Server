package views;

import controller.CustomListSelectionListener;
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
    private JPanel jpTop;
    private JPanel jpPrincipal;
    private ArrayList<String> usuaris;
    private JScrollPane jspRight;
    private int indice;

    public UsersView() {

        initComponents();
        createView();

        this.setResizable(false);
        this.setSize(300, 300);
        this.setTitle("Evolucio Usuaris");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void initComponents(){

        dataUsuaris = new DefaultListModel<>();
        jlUsuaris = new JList<>(dataUsuaris);
        jlUsuaris.setMaximumSize(new Dimension(150, 100));
        jlUsuaris.setMinimumSize(new Dimension(150, 100));

        jbContinua = new JButton("Continuar");
        jbMostra = new JButton("Mostra Usuaris");

        jspRight = new JScrollPane(jlUsuaris);
        jspRight.setMaximumSize(new Dimension(150, 100));
        jspRight.setMinimumSize(new Dimension(150, 100));

        jpTop = new JPanel(new FlowLayout());
        jpTop.setOpaque(false);

        jpPrincipal = new JPanel();

        this.jpPrincipal = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("images/dgreen.jpg")),
                            -100, -200, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        jpPrincipal.setLayout(new BorderLayout());

        this.jpPrincipal.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white, 2), "Evolucio: ", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 16), Color.white));

        usuaris = new ArrayList<>();
    }

    public void createView (){

        this.jpTop.add(jbMostra);
        this.jpTop.add(jbContinua);

        this.jpPrincipal.add(jpTop, BorderLayout.NORTH);
        this.jpPrincipal.add(jspRight, BorderLayout.CENTER);

        this.getContentPane().add(jpPrincipal);
    }

    public void registrarControladorBoton(ServerController serverController, CustomListSelectionListener customListSelectionListener){

        this.jbMostra.setActionCommand("BTN_MOSTRA");
        this.jbContinua.setActionCommand("BTN_CONTINUA");

        this.jlUsuaris.addListSelectionListener(customListSelectionListener);
        this.jbMostra.addActionListener(serverController);
        this.jbContinua.addActionListener(serverController);

    }

    public void actualitzarVista(ArrayList<String> usuaris){

        dataUsuaris.clear();
        for(int i = 0; i < usuaris.size(); i++){

            dataUsuaris.addElement(usuaris.get(i));


        }
        jlUsuaris = new JList<String>(dataUsuaris);
        jspRight.updateUI();

    }


    public int getIndice() {
        return indice;
    }

    public String getUsername(){

        return dataUsuaris.get(indice);
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public ArrayList<String> getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(ArrayList<String> usuaris) {
        this.usuaris = usuaris;
    }
}
