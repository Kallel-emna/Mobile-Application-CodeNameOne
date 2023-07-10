/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Entite;

/**
 *
 * @author kalle
 */
public class Bilan {
    private int id;
    private String Antecedents;
    private String Taille;
    private String Poids;
    private String ExamensBiologiques;
    
    

    public Bilan() {
    }

    public Bilan(int id, String Antecedents, String Taille, String Poids, String ExamensBiologiques) {
        this.id = id;
        this.Antecedents = Antecedents;
        this.Taille = Taille;
        this.Poids = Poids;
        this.ExamensBiologiques = ExamensBiologiques;
    }

    public Bilan(String Antecedents, String Taille, String Poids, String ExamensBiologiques) {
        this.Antecedents = Antecedents;
        this.Taille = Taille;
        this.Poids = Poids;
        this.ExamensBiologiques = ExamensBiologiques;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAntecedents() {
        return Antecedents;
    }

    public void setAntecedents(String Antecedents) {
        this.Antecedents = Antecedents;
    }

    public String getTaille() {
        return Taille;
    }

    public void setTaille(String Taille) {
        this.Taille = Taille;
    }

    public String getPoids() {
        return Poids;
    }

    public void setPoids(String Poids) {
        this.Poids = Poids;
    }

    public String getExamensBiologiques() {
        return ExamensBiologiques;
    }

    public void setExamensBiologiques(String ExamensBiologiques) {
        this.ExamensBiologiques = ExamensBiologiques;
    }

    @Override
    public String toString() {
        return "bilan{" + "id=" + id + ", Antecedents=" + Antecedents + ", Taille=" + Taille + ", Poids=" + Poids + ", ExamensBiologiques=" + ExamensBiologiques + '}';
    }
    
}
