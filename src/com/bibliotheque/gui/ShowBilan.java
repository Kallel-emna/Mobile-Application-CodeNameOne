/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Bilan;
import com.bibliotheque.Service.BilanService;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author kalle
 */
public class ShowBilan extends BaseForm {

    public ShowBilan(Resources res) {
        super(BoxLayout.y());
        super.addSideMenu(res);

     //   Toolbar tb = getToolbar();
       // tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Titre");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Bilan", "Titre")
                        )
                )
        );

        //tb.setTitleComponent(titleCmp);

        Button newPost = new Button("Ajouter Bilan");

        newPost.setAlignment(LEFT);
        newPost.addActionListener((l) -> {
            new AddBilan(res).show();
        });

        add(new Label("Bilan", ""));
        add(newPost);

        ArrayList<Bilan> Publications = BilanService.getInstance().showBilan();

        for (Bilan pubs : Publications) {

            Button editPost = new Button("Modifier");
            editPost.getStyle().setBgColor(0xffffff);
            editPost.getStyle().setFgColor(0x02C887);
            editPost.getStyle().setBgTransparency(255);
            editPost.getStyle().setBorder(Border.createRoundBorder(30, 30));
            editPost.getStyle().setPadding(1, 1, 1, 1);
            editPost.getStyle().setMargin(2, 2, 2, 2);
            editPost.addActionListener((l) -> {
                //new EditPub(res, pubs).show();
                new EditBilan(res, pubs).show();
            });

            Button deletePost = new Button("Supprimer");
            deletePost.getStyle().setBgColor(0xffffff);
            deletePost.getStyle().setFgColor(0xFF0000);
            deletePost.getStyle().setBgTransparency(255);
            deletePost.getStyle().setBorder(Border.createRoundBorder(30, 30));
            deletePost.getStyle().setPadding(1, 1, 1, 1);
            deletePost.getStyle().setMargin(2, 2, 2, 2);
            deletePost.addActionListener((l) -> {
                BilanService.getInstance().deleteBilan(pubs.getId());
                if (BilanService.getInstance().deleteBilan(pubs.getId())) {
                    Dialog.show("Success", "Post deleted", "OK", null);

                }
                new ShowBilan(res).show();
                refreshTheme();
            });
//String username = user.getUsername();
            Label antecedent = new Label("Antecedent: " + pubs.getAntecedents());
            antecedent.getAllStyles().setFgColor(0xffffff);

            Label taille = new Label("Taille: " + pubs.getTaille());
            taille.getAllStyles().setFgColor(0xffffff);

            Label poids = new Label("Poids: " + pubs.getPoids());
            poids.getAllStyles().setFgColor(0xffffff);

            Label examenB = new Label("ExamenB: " + pubs.getExamensBiologiques());
            examenB.getAllStyles().setFgColor(0xffffff);

            Container post = BoxLayout.encloseY(
                    GridLayout.encloseIn(3, taille, poids, examenB));
            Container first = GridLayout.encloseIn(1, editPost);
            Container second = GridLayout.encloseIn(1, deletePost);
            Container pub = BoxLayout.encloseY(
                    BorderLayout.centerAbsolute(
                            BoxLayout.encloseY(
                                    antecedent
                            )
                    ),//.add(BorderLayout.WEST, pubImage),
                    BoxLayout.encloseY(post, first, second)
            );

            pub.getStyle().setFgColor(0xffffff);
            pub.getStyle().setBgColor(0x1B82AC);
            pub.getStyle().setBgTransparency(255);
            pub.getStyle().setPadding(7, 7, 7, 7);
            pub.getStyle().setMargin(20, 20, 30, 30);
            pub.getStyle().setBorder(Border.createRoundBorder(50, 50));

            add(pub);
        }
    }
}
