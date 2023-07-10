/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Service;

import com.bibliotheque.Entite.Consultation;
import com.bibliotheque.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author medam
 */
public class ServiceConsultation {
     //singleton 
public ArrayList<Consultation> Consultation;

 
    public ConnectionRequest req;
    public boolean resultOK;
    public static ServiceConsultation instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
  
    
     public static ServiceConsultation getInstance() {
        if (instance == null) {
            instance = new ServiceConsultation();
        }
        return instance;
    }


    public ServiceConsultation() {
        req = new ConnectionRequest();
    }
      public boolean ajoutConsultation(Consultation c) {
        String url =Statics.BASE_URL+"/Amine/add?notes="+c.getNotes()+"&prix="+c.getPrix(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return false;
    }
       
    public ArrayList<Consultation>affichageConsultation() {
    
        String url = Statics.BASE_URL + "/consultation/Chtioui/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Consultation = parseTask(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Consultation;
    }

    public ArrayList<Consultation> parseTask(String jsonText) {

        try {
            Consultation = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> taskListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) taskListJson.get("root");
            for (Map<String, Object> obj : list) {
                Consultation t = new Consultation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if (obj.get("notes") == null) {
                    t.setNotes("null");
                      t.setPrix("null");
                } else {
                    t.setNotes(obj.get("notes").toString());
                     t.setPrix(obj.get("prix").toString());
                }
                
                Consultation.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return Consultation;
    }
        
        
    }
    
    

