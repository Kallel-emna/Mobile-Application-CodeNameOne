/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Service;

import com.bibliotheque.Entite.Utilisateur;
import com.bibliotheque.gui.ProfileForm;
import com.bibliotheque.gui.SessionManager;
import com.bibliotheque.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.bibliotheque.gui.SessionManager;
import com.bibliotheque.utils.Statics;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author moham
 */
public class ServiceUtilisateur {

    public ArrayList<Utilisateur> users;

    public static ServiceUtilisateur instance = null;
    public ConnectionRequest req;
    public boolean resultOK;
    //singleton 

    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public ServiceUtilisateur() {
        req = new ConnectionRequest();

    }

    //Signup
    public void signup(TextField email, ComboBox<String> roles, TextField password, TextField firstname, TextField lastname, TextField address, TextField telephone, Resources res) {

        String url = Statics.BASE_URL + "/User/signup?email=" + email.getText().toString()
                + "&roles" + roles.getSelectedItem().toString()
                + "&password=" + password.getText().toString()
                + "&firstname=" + firstname.getText().toString()
                + "&lastname=" + lastname.getText().toString()
                + "&address=" + address.getText().toString()
                + "&telephone=" + telephone.getText().toString();

        req.setUrl(url);

        //Control saisi
        if (firstname.getText().equals(" ") && lastname.getText().equals(" ") && address.getText().equals(" ") && password.getText().equals(" ") && telephone.getText().equals(" ") && email.getText().equals(" ")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            //njib data ly7atithom fi form 
            byte[] data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 

            System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    //SignIn
    public void signin(TextField email, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/User/signin?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";
            try {
                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);

                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setEmail(user.get("email").toString());
                    

                    if (user.size() > 0) {new ProfileForm(rs).show();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Utilisateur> getAllTasks() {

        String url = Statics.BASE_URL + "/User/shows";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTask(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public ArrayList<Utilisateur> parseTask(String jsonText) {

        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> taskListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) taskListJson.get("root");
            for (Map<String, Object> obj : list) {
                Utilisateur t = new Utilisateur();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if (obj.get("email") == null) {
                    t.setEmail("null");
                } else {
                    t.setEmail(obj.get("email").toString());
                    t.setRole(obj.get("roles").toString());
                    t.setPassword(obj.get("password").toString());
                    t.setFirstname(obj.get("firstname").toString());
                    t.setLastname(obj.get("lastname").toString());
                    t.setAddress(obj.get("address").toString());
                    t.setTelephone(obj.get("telephone").toString());

                }

                users.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return users;
    }

    public boolean modifier(Utilisateur User) {
        String url = Statics.BASE_URL + "/User/modifier?id=" + User.getId()
                + "&email=" + User.getEmail()
                + "&firstname="+ User.getFirstname()
                + "&lastname=" + User.getLastname()
                + "&address=" + User.getAddress()
                +"&telephone=" + User.getTelephone();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "/Userr/delete?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
}
