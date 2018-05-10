package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Modelo del programa cliente
 * Created by Marc on 13/3/18.
 */
public class ProjectManager implements Serializable{

    private Usuari usuari;  //Usuario que almacena los datos de registro de inicio de sesión
    private Project project;
    private int mode;
    private ArrayList<Project> projects;
    /**
     * Constructor del modelo
     */

    public ProjectManager(){

        usuari = new Usuari();
        project = new Project();
        projects = new ArrayList<>();

    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
