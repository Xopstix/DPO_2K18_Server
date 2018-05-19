package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.ProjectManager;
import utility.ConectorDB;
import views.VistaEvolucio;
import views.VistaPrincipal;
import views.VistaTop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Controlador de los botones de la vista del servidor
 * Created by checho on 08/04/2018.
 */
public class ServerController implements ActionListener{

    private VistaPrincipal vistaPrincipal;
    private VistaEvolucio vistaEvolucio;
    private VistaTop vistaTop;
    private ConectorDB conn;
    private int day;
    private int month;
    private int year;
    private int[] num_proyectos;

    /**
     * Constructor del controlador que se encarga de poner las condiciones de inicio a partir de la vista y
     * el modelo recibidos por parámetros
     * @param vistaPrincipal vista principal del servidor
     * @param projectManager
     */
    public ServerController(VistaPrincipal vistaPrincipal, VistaEvolucio vistaEvolucio, VistaTop vistaTop, ConectorDB conn){

        this.vistaPrincipal = vistaPrincipal;
        this.vistaEvolucio = vistaEvolucio;
        this.vistaTop = vistaTop;
        this.conn = conn;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("BTN_TOP10")){         //Si se quiere iniciar sesión

            vistaTop.setVisible(true);

        }

        if (e.getActionCommand().equals("BTN_EVOLUCION")){         //Si se quiere iniciar sesión

            vistaEvolucio.setVisible(true);
            System.out.println("Patata");

        }

        if (e.getActionCommand().equals("BTN_ANY")){         //Si se quiere iniciar sesión
            this.getDate();
            System.out.println(year);
            ResultSet prueba;
            conn.connect();
            num_proyectos = new int[5];

            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Proyecto WHERE year_proyecto = " + (year-4) + " AND username = " + "'pruebaGrafica'" + ";");
            System.out.println("SELECT COUNT(*) AS numero FROM Proyecto WHERE year_proyecto = " + (year-4) + " AND username = " + "'pruebaGrafica'" + ";");
            try {
                prueba.next();
                num_proyectos[4] = prueba.getInt("numero");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Proyecto WHERE year_proyecto = " + (year-3) + " AND username = " + "'pruebaGrafica'" + ";");
            try {
                prueba.next();
                num_proyectos[3] = prueba.getInt("numero");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Proyecto WHERE year_proyecto = " + (year-2) + " AND username = " + "'pruebaGrafica'" + ";");
            try {
                prueba.next();
                num_proyectos[2] = prueba.getInt("numero");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Proyecto WHERE year_proyecto = " + (year-1) + " AND username = " + "'pruebaGrafica'" + ";");
            try {
                prueba.next();
                num_proyectos[1] = prueba.getInt("numero");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Proyecto WHERE year_proyecto = " + (year-0) + " AND username = " + "'pruebaGrafica'" + ";");
            try {
                prueba.next();
                num_proyectos[0] = prueba.getInt("numero");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            //ordena los datos por año
            System.out.println("Ordenado por año en Evolución");
            vistaEvolucio.actualizarVista(2, num_proyectos);

        }

        if (e.getActionCommand().equals("BTN_MES")){         //Si se quiere iniciar sesión
            this.getDate();
            System.out.println(month);
            ResultSet prueba;
            conn.connect();
            num_proyectos = new int[12];
            //ordena los datos por mes
            System.out.println("Ordenado por mes en Evolución");
            vistaEvolucio.actualizarVista(1, num_proyectos);

        }

        if (e.getActionCommand().equals("BTN_SETMANA")){         //Si se quiere iniciar sesión

            //Ordena los datos por semana
            System.out.println("Ordenado por semana en Evolución");
            vistaEvolucio.actualizarVista(0, num_proyectos);

        }

        if (e.getActionCommand().equals("BTN_ANY2")){         //Si se quiere iniciar sesión

            //ordena los datos por año
            System.out.println("Ordenado por año en Top");

        }

        if (e.getActionCommand().equals("BTN_MES2")){         //Si se quiere iniciar sesión

            //ordena los datos por mes
            System.out.println("Ordenado por mes en Top");

        }

        if (e.getActionCommand().equals("BTN_SETMANA2")){         //Si se quiere iniciar sesión

            //Ordena los datos por semana
            System.out.println("Ordenado por semana en Top");

        }

    }

    public void getDate (){

        java.util.Date fecha = new Date();

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }
}
