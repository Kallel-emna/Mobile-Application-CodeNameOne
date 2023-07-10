/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.productCategory;
import com.bibliotheque.Service.categoryService;
import com.bibliotheque.gui.AddCategory;
import com.bibliotheque.gui.BaseForm;
import com.bibliotheque.gui.EditCategory;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
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
public class ShowCategory extends BaseForm {

    public ShowCategory(Resources res) {
        super(BoxLayout.y());
        super.addSideMenu(res);

        //Toolbar tb = getToolbar();
        //tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Titre");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Categorie", "Titre")
                        )
                )
        );

        //tb.setTitleComponent(titleCmp);

        Button newPost = new Button("Ajouter categorie");

        newPost.setAlignment(LEFT);
        newPost.addActionListener((l) -> {
            new AddCategory(res).show();
        });

        add(new Label("Categorie", ""));
        add(newPost);
        /*add(SearchArea);
        add(searchButton);*/

        ArrayList<productCategory> Publications = categoryService.getInstance().showCategory();

        for (productCategory pubs : Publications) {

            

            Button editPost = new Button("Modifier");
            editPost.getStyle().setBgColor(0xffffff);
            editPost.getStyle().setFgColor(0x02C887);
            editPost.getStyle().setBgTransparency(255);
            editPost.getStyle().setBorder(Border.createRoundBorder(30, 30));
            editPost.getStyle().setPadding(1, 1, 1, 1);
            editPost.getStyle().setMargin(2, 2, 2, 2);
            editPost.addActionListener((l) -> {
                new EditCategory(res, pubs).show();
            });

            Button deletePost = new Button("Supprimer");
            deletePost.getStyle().setBgColor(0xffffff);
            deletePost.getStyle().setFgColor(0xFF0000);
            deletePost.getStyle().setBgTransparency(255);
            deletePost.getStyle().setBorder(Border.createRoundBorder(30, 30));
            deletePost.getStyle().setPadding(1, 1, 1, 1);
            deletePost.getStyle().setMargin(2, 2, 2, 2);
            deletePost.addActionListener((l) -> {
                categoryService.getInstance().deleteCategory(pubs.getId());
                if (categoryService.getInstance().deleteCategory(pubs.getId())) {
                    Dialog.show("Success", "catergory deleted", "OK", null);
                    
                }
                new ShowCategory(res).show();
                    refreshTheme();
            });
//String username = user.getUsername();
            Label nom = new Label("Nom: " + pubs.getName());
            nom.getAllStyles().setFgColor(0xffffff);

            Container first = GridLayout.encloseIn(1, editPost);
            Container second = GridLayout.encloseIn(1, deletePost);
            Container pub = BoxLayout.encloseY(
                    BorderLayout.centerAbsolute(
                            BoxLayout.encloseY(
                                    nom
                            )
                    ),//.add(BorderLayout.WEST, pubImage),
                    BoxLayout.encloseY(first, second)
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
