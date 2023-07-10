/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotheque.Entite;

/**
 *
 * @author youfo
 */
public class Task {

    private int id, status;
    private String name,title,date;

    public Task( String title,String name, int status, String date) {
        this.status = status;
        this.name = name;
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Task() {

    }

    public Task(int status, String name) {
        this.status = status;
        this.name = name;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task(int id, int status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", status=" + status + ", name=" + name + "\n";
    }

}
