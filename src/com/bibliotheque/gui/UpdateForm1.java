/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Task;
import com.bibliotheque.Service.ServiceTask;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author moham
 */
public class UpdateForm1 extends BaseForm {

    public UpdateForm1(Task t,Resources res) {
        
        
        setTitle("Modifier Evenement");
        
        String sStatus = String.valueOf(t.getStatus());
        TextField title = new TextField(t.getTitle(), "Title", 20, TextField.ANY);
        TextField name = new TextField(t.getName(), "Name", 20, TextField.ANY);
        TextField status = new TextField(sStatus, "Status", 20, TextField.ANY);
        TextField date = new TextField(t.getDate(), "Date", 20, TextField.ANY);
        Button btnModifier = new Button("Modifier");
        btnModifier.addPointerPressedListener(l -> {
            int numStatus = Integer.parseInt(status.getText());
            t.setTitle(title.getText());
            t.setName(name.getText());
            t.setStatus(numStatus);
            t.setDate(date.getText());

            if (ServiceTask.getInstance().modifier(t)) {
                Form home = new HomeForm();
                new ShowForm_1(home,res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            Form home = new HomeForm();
            new ShowForm_1(home,res).show();
        });

        Container content = BoxLayout.encloseY(
                new FloatingHint(title),
                new FloatingHint(name),
                new FloatingHint(status),
                new FloatingHint(date)
                
        );

        addAll(content,btnAnnuler,btnModifier);
        show();

    }
}