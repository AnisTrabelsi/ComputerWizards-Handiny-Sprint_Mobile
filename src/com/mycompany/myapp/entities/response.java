/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Oubeid Mezni
 */
public class response {
   
    private int id,child,parent;
    private String imageUrl;
    private Task task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public response() {
    }

    
    public response(int id, int child, int parent, String imageUrl, Task task) {
        this.id = id;
        this.child = child;
        this.parent = parent;
        this.imageUrl = imageUrl;
        this.task = task;
    }

    public response(int child, int parent, String imageUrl, Task task) {
        this.child = child;
        this.parent = parent;
        this.imageUrl = imageUrl;
        this.task = task;
    }

    public response(int id, int child, int parent, String imageUrl) {
        this.id = id;
        this.child = child;
        this.parent = parent;
        this.imageUrl = imageUrl;
    }

    public response(int child, int parent, String imageUrl) {
        this.child = child;
        this.parent = parent;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "response{" + "id=" + id + ", child=" + child + ", parent=" + parent + ", imageUrl=" + imageUrl + ", task=" + task + '}';
    }
}

