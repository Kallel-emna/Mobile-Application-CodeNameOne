/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Entite;

/**
 *
 * @author moham
 */
public class Utilisateur {

    private int id;
    private String email;
    private String password;
    private String Firstname;
    private String Lastname;
    private String address;
    private String telephone;
    private String Roles;

    public Utilisateur(String email, String motdepasse, String Firstname, String Lastname, String address, String telephone, String Roles) {
        this.email = email;
        this.password = motdepasse;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.address = address;
        this.telephone = telephone;
        this.Roles = Roles;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", email=" + email + ", motdepasse=" + password + ", Firstname=" + Firstname + ", Lastname=" + Lastname + ", address=" + address + ", telephone=" + telephone + ", Roles=" + Roles + '}';
    }

  

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    


    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String email, String motdepasse) {
        this.email = email;
        this.password = motdepasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRole(String Roles) {
        this.Roles = Roles;
    }



}
