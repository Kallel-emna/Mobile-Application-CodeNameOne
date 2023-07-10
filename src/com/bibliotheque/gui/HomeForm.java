/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author youfo
 */
public class HomeForm extends Form{

    public HomeForm() {
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnAdd = new Button("Ajouter un evenement");
        Button btnShow = new Button("Afficher tout les evenements");
        btnAdd.addActionListener((evt)-> {
           
        });
        btnShow.addActionListener((evt)-> {
        });
        addAll(btnAdd,btnShow);
    }
    
}
