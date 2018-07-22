package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Etiqueta
 * Created by Marc on 13/3/18.
 */
public class Etiqueta implements Serializable{

    private String nom;
    private String color;
    private int id_etiqueta;
    private int id_proyecto;



    public int getId_etiqueta() {
        return id_etiqueta;
    }

    public void setId_etiqueta(int id_etiqueta) {
        this.id_etiqueta = id_etiqueta;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

}