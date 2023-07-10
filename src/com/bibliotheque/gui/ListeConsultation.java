/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Consultation;
import com.bibliotheque.Service.ServiceConsultation;
import com.bibliotheque.gui.BaseForm;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;

/**
 *
 * @author medam
 */
public class ListeConsultation extends BaseForm{
ArrayList<Consultation> consultation;
    
    public ListeConsultation(Form previous, Resources res){
        setTitle("List consultation");
        setLayout(BoxLayout.y());
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {
            previous.showBack();
        });
        consultation = ServiceConsultation.getInstance().affichageConsultation();
        
        for(Consultation t: consultation){
            Label cbTask = new Label("notes:"+t.getNotes());
        Label cbTask1 = new Label("prix"+t.getPrix());
            cbTask.setEnabled(false);
             cbTask1.setEnabled(false);
            
            add(cbTask);
            add(cbTask1);
        }
    }
}

        
      
           
    
        

