package controller;

import views.VistaEvolucio;
import views.VistaPrincipal;
import views.VistaTop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador de los botones de la vista del servidor
 * Created by checho on 08/04/2018.
 */
public class ServerController implements ActionListener{

    private VistaPrincipal vistaPrincipal;
    private VistaEvolucio vistaEvolucio;
    private VistaTop vistaTop;

    /**
     * Constructor del controlador que se encarga de poner las condiciones de inicio a partir de la vista y
     * el modelo recibidos por parámetros
     * @param vistaPrincipal vista principal del servidor
     */
    public ServerController(VistaPrincipal vistaPrincipal, VistaEvolucio vistaEvolucio, VistaTop vistaTop){

        this.vistaPrincipal = vistaPrincipal;
        this.vistaEvolucio = vistaEvolucio;
        this.vistaTop = vistaTop;

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

            //ordena los datos por año
            System.out.println("Ordenado por año en Evolución");
            vistaEvolucio.actualizarVista(2);

        }

        if (e.getActionCommand().equals("BTN_MES")){         //Si se quiere iniciar sesión

            //ordena los datos por mes
            System.out.println("Ordenado por mes en Evolución");
            vistaEvolucio.actualizarVista(1);

        }

        if (e.getActionCommand().equals("BTN_SETMANA")){         //Si se quiere iniciar sesión

            //Ordena los datos por semana
            System.out.println("Ordenado por semana en Evolución");
            vistaEvolucio.actualizarVista(0);

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
}
