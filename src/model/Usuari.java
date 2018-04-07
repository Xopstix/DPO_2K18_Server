package model;

import java.io.Serializable;

/**
 * Usuario
 * Created by Marc on 13/3/18.
 */
public class Usuari implements Serializable{

    private String nom;
    private String correu;
    private String password;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
