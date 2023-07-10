/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Service;
import com.bibliotheque.Entite.chambre;
import com.bibliotheque.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author LENOVO
 */
public class serviceschambre {
      public static serviceschambre instance = null;

    public static boolean resultOk = true;
public ArrayList <chambre> tasks;
    //initilisation connection request 
    private ConnectionRequest req;
    
     public static serviceschambre getInstance() {
        if (instance == null) {
            instance = new serviceschambre();
        }
        return instance;
    }

    /**
     *
     */
    public serviceschambre() {
        req = new ConnectionRequest();
    }
      public boolean ajoutchambre(chambre c) {
        String url =Statics.BASE_URL+"/add?etage="+c.getEtage()+"&prix="+c.getPrix()+"&capacite="+c.getCapacite(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return false;
    }
      
public ArrayList<chambre> getAllTasks() {

        String url = Statics.BASE_URL + "/chambre/mehdi/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTask(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    public ArrayList<chambre> parseTask(String jsonText) {

        try {
            tasks =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> taskListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) taskListJson.get("root");
            for (Map<String, Object> obj : list) {
                chambre t = new chambre();
                t.setId(obj.get("id").toString());
                t.setEtage(obj.get("id").toString());
                t.setPrix(obj.get("id").toString());
                t.setCapacite(obj.get("id").toString());    
 
                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return tasks;
    }
    
}

