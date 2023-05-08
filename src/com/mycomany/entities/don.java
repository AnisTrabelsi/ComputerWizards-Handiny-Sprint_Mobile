/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;



/**
 *
 * @author ASUS
 */
public class don {
         private int id_don;
   private int id_utilisateur;
   private String type;
   private String image_don;
   private String description;
   private String date_ajout;

    public don() {
    }
public don(int id_utilisateur, String type, String image_don, String description) {
        this.id_utilisateur = id_utilisateur;
        this.type = type;
        this.image_don = image_don;
        this.description = description;
      
    }

    public don(int id_utilisateur, String type, String image_don, String description, String date_ajout) {
        this.id_utilisateur = id_utilisateur;
        this.type = type;
        this.image_don = image_don;
        this.description = description;
        this.date_ajout = date_ajout;
    }


    public don(int id_don, int id_utilisateur, String type, String image_don, String description, String date_ajout) {
        this.id_don = id_don;
        this.id_utilisateur = id_utilisateur;
        this.type = type;
        this.image_don = image_don;
        this.description = description;
        this.date_ajout = date_ajout;
    }
  public don(int id_don, int id_utilisateur, String type, String image_don, String description) {
        this.id_don = id_don;
        this.id_utilisateur = id_utilisateur;
        this.type = type;
        this.image_don = image_don;
        this.description = description;
       
    }

    

    public int getId_don() {
        return id_don;
    }

    public void setId_don(int id_don) {
        this.id_don = id_don;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_don() {
        return image_don;
    }

    public void setImage_don(String image_don) {
        this.image_don = image_don;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "don{" + "id_don=" + id_don + ", id_utilisateur=" + id_utilisateur + ", type=" + type + ", image_don=" + image_don + ", description=" + description + ", date_ajout=" + date_ajout + '}';
    }



}
