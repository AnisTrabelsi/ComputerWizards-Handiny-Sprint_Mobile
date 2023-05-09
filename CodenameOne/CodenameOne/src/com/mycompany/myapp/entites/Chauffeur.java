/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author imtinen
 */
public class Chauffeur {
    int id_chauffeur;
    String CIN,Nom,Adresse;

    
    public Chauffeur(){}

    public Chauffeur(int id_chauffeur, String Nom ) {
        this.id_chauffeur = id_chauffeur;
        this.Nom = Nom;
    }
    
   
    public Chauffeur(int id_chauffeur , String CIN ,String Nom, String Adresse) {
        this.id_chauffeur = id_chauffeur;
        this.CIN = CIN;
        this.Nom = Nom;
        this.Adresse = Adresse;
        
    
  
    }

 public Chauffeur(String CIN,String Nom, String Adresse) {
        this.CIN = CIN;
        this.Nom = Nom;
        this.Adresse = Adresse;
        
     

        
    }
    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }
    
     public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
    
    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

   

   


    
    @Override
    public String toString() {
        return "Chauffeur{" + " CIN=" + CIN + "Nom=" + Nom + ", Adresse=" + Adresse + '}';
    }



  

    
}
