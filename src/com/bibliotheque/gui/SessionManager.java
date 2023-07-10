/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author moham
 */
public class SessionManager {

    public static Preferences pref; 

    private static int id;
    private static String firstname;
    private static String lastname;
    private static String email;
    private static String passowrd;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id", id);
    }

    public static void setId(int id) {
        pref.set("id", id);
    }

    public static String getFirstname() {
        return pref.get("firstname", firstname);
    }

    public static void setFirstname(String userName) {
        pref.set("username", userName);
    }

    public static String getLastname() {
        return pref.get("lasttname", lastname);
    }

    public static void setLastname() {
        pref.set("lastname", lastname);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd", passowrd);
    }

    public static void setPassowrd(String passowrd) {
        pref.set("passowrd", passowrd);
    }


}
