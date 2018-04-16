package views;

import controller.ServerController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by checho on 08/04/2018.
 */
public class VistaEvolucio extends JFrame{

    private JPanel jpCentral;
    private JPanel jpTop;
    private DrawPanel jpBottom;
    private JButton jbSetmana;
    private JButton jbMes;
    private JButton jbAny;
    private int x = 10;
    private int y = 10;

    public VistaEvolucio() throws IOException{

        this.jpCentral = new JPanel();
        this.jpTop = new JPanel();
        this.jpBottom = new DrawPanel();
        this.jpBottom.setXY(10,10);
        this.jbSetmana = new JButton("Setmana");
        this.jbMes = new JButton("Mes");
        this.jbAny = new JButton("Any");

        this.jpCentral.setBorder(BorderFactory.createTitledBorder("Evolució usuaris"));
        this.jpCentral.setLayout(new GridLayout(2, 1));
        this.jpTop.add(jbSetmana);
        this.jpTop.add(jbMes);
        this.jpTop.add(jbAny);
        this.jpCentral.add(jpTop);
        this.jpCentral.add(jpBottom);
        this.add(jpCentral);


        this.setSize(800, 800);
        this.setResizable(true);
        this.setTitle("Evolució usuaris");
        this.setLocationRelativeTo(null);

    }

    public void actualizarVista(int id){
        if(id == 0){
            x = 10;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.updateUI();
        }
        if(id == 1){
            x = 20;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.updateUI();
        }
        if(id == 2){
            x = 30;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.updateUI();
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
