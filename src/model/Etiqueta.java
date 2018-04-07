package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Etiqueta
 * Created by Marc on 13/3/18.
 */
public class Etiqueta implements Serializable{

    private String nom;
    private Color color;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
