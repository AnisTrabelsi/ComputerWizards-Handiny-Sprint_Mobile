/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Chaima
 */
public class Voiture {
    private int id_voiture;
    private String immatriculation,marque,modele,boite_vitesse,kilometrage,carburant,image_voiture,description;
    private float prix_location;
    private String date_validation_technique;
    private int id_user;

    
  public Voiture(String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String image_voiture, String description, float prix_location, String date_validation_technique, int id_user) {
       
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique=date_validation_technique;
       
    }
    public Voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }
     public Voiture() {
       
    }
 
    
   
   
       public Voiture(String immatriculation, String marque, String modele, String boite_vitesse, String kilometrage, String carburant, String image_voiture, String description, float prix_location,String date_validation_technique) {
        
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.boite_vitesse = boite_vitesse;
        this.kilometrage = kilometrage;
        this.carburant = carburant;
        this.image_voiture = image_voiture;
        this.description = description;
        this.prix_location = prix_location;
        this.date_validation_technique = date_validation_technique;
       
    }

   





    

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }
    
    
    

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getBoite_vitesse() {
        return boite_vitesse;
    }

    public void setBoite_vitesse(String boite_vitesse) {
        this.boite_vitesse = boite_vitesse;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getString_validation_technique() {
        return date_validation_technique;
    }

    public void setString_validation_technique(String date_validation_technique) {
        this.date_validation_technique = date_validation_technique;
    }
     

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public String getImage_voiture() {
        return image_voiture;
    }

    public void setImage_voiture(String image_voiture) {
        this.image_voiture = image_voiture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(float prix_location) {
        this.prix_location = prix_location;
    }

    
    public int getUser() {
        return id_user;
    }

    public void setUser(int user) {
        this.id_user = user;
    }

    @Override
    public String toString() {
        return   marque+"\t \t"+"\t \t"+modele+"\t \t \t \t"+ description+"\t \t \t"+ boite_vitesse +"\t \t \t"+ prix_location +"\t \t"+ date_validation_technique;

    }
}
