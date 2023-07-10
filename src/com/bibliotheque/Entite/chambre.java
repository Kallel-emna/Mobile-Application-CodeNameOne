package com.bibliotheque.Entite;


public class chambre{

    private String id;
    private String etage;
    private String prix;
    private String capacite;
private boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) {
        return false;
    }

    for (char c : str.toCharArray()) {
        if (!Character.isDigit(c)) {
            return false;
        }
    }

    return true;
}

    public chambre() {
    }

    public chambre(String etage, String prix, String capacite) {
        this.etage = etage;
        this.prix = prix;
        this.capacite = capacite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

   
}

