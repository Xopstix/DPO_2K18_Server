package model;

import java.io.Serializable;

/**
 * Tarea
 * Created by Marc on 13/3/18.
 */
public class Tasca implements Serializable{

    private String nom;
    private int ordre;
    private String descripcio;
    private Etiqueta etiqueta;
    private Usuari usuari;

    public int getCompleta() {
        return completa;
    }

    private int completa;

    public int isCompleta() {
        return completa;
    }

    public void setCompleta(int completa) {
        this.completa = completa;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

}
