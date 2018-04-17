package views;

import controller.ServerController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by checho y manu on 08/04/2018.
 */
public class VistaEvolucio extends JFrame{

    private JPanel jpCentral;
    private JPanel jpTop;
    private JPanel jpX;
    private JPanel jpY;
    private DrawPanel jpBottom;
    private JButton jbSetmana;
    private JButton jbMes;
    private JButton jbAny;
    private JLabel jlx;
    private JLabel jly;
    private int x = 10;
    private int y = 10;

    public VistaEvolucio() throws IOException{

        this.jpCentral = new JPanel();
        this.jpTop = new JPanel();
        this.jpBottom = new DrawPanel();
        this.jpBottom.setXY(10,10);
        this.jpX = new JPanel();
        this.jpY = new JPanel();
        this.jbSetmana = new JButton("Setmana");
        this.jbMes = new JButton("Mes");
        this.jbAny = new JButton("Any");
        this.jlx = new JLabel("Divison Temporal");
        this.jlx.setHorizontalAlignment(SwingConstants.CENTER);
        this.jly = new JLabel("Num. Projectes");
        this.jly.setVerticalAlignment(SwingConstants.CENTER);

        this.jpCentral.setBorder(BorderFactory.createTitledBorder("Evolució usuaris"));
        this.jpCentral.setLayout(new BorderLayout());
        this.jpTop.setLayout(new FlowLayout());
        this.jpTop.add(jbSetmana);
        this.jpTop.add(jbMes);
        this.jpTop.add(jbAny);
        this.jpX.add(jlx);
        this.jpY.add(jly);
        this.jpCentral.add(jpTop, BorderLayout.NORTH);
        this.jpCentral.add(jpBottom, BorderLayout.CENTER);
        this.jpCentral.add(jpX, BorderLayout.SOUTH);
        this.jpCentral.add(jpY, BorderLayout.WEST);
        this.add(jpCentral);


        this.setSize(800,400);
        this.setResizable(true);
        this.setTitle("Evolució usuaris");
        this.setLocationRelativeTo(null);

    }

    public void actualizarVista(int id){
        if(id == 0){
            x = 7 + 1;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.updateUI();
            jlx.setText("Semanas");

        }
        if(id == 1){
            x = 31 + 1;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.updateUI();
            jlx.setText("Meses");
        }
        if(id == 2){
            x = ~~(365/10) + 1;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.updateUI();
            jlx.setText("Años");
        }
    }

    public void registrarControladorBoton(ServerController serverController){

        this.jbAny.setActionCommand("BTN_ANY");
        this.jbMes.setActionCommand("BTN_MES");
        this.jbSetmana.setActionCommand("BTN_SETMANA");

        this.jbAny.addActionListener(serverController);
        this.jbMes.addActionListener(serverController);
        this.jbSetmana.addActionListener(serverController);

    }
}
