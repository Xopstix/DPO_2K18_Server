package model;

import java.io.Serializable;

/**
 * Modelo del programa cliente
 * Created by Marc on 13/3/18.
 */
public class ProjectManager implements Serializable{

    private Usuari usuari;  //Usuario que almacena los datos de registro de inicio de sesión

    /**
     * Constructor del modelo
     */
    public ProjectManager(){

        usuari = new Usuari();
    }


    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }
}
