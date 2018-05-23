package views;

import controller.ServerController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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
    private int day;
    private int month;
    private int year;
    private String usuari;


    public VistaEvolucio() throws IOException{

        this.jpCentral = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("images/dgreen.jpg")),
                            0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.jpCentral.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white, 2), "Organizer" , TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 16), Color.white));
        this.jpTop = new JPanel(new FlowLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("images/dgreen.jpg")),
                            0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.jpBottom = new DrawPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("images/dgreen.jpg")),
                            0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };;
        this.jpBottom.setXY(10,10);
        this.jpX = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("images/dgreen.jpg")),
                            0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };;
        this.jpY = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("images/dgreen.jpg")),
                            0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.jbSetmana = new JButton("Week");
        this.jbMes = new JButton("Month");
        this.jbAny = new JButton("Year");
        this.jlx = new JLabel("Time");
        this.jlx.setHorizontalAlignment(SwingConstants.CENTER);
        this.jly = new JLabel("Number of projects");
        this.jly.setVerticalAlignment(SwingConstants.CENTER);
        jly.setForeground(Color.WHITE);
        jlx.setForeground(Color.WHITE);

        /*this.jpCentral.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 2), "Evolution", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 16), Color.black));*/
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


        this.setSize(800,600);
        this.setResizable(true);
        this.setTitle("User Evolution");
        this.setLocationRelativeTo(null);

    }

    public void actualizarVista(int id, int[] num_proyectos){
        if(id == 0){
            x = 10;
            y = 10;
            jpBottom.setXY(x,y);
            jpBottom.setNumProjects(num_proyectos);
            jpBottom.updateUI();
            jlx.setText("Weeks");

        }
        if(id == 1){
            x = 12;
            y = 20;
            jpBottom.setXY(x,y);
            jpBottom.setNumProjects(num_proyectos);
            jpBottom.updateUI();
            jlx.setText("Months");
        }
        if(id == 2){
            x = 5;
            y = 30;
            jpBottom.setXY(x,y);
            jpBottom.setNumProjects(num_proyectos);
            jpBottom.updateUI();
            jlx.setText("Years");
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
    public void getDate (){
        java.util.Date fecha = new Date();

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }
}
