package com.bibliotheque.gui;

import com.bibliotheque.Entite.Utilisateur;
import com.bibliotheque.Entite.chambre;
import com.bibliotheque.Service.ServiceUtilisateur;
import com.bibliotheque.Service.serviceschambre;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

public class showchambre extends BaseForm {

    ArrayList<chambre> chambres;

    public showchambre(BaseForm aThis, Resources res) {
       chambres = serviceschambre.getInstance().getAllTasks();
        TableLayout tl = new TableLayout(chambres.size() + 1, 4);
        setLayout(tl);
        setTitle("Liste de notre Utilisateur");
        super.addSideMenu(res);
      add(new Label("ID"));
        add(new Label("Etage"));
        add(new Label("Prix"));
        add(new Label("Capacité"));

                for (chambre c : chambres) {
            add(new Label(c.getId()));
            add(new Label(c.getEtage()));
            add(new Label(c.getPrix()));
            add(new Label(c.getCapacite()));
       
          
        }

    }
    }

