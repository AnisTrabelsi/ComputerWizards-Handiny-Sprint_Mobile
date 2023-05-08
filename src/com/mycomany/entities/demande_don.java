/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;


/**
 *
 * @author anis
 */
public class demande_don {
       private int id_demande_don;
   private int id_utilisateur;
   private int id_don;
   private String date_demande;
   private String justificatif_handicap;
   private String type_produit_demande;
   private String Remarques;
  private String etat;

    public demande_don(int id_demande_don, int id_utilisateur, int id_don, String date_demande, String justificatif_handicap, String type_produit_demande, String Remarques, String etat) {
        this.id_demande_don = id_demande_don;
        this.id_utilisateur = id_utilisateur;
        this.id_don = id_don;
        this.date_demande = date_demande;
        this.justificatif_handicap = justificatif_handicap;
        this.type_produit_demande = type_produit_demande;
        this.Remarques = Remarques;
        this.etat = etat;
    }

    public demande_don(int id_demande_don, int id_utilisateur, int id_don, String justificatif_handicap, String type_produit_demande, String Remarques, String etat) {
        this.id_demande_don = id_demande_don;
        this.id_utilisateur = id_utilisateur;
        this.id_don = id_don;
        this.justificatif_handicap = justificatif_handicap;
        this.type_produit_demande = type_produit_demande;
        this.Remarques = Remarques;
        this.etat = etat;
    }

    public demande_don(int id_utilisateur, String justificatif_handicap, String type_produit_demande, String Remarques, String etat) {
        this.id_utilisateur = id_utilisateur;
        this.justificatif_handicap = justificatif_handicap;
        this.type_produit_demande = type_produit_demande;
        this.Remarques = Remarques;
        this.etat = etat;
    }

    public demande_don(int id_utilisateur, int id_don, String justificatif_handicap, String type_produit_demande, String Remarques) {
        this.id_utilisateur = id_utilisateur;
        this.id_don = id_don;
        this.justificatif_handicap = justificatif_handicap;
        this.type_produit_demande = type_produit_demande;
        this.Remarques = Remarques;
    }

    public demande_don(int id_utilisateur, int id_don, String justificatif_handicap, String type_produit_demande, String Remarques, String etat) {
        this.id_utilisateur = id_utilisateur;
        this.id_don = id_don;
        this.justificatif_handicap = justificatif_handicap;
        this.type_produit_demande = type_produit_demande;
        this.Remarques = Remarques;
        this.etat = etat;
    }

    
    

    
    public demande_don() {
    }

  
  
  
    public int getId_demande_don() {
        return id_demande_don;
    }

    public void setId_demande_don(int id_demande_don) {
        this.id_demande_don = id_demande_don;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_don() {
        return id_don;
    }

    public void setId_don(int id_don) {
        this.id_don = id_don;
    }

    public String getdate_demande() {
        return date_demande;
    }

    public void setDate_demande(String date_demande) {
        this.date_demande = date_demande;
    }

    public String getJustificatif_handicap() {
        return justificatif_handicap;
    }

    public void setJustificatif_handicap(String justificatif_handicap) {
        this.justificatif_handicap = justificatif_handicap;
    }

    public String getType_produit_demande() {
        return type_produit_demande;
    }

    public void setType_produit_demande(String type_produit_demande) {
        this.type_produit_demande = type_produit_demande;
    }

    public String getRemarques() {
        return Remarques;
    }

    public void setRemarques(String Remarques) {
        this.Remarques = Remarques;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
  
}

