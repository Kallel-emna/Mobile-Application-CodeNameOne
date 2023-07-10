/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Service;

import com.bibliotheque.Entite.Task;
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
 * @author youfo
 */
public class ServiceTask {

    public ArrayList<Task> tasks;

    public static ServiceTask instance = null;
    public ConnectionRequest req;
    public boolean resultOK;

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public ServiceTask() {
        req = new ConnectionRequest();
    }

    public boolean addTask(Task t) {
        String title = t.getTitle();
        String name = t.getName();
        int status = t.getStatus();
        String date = t.getDate();

        String url = Statics.BASE_URL + "/add?title=" + title + "&name=" + name + "&status=" + status + "&date=" + date;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Task> getAllTasks() {

        String url = Statics.BASE_URL + "/liste";
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

    public ArrayList<Task> parseTask(String jsonText) {

        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> taskListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) taskListJson.get("root");
            for (Map<String, Object> obj : list) {
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                if (obj.get("name") == null) {
                    t.setName("null");
                    t.setTitle("null");
                    t.setDate("null");
                } else {
                    t.setName(obj.get("name").toString());
                    t.setTitle(obj.get("title").toString());
                    t.setDate(obj.get("date").toString());
                }

                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return tasks;
    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "/liste/delete?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean modifier(Task task) {
        String url = Statics.BASE_URL + "/liste/modifier?id=" + task.getId()
                + "&title=" + task.getTitle()
                + "&name=" + task.getName()
                + "&status=" + task.getStatus()
                + "&date=" + task.getDate();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

}
