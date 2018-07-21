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
    private int id_etiqueta;
    private Usuari usuari;
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

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public int getId_etiqueta() {
        return id_etiqueta;
    }

    public void setId_etiqueta(int id_etiqueta) {
        this.id_etiqueta = id_etiqueta;
    }
}