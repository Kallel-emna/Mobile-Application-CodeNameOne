/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.gui;

import com.bibliotheque.Entite.Task;
import com.bibliotheque.Service.ServiceTask;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author youfo
 */
public class ShowForm_1 extends BaseForm {
    
    ArrayList<Task> tasks;
    
    public ShowForm_1(Form previous, Resources res) {
        setTitle("List Evenements");
        tasks = ServiceTask.getInstance().getAllTasks();
        TableLayout tl = new TableLayout(tasks.size() + 1, 7);
        setLayout(tl);
        super.addSideMenu(res);
        add(new Label("ID"));
        add(new Label("Titre"));
        add(new Label("Nom"));
        add(new Label("Status"));
        add(new Label("Date"));
        add(new Label("Supprimer"));
        add(new Label("Modifier"));
        for (Task t : tasks) {
            String sId = String.valueOf(t.getId());
            String sStatus = String.valueOf(t.getStatus());
            add(new Label(sId));
            add(new Label(t.getTitle()));
            add(new Label(t.getName()));
            add(new Label(sStatus));
            add(new Label(t.getDate()));
            Button Delete = new Button("Supprimer");
            Button Modifier = new Button("Modifier");
            Delete.addActionListener(e -> {
                
                Dialog dig = new Dialog("Suppression");
                
                if (dig.show("Suppression", "Vous voulez supprimer cet evenement ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                if (ServiceTask.getInstance().delete(t.getId())) {
                    Form home = new HomeForm();
                    new ShowForm_1(home,res).show();
                }
            });
            add(Delete);
            Modifier.addActionListener(e -> {
                new UpdateForm1(t,res).show();
            });
            add(Modifier);
        }
       
        
    }
    
}
