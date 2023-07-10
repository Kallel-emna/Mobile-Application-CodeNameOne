/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Entite;

/**
 *
 * @author kalle
 */
public class productCategory {

    private int id;
    private String nom_cat;

    public productCategory() {
    }

    public productCategory(String name) {
        this.nom_cat = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nom_cat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.nom_cat = name;
    }

    @Override
    public String toString() {
        return "category{" + "id=" + id + ", name=" + nom_cat + "}";
    }

}
