/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author abbes
 */
public class Covoiturage {
           
     private int id;
    private String depart,destination;
    private String telephone,nom;
    private int Prix,nbrplace ;
    private float latitude,longitude ; 
    private int id_utilisateur;
private String date_covoiturage;


    public Covoiturage() {
    }

    public Covoiturage(int id, String depart, String destination, String telephone, String nom, int Prix, int nbrplace, float latitude, float longitude, int id_utilisateur,  String date_covoiturage) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.telephone = telephone;
        this.nom = nom;
        this.Prix = Prix;
        this.nbrplace = nbrplace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_utilisateur = id_utilisateur;
      //  this.user = user;
        this.date_covoiturage = date_covoiturage;
    }

    public Covoiturage(String depart, String destination, String telephone, String nom, int Prix, int nbrplace, float latitude, float longitude, int id_utilisateur,  String date_covoiturage) {
        this.depart = depart;
        this.destination = destination;
        this.telephone = telephone;
        this.nom = nom;
        this.Prix = Prix;
        this.nbrplace = nbrplace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_utilisateur = id_utilisateur;
     //   this.user = user;
        this.date_covoiturage = date_covoiturage;
    }

     public Covoiturage(String depart, String destination, String telephone, String nom, int Prix, int nbrplace,  String date_covoiturage) {
        this.depart = depart;
        this.destination = destination;
        this.telephone = telephone;
        this.nom = nom;
        this.Prix = Prix;
        this.nbrplace = nbrplace;
     //   this.latitude = latitude;
       // this.longitude = longitude;
        //this.id_utilisateur = id_utilisateur;
     //   this.user = user;
        this.date_covoiturage = date_covoiturage;
    }

    public Covoiturage(int id, String depart, String destination, int Prix, int nbrplace, String date_covoiturage) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.Prix = Prix;
        this.nbrplace = nbrplace;
        this.date_covoiturage = date_covoiturage;
    }

    

    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

  /*  public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }*/

    public String getDate_covoiturage() {
        return date_covoiturage;
    }

    public void setDate_covoiturage(String date_covoiturage) {
        this.date_covoiturage = date_covoiturage;
    }

    @Override
    public String toString() {
        return "Covoiturage{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + ", telephone=" + telephone + ", nom=" + nom + ", Prix=" + Prix + ", nbrplace=" + nbrplace + ", latitude=" + latitude + ", longitude=" + longitude + ", id_utilisateur=" + id_utilisateur +  ", date_covoiturage=" + date_covoiturage + '}';
    }

}
