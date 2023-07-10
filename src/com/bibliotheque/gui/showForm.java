/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Utilisateur;
import com.bibliotheque.Service.ServiceUtilisateur;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;

/**
 *
 * @author moham
 */
public class showForm extends BaseForm {

    ArrayList<Utilisateur> users;

    public showForm(Resources res) {
        users = ServiceUtilisateur.getInstance().getAllTasks();
        TableLayout tl = new TableLayout(users.size() + 1, 5);
        setLayout(tl);
        setTitle("Liste de notre Utilisateur");
        super.addSideMenu(res);
        add(new Label("ID"));
        add(new Label("firstname"));
        add(new Label("lastname"));
        add(new Label("Delete"));
        add(new Label("Update"));
        for (Utilisateur t : users) {
            String sId = String.valueOf(t.getId());
            add(new Label(sId));
            add(new Label(t.getFirstname()));
            add(new Label(t.getLastname()));
            System.out.println(t.getFirstname());
            Image cImage = res.getImage("cza.png");
            Image EImage = res.getImage("Ez.png");
            Button Delete = new Button(cImage);
            Button Update = new Button(EImage);
            Delete.addActionListener(e -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Delete", "veuillez vous supprimer cette compte ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                if (ServiceUtilisateur.getInstance().delete(t.getId())) {
                    new showForm(res).show();
                }

            });
            add(Delete);

            Update.addActionListener(u -> {
                System.out.println("hello update");
                new UpdateForm(res, t).show();
            });
            add(Update);
        }

    }
}
