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
    private int ano_tarea;
    private int mes_tarea;
    private int dia_tarea;

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

    public int getAno_tarea() {
        return ano_tarea;
    }

    public void setAno_tarea(int ano_tarea) {
        this.ano_tarea = ano_tarea;
    }

    public int getMes_tarea() {
        return mes_tarea;
    }

    public void setMes_tarea(int mes_tarea) {
        this.mes_tarea = mes_tarea;
    }

    public int getDia_tarea() {
        return dia_tarea;
    }

    public void setDia_tarea(int dia_tarea) {
        this.dia_tarea = dia_tarea;
    }
}