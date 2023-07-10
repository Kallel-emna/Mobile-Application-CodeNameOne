/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Bilan;
import com.bibliotheque.Service.BilanService;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;

/**
 *
 * @author kalle
 */
public class AddBilan extends BaseForm{
    public AddBilan(Resources res) {
        super(BoxLayout.y());
        super.addSideMenu(res);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Add Bilan", "Title")
                        )
                )
        );

      

        TextField an = new TextField("", "antecedents...");
        an.getStyle().setBgColor(0xffffff);
        an.getStyle().setFgColor(0x000000);
        an.getStyle().setBorder(Border.createRoundBorder(50, 50));
        an.getStyle().setElevation(1);
        an.getStyle().setPadding(3, 3, 0, 0);
        an.getStyle().setUnderline(false);

        TextField taille = new TextField("", "taille...");
        taille.getStyle().setBgColor(0xffffff);
        taille.getStyle().setFgColor(0x000000);
        taille.getStyle().setBorder(Border.createRoundBorder(50, 50));
        taille.getStyle().setElevation(1);
        taille.getStyle().setPadding(3, 3, 0, 0);
        taille.getStyle().setUnderline(false);

        TextField poids = new TextField("", "poids...");
        poids.getStyle().setBgColor(0xffffff);
        poids.getStyle().setFgColor(0x000000);
        poids.getStyle().setBorder(Border.createRoundBorder(50, 50));
        poids.getStyle().setElevation(1);
        poids.getStyle().setPadding(3, 3, 0, 0);
        poids.getStyle().setUnderline(false);

        TextField examenB = new TextField("", "examenB...");
        examenB.getStyle().setBgColor(0xffffff);
        examenB.getStyle().setFgColor(0x000000);
        examenB.getStyle().setBorder(Border.createRoundBorder(50, 50));
        examenB.getStyle().setElevation(1);
        examenB.getStyle().setPadding(3, 3, 0, 0);
        examenB.getStyle().setUnderline(false);

        Button addPub = new Button("Add");
        addPub.getStyle().setBgColor(0xffffff);
        addPub.getStyle().setFgColor(0x0583D2);
        addPub.getStyle().setBgTransparency(255);
        addPub.getStyle().setBorder(Border.createRoundBorder(30, 30));
        addPub.getStyle().setMargin(13, 13, 80, 80);
        addPub.getStyle().setPadding(3, 3, 0, 0);

        Container pub = BoxLayout.encloseY(
                BorderLayout.center(
                        BoxLayout.encloseY(
                                an, taille, poids, examenB, addPub
                        )
                )
        );
        pub.getStyle().setPadding(70, 70, 40, 40);

        add(pub);

        addPub.addActionListener(l -> {
            if (an.getText().equals("") || taille.getText().equals("") || poids.getText().equals("") || examenB.getText().equals("")) {
                Dialog.show("Error", "Veuillez vérifier les données", "OK", null);
            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                Bilan com = new Bilan(an.getText(), taille.getText(), poids.getText(), examenB.getText());
                BilanService.getInstance().addBilan(com);
                iDialog.dispose();
//showprodcuts
                new ShowBilan(res).show();
                /*new (res).show();
                new ShowPub(res).show();*/
                refreshTheme();

            }
        });
    }
}
