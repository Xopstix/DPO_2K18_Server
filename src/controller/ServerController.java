package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import model.ProjectManager;
import utility.ConectorDB;
import views.UsersView;
import views.VistaEvolucio;
import views.VistaPrincipal;
import views.VistaTop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private UsersView vistaUsers;
    private ConectorDB conn;
    private int day;
    private int month;
    private int year;
    private int week;
    private int[] num_tareas;
    private  int max;

    /**
     * Constructor del controlador que se encarga de poner las condiciones de inicio a partir de la vista y
     * el modelo recibidos por parámetros
     * @param vistaPrincipal vista principal del servidor
     * @param projectManager
     */
    public ServerController(VistaPrincipal vistaPrincipal, VistaEvolucio vistaEvolucio, VistaTop vistaTop, UsersView vistaUsers, ConectorDB conn){

        this.vistaPrincipal = vistaPrincipal;
        this.vistaEvolucio = vistaEvolucio;
        this.vistaTop = vistaTop;
        this.vistaUsers = vistaUsers;
        this.conn = conn;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("BTN_TOP10")){         //Si se quiere iniciar sesión

            vistaTop.setVisible(true);

        }

        if (e.getActionCommand().equals("BTN_EVOLUCION")){         //Si se quiere iniciar sesión

            vistaUsers.setVisible(true);
            System.out.println("Patata");

        }
        // nos guarda todas las tareas completadas por un usuario por dias de la semana
        if (e.getActionCommand().equals("BTN_SETMANA")){
            max = 5;
            this.getDate();
            System.out.println("semana");
            ResultSet prueba;
            conn.connect();
            num_tareas = new int[7];
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day-6) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[4] = prueba.getInt("numero");

                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day-5) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[4] = prueba.getInt("numero");

                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day-4) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[4] = prueba.getInt("numero");

                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day-3) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[3] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day-2) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[2] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day-1) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[1] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND dia_tarea = " + (day) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[0] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            //ordena los datos por año
            System.out.println("Ordenado por dia en Evolución");
            vistaEvolucio.actualizarVista(0, num_tareas, max);

        }
        //nos guarda todas las tareas completadas por un usuario en un espacio de 5 años
        if (e.getActionCommand().equals("BTN_ANY")){
            max = 5;
            this.getDate();
            System.out.println(year);
            ResultSet prueba;
            conn.connect();
            num_tareas = new int[5];

            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year-4) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[4] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year-3) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[3] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year-2) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[2] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year-1) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[1] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "' ;");
            try {
                prueba.next();
                num_tareas[0] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            //ordena los datos por año
            System.out.println("Ordenado por año en Evolución");
            vistaEvolucio.actualizarVista(2, num_tareas, max);

        }
        // nos guarda todas las tareas completadas de un usuario en los pasados 12 meses
        if (e.getActionCommand().equals("BTN_MES")){
            max = 5;
            this.getDate();
            System.out.println(month);
            ResultSet prueba;
            conn.connect();
            num_tareas = new int[12];

            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-11) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[11] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-10) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[10] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-9) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[9] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-8) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[8] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-7) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[7] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-6) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[6] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-5) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[5] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-4) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[4] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-3) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[3] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-2) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[2] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month-1) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[1] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            prueba = conn.selectQuery("SELECT COUNT(*) AS numero FROM Tarea WHERE ano_tarea = " + (year) + " AND mes_tarea = " + (month) + " AND completa = " + 1 + " AND username = '"+ vistaEvolucio.getUsuari()+ "';");
            try {
                prueba.next();
                num_tareas[0] = prueba.getInt("numero");
                if(prueba.getInt("numero")> max){
                    max = prueba.getInt("numero");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            //ordena los datos por mes
            System.out.println("Ordenado por mes en Evolución");
            vistaEvolucio.actualizarVista(1, num_tareas, max);

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

        if (e.getActionCommand().equals("BTN_MOSTRA")){

            //Muestra todos los usuarios por la lista
            System.out.println("Llista d'usuaris");
            ResultSet prueba;
            conn.connect();
            ArrayList<String> usuaris = new ArrayList<String>();

            prueba = conn.selectQuery("SELECT * FROM usuario;");
            try{
                while(prueba.next()){
                    usuaris.add(prueba.getString("username"));
                }
            }catch(SQLException el) {
                el.printStackTrace();
            }
            for(int i=0; i< usuaris.size(); i++){
                System.out.println(usuaris.get(i));
            }
            vistaUsers.actualitzarVista(usuaris);
        }

        if (e.getActionCommand().equals("BTN_CONTINUA")){

            vistaEvolucio.setUsuari(vistaUsers.getUsername());
            vistaEvolucio.setVisible(true);

        }

    }

    public void getDate (){

        java.util.Date fecha = new Date();

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
        week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }
}
