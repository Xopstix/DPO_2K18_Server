package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Tablero del proyecto
 * Created by Marc on 13/3/18.
 */
public class Project implements Serializable{

    private String name;
    private String username;
    private ArrayList<String> membres;
    private ArrayList<Columna> columnes;
    private String background;
    private ArrayList<Etiqueta> etiquetes;
    private int day;
    private int month;
    private int year;
    private int week;
    private int idProyecto;

    public Project(){
        membres = new ArrayList<>();
        columnes = new ArrayList<>();
        etiquetes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMembres() {
        return membres;
    }

    public void setMembres(ArrayList<String> membres) {
        this.membres = membres;
    }

    public ArrayList<Columna> getColumnes() {
        return columnes;
    }

    public void setColumnes(ArrayList<Columna> columnes) {
        this.columnes = columnes;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public ArrayList<Etiqueta> getEtiquetes() {
        return etiquetes;
    }

    public void setEtiquetes(ArrayList<Etiqueta> etiquetes) {
        this.etiquetes = etiquetes;
    }

    public void setDate (){
        java.util.Date fecha = new Date();

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
        week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
