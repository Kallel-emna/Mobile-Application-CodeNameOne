/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Utilisateur;
import com.bibliotheque.Service.ServiceUtilisateur;
import com.codename1.ui.Form;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author moham
 */
public class UpdateForm extends BaseForm {

    Form current;

    public UpdateForm(Resources res, Utilisateur r) {
        super.addSideMenu(res);

        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);

        TextField email = new TextField(r.getEmail(), "E-Mail", 20, TextField.ANY);
        TextField firstname = new TextField(r.getFirstname(), "firstname", 20, TextField.ANY);
        TextField lastname = new TextField(r.getLastname(), "lastname", 20, TextField.ANY);
        TextField address = new TextField(r.getAddress(), "address", 20, TextField.ANY);
        TextField telephone = new TextField(r.getTelephone(), "telephone", 20, TextField.ANY);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
        btnModifier.addPointerPressedListener(l -> {

            r.setEmail(email.getText());
            r.setFirstname(firstname.getText());
            r.setLastname(lastname.getText());
            r.setAddress(address.getText());
            r.setTelephone(telephone.getText());

            if (ServiceUtilisateur.getInstance().modifier(r)) {
                new showForm(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new showForm(res).show();
        });

        Container content = BoxLayout.encloseY(
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(firstname),
                createLineSeparator(),
                new FloatingHint(lastname),
                createLineSeparator(),
                new FloatingHint(address),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }
}
