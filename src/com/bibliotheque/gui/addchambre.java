/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.chambre;
import com.bibliotheque.Service.serviceschambre;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author medam
 */
public class addchambre extends Form {

    Form current;
    public addchambre(Form previous ) {
        
        super("ajout chambre",BoxLayout.y()); 
       
        Toolbar tb = new Toolbar(true);
        current = this ;
        
        setToolbar(tb);
         getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->{
       previous.showBack();
        });
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
 
       TextField etage = new TextField("", "entrer etage");
etage.setUIID("TextFieldBlack");
etage.setConstraint(TextField.NUMERIC); // Restreindre la saisie aux nombres
addStringValue("etage",etage);

TextField prix = new TextField("", "entrer prix");
prix.setUIID("TextFieldBlack");
prix.setConstraint(TextField.NUMERIC); // Restreindre la saisie aux nombres
addStringValue("prix",prix);

TextField capacite = new TextField("", "entrer capacite");
capacite.setUIID("TextFieldBlack");
capacite.setConstraint(TextField.NUMERIC); // Restreindre la saisie aux nombres
addStringValue("capacite",capacite);

        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                    boolean isValid = true;

                if(etage.getText().equals("") || prix.getText().equals("") || capacite.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                 // Vérifier si les valeurs sont numériques
  
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo iduser men session (current user)
                    chambre r = new chambre(String.valueOf(etage.getText()
                                  ).toString(),
                                  String.valueOf(prix.getText()).toString(),
                                  String.valueOf(capacite.getText()).toString());
                    
                    System.out.println("data  reclamation == "+r);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    serviceschambre.getInstance().ajoutchambre(r);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }

        });
        

    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
    }
}