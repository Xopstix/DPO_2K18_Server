package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Tablero del proyecto
 * Created by Marc on 13/3/18.
 */
public class Project implements Serializable{

    private String name;
    private Usuari[] membres;
    private Columna[] columnes;
    private String background;
    private Etiqueta[] etiquetes;
    private int day;
    private int month;
    private  int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usuari[] getMembres() {
        return membres;
    }

    public void setMembres(Usuari[] membres) {
        this.membres = membres;
    }

    public Columna[] getColumnes() {
        return columnes;
    }

    public void setColumnes(Columna[] columnes) {
        this.columnes = columnes;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Etiqueta[] getEtiquetes() {
        return etiquetes;
    }

    public void setEtiquetes(Etiqueta[] etiquetes) {
        this.etiquetes = etiquetes;
    }
}
