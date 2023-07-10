/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Service;

import com.bibliotheque.Entite.Bilan;
import com.bibliotheque.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kalle
 */
public class BilanService {
    public static BilanService instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;
    private boolean resultat;
    public ArrayList<Bilan> Publications;

    public static BilanService getInstance() {
        if (instance == null) {
            instance = new BilanService();
        }
        return instance;
    }

    public BilanService() {
        req = new ConnectionRequest();
    }

    public void addBilan(Bilan ex) {
        String an = ex.getAntecedents();
        String taille = ex.getTaille();
        String poids = ex.getPoids();
        String exx = ex.getExamensBiologiques();

        String url = Statics.BASE_URL + "/addBilanJSON/new?antecedents=" + an + "&taille=" + taille + "&poids=" + poids + "&examens_biologiques=" + exx;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean deleteBilan(int id) {
        String url = Statics.BASE_URL + "/deleteBilanJSON/" + id;

        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

    public void editBilan(Bilan ex) {
        String an = ex.getAntecedents();
        String taille = ex.getTaille();
        String poids = ex.getPoids();
        String exx = ex.getExamensBiologiques();
        String url = Statics.BASE_URL + "/updateBilanJSON/" + ex.getId() + "?antecedents=" + an + "&taille=" + taille + "&poids=" + poids + "&examens_biologiques=" + exx;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    
    
    public ArrayList<Bilan>showBilan() {
        ArrayList<Bilan> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/allBilan";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapExercice = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapExercice.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Bilan re = new Bilan();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String an = obj.get("Antecedents").toString();
                        String t = obj.get("Taille").toString();
                        String p = obj.get("Poids").toString();
                       String exx = obj.get("ExamensBiologiques").toString();
                        
                        re.setId((int)id);
                        re.setAntecedents(an);
                        re.setTaille(t);
                        re.setPoids(p);
                        re.setExamensBiologiques(exx);
                        
                        result.add(re);
                       
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
}
