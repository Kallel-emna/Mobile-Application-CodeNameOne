/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Task;
import com.bibliotheque.Service.ServiceTask;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.Resources;

/**
 *
 * @author youfo
 */
public class AddForm extends BaseForm {

    public AddForm(Form previous,Resources res) {
        super.addSideMenu(res);
        setTitle("Ajouter Evenement");
        setLayout(BoxLayout.y());
        TextField tfTitle = new TextField("", "Titre evenement");
        TextField tfName = new TextField("", "Nom de l'employe");
        CheckBox cbStatus = new CheckBox("Status");
        TextField tfDate = new TextField("", "Date Evenement");
        Button btnAdd = new Button("Ajouter");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfName.getText().equals("") || tfTitle.getText().equals("") || tfDate.getText().equals("")) {
                    Dialog.show("Error", "Veuillez remplir tout les champs", "OK", null);
                } else {
                    int status = 0;
                    if (cbStatus.isSelected()) {
                        status = 1;
                    }
                    String name = tfName.getText().toString();
                    Task task = new Task(tfTitle.getText().toString(), tfName.getText().toString(), status, tfDate.getText().toString());
                    if (ServiceTask.getInstance().addTask(task)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                        new BaseForm().show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfTitle, tfName, cbStatus, tfDate, btnAdd);
        

    }

}
