/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Consultation;
import com.bibliotheque.Service.ServiceConsultation;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;

import java.text.SimpleDateFormat;
/**
 *
 * @author medam
 */
public class AjoutConsultation extends Form {

    Form current;
    public AjoutConsultation(Form previous ) {
               setTitle("Ajout consultation");
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {
            previous.showBack();
        });
     
        getTitleArea().setUIID("Container");
    
        getContentPane().setScrollVisible(false);
        
       
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

     

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

     
        
  
   

        
        
        
      
        TextField notes = new TextField("", "entrer notes!!");
        notes.setUIID("TextFieldBlack");
        addStringValue("notes",notes);
        
        TextField prix = new TextField("", "entrer prix!!");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix",prix);
        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                if(notes.getText().equals("") || prix.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Consultation r = new Consultation(String.valueOf(notes.getText()
                                  ).toString(),
                                  String.valueOf(prix.getText()).toString());
                    
                    System.out.println("data  reclamation == "+r);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceConsultation.getInstance().ajoutConsultation(r);
                    
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
