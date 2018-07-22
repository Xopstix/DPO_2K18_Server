package views;

import com.sun.security.ntlm.Server;
import controller.ServerController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
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

    // vista inicial que te permite seleccionar una de las dos funcionalidades del server, ya sea ver el top de contribuidores o ver la evolucion de uno en concreto
    public VistaPrincipal() throws IOException{

        this.jpCentral = new JPanel(new FlowLayout()){
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
        this.jbEvolucion = new JButton("User Evolution");
        this.jbTop10 = new JButton("Top 10 Users");

        this.jpCentral.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white, 2), "Menu", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 16), Color.white));
        this.jpCentral.add(jbEvolucion);
        this.jpCentral.add(jbTop10);
        this.add(jpCentral);

        this.setSize(300, 100);
        this.setResizable(false);
        this.setTitle("LS Organizer Server");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public void registrarControladorBoton(ServerController serverController){

        this.jbTop10.setActionCommand("BTN_TOP10");
        this.jbEvolucion.setActionCommand("BTN_EVOLUCION");

        this.jbTop10.addActionListener(serverController);
        this.jbEvolucion.addActionListener(serverController);

    }
}