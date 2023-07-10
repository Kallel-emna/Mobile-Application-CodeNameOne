/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.productCategory;
import com.bibliotheque.Service.categoryService;
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
public class AddCategory extends BaseForm {

    public AddCategory(Resources res) {
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
                                new Label("Add category", "Title")
                        )
                )
        );


        TextField titre = new TextField("", "nom ...");
        titre.getStyle().setBgColor(0xffffff);
        titre.getStyle().setFgColor(0x000000);
        titre.getStyle().setBorder(Border.createRoundBorder(50, 50));
        titre.getStyle().setElevation(1);
        titre.getStyle().setPadding(3, 3, 0, 0);
        titre.getStyle().setUnderline(false);

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
                                titre, addPub
                        )
                )
        );
        pub.getStyle().setPadding(70, 70, 40, 40);

        add(pub);

        addPub.addActionListener(l -> {
            if (titre.getText().equals("")) {
                Dialog.show("Error", "Veuillez vérifier les données", "OK", null);
            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
//id mtaa user statique lyn tsir l'intégration
/*
int id = user.getId();
                 */
                productCategory p = new productCategory(titre.getText());
                categoryService.getInstance().addCategory(p);
                iDialog.dispose();
                new ShowCategory(res).show();
                refreshTheme();
            }
        });
    }

}
