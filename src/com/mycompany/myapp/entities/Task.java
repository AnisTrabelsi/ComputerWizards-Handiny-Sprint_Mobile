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
public class Task {
    private int id,priority,child,parent;
    private String name,description;
    private boolean state;

    public Task(int id, int priority, int child, int parent, String name, String description, boolean state) {
        this.id = id;
        this.priority = priority;
        this.child = child;
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public Task(String name,String description ,boolean state,int priority, int child, int parent  ) {
        this.priority = priority;
        this.child = child;
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    public Task(String name, String description, boolean state,int priority) {
        this.priority = priority;
        this.name = name;
        this.description = description;
        this.state = state;
    }
    

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

   
    
   

   

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
    @Override
    public String toString() {
        return "Task{" + "priority=" + priority + ", child=" + child + ", parent=" + parent + ", name=" + name + ", description=" + description + ", state=" + state + '}';
    } 
}
