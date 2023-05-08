/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;

import javafx.scene.control.TextField;

/**
 *
 * @author Chayma
 */
public class Utilisateur {
        private int id_utilisateur;
        private String nom ;
        private String prenom ;
        private String cin ;
        private String email ;
        private String telephone ;
        private String login ;
        private String mot_de_passe ;
        private String date_de_naissance ;
        private String region ;
        private String adresse ;
        private int code_postal ;
        private String role ;
        private String code_postal_en_string = Integer.toString(code_postal);
        private static Utilisateur current_user ;
        private String id_en_string = Integer.toString(id_utilisateur);
   private String code;
   private int targetid ;

    public int getTargetid() {
        return targetid;
    }

    public void setTargetid(int targetid) {
        this.targetid = targetid;
    }
   

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Utilisateur(){
    }

    public Utilisateur(String login, String mot_de_passe) {
        this.login = login;
        this.mot_de_passe = mot_de_passe;
    }

     public Utilisateur(int id_utilisateur, String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, String date_de_naissance, String region, String adresse, int code_postal, String role) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naissance = date_de_naissance;
        this.region = region;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.role = role;
    }

    public Utilisateur(int id_utilisateur, String email, String telephone, String login, String adresse) {
        this.id_utilisateur = id_utilisateur;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.adresse = adresse;
    }

    public Utilisateur(String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, String date_de_naissance, String region, String adresse, int code_postal, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naissance = date_de_naissance;
        this.region = region;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String cin, String email, String telephone, String login, String mot_de_passe, String date_de_naissance, String region, String adresse, int code_postal) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.date_de_naissance = date_de_naissance;
        this.region = region;
        this.adresse = adresse;
        this.code_postal = code_postal;
    }

    public Utilisateur(String email, String login, String mot_de_passe, String adresse) {
        this.email = email;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.adresse = adresse;
    }

    public static Utilisateur getCurrent_user() {
        return current_user;
    }

    public static void setCurrent_user(Utilisateur current_user) {
        Utilisateur.current_user = current_user;
    }
    

    public String getCode_postal_en_string() {
        return code_postal_en_string;
    }

    public void setCode_postal_en_string(String code_postal_en_string) {
        this.code_postal_en_string = code_postal_en_string;
    }

    public String getId_en_string() {
        return id_en_string;
    }

    public void setId_en_string(String id_en_string) {
        this.id_en_string = id_en_string;
    }

    
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getString_de_naissance() {
        return date_de_naissance;
    }

    public void setString_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", email=" + email + ", telephone=" + telephone + ", login=" + login + ", mot_de_passe=" + mot_de_passe + ", date_de_naissance=" + date_de_naissance + ", region=" + region + ", adresse=" + adresse + ", code_postal=" + code_postal + ", role=" + role + '}';
    }
     

}
