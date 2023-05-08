/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author Chaima
 */
public class Reservation_voiture {
 private int id_reservation_voiture;
 private int id_user;
 private int id_voiture; 
 private String date_debut_reservation;
 private String  date_fin_reservation;
 private String etat_demande_reservation;
 private String description_reservation;
 private String date_demande_reservation;

    public Reservation_voiture(int id_reservation_voiture, int id_user, int id_voiture, String date_debut_reservation, String date_fin_reservation, String etat_demande_reservation, String description_reservation, String date_demande_reservation) {
        this.id_reservation_voiture = id_reservation_voiture;
        this.id_user = id_user;
        this.id_voiture = id_voiture;
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.etat_demande_reservation = etat_demande_reservation;
        this.description_reservation = description_reservation;
        this.date_demande_reservation = date_demande_reservation;
    }

    public Reservation_voiture(int id_user, int id_voiture, String date_debut_reservation, String date_fin_reservation, String etat_demande_reservation, String description_reservation, String date_demande_reservation) {
        this.id_user = id_user;
        this.id_voiture = id_voiture;
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.etat_demande_reservation = etat_demande_reservation;
        this.description_reservation = description_reservation;
        this.date_demande_reservation = date_demande_reservation;
    }

    public Reservation_voiture(String date_debut_reservation, String date_fin_reservation, String description_reservation) {
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.description_reservation = description_reservation;
    }
    
     public Reservation_voiture(String date_debut_reservation, String date_fin_reservation, String description_reservation,int id_user, int id_voiture) {
        this.date_debut_reservation = date_debut_reservation;
        this.date_fin_reservation = date_fin_reservation;
        this.description_reservation = description_reservation;
         this.id_user = id_user;
        this.id_voiture = id_voiture;
    }

    public Reservation_voiture() {
       
    }
    
    

    public int getId_reservation_voiture() {
        return id_reservation_voiture;
    }

    public void setId_reservation_voiture(int id_reservation_voiture) {
        this.id_reservation_voiture = id_reservation_voiture;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public String getString_debut_reservation() {
        return date_debut_reservation;
    }

    public void setString_debut_reservation(String date_debut_reservation) {
        this.date_debut_reservation = date_debut_reservation;
    }

    public String getString_fin_reservation() {
        return date_fin_reservation;
    }

    public void setString_fin_reservation(String date_fin_reservation) {
        this.date_fin_reservation = date_fin_reservation;
    }

    public String getEtat_demande_reservation() {
        return etat_demande_reservation;
    }

    public void setEtat_demande_reservation(String etat_demande_reservation) {
        this.etat_demande_reservation = etat_demande_reservation;
    }

    public String getDescription_reservation() {
        return description_reservation;
    }

    public void setDescription_reservation(String description_reservation) {
        this.description_reservation = description_reservation;
    }

    public String getString_demande_reservation() {
        return date_demande_reservation;
    }

    public void setString_demande_reservation(String date_demande_reservation) {
        this.date_demande_reservation = date_demande_reservation;
    }

    @Override
    public String toString() {
        return "Reservation_voiture{" + "id_reservation_voiture=" + id_reservation_voiture + ", id_user=" + id_user + ", id_voiture=" + id_voiture + ", date_debut_reservation=" + date_debut_reservation + ", date_fin_reservation=" + date_fin_reservation + ", etat_demande_reservation=" + etat_demande_reservation + ", description_reservation=" + description_reservation + ", date_demande_reservation=" + date_demande_reservation + '}';
    }

    
    
}
