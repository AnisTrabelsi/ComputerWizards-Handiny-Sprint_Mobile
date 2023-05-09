/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import com.mycomany.entities.Utilisateur;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String nom;
    private static String prenom;
    private static String cin;
    private static String email;
    private static String telephone;
    private static String login;
    private static String mot_de_passe;
    private static String date_de_naissance;
    private static String region;
    private static String adresse;
    private static int code_postal;
    private static String role;
    private static String genre;
    private static boolean isVerified;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("nom",nom);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPrenom() {
    return pref.get("prenom", prenom);
}

public static void setPrenom(String prenom) {
    pref.set("prenom", prenom);
}

public static String getCin() {
    return pref.get("cin", cin);
}

public static void setCin(String cin) {
    pref.set("cin", cin);
}

public static String getTelephone() {
    return pref.get("telephone", telephone);
}

public static void setTelephone(String telephone) {
    pref.set("telephone", telephone);
}

public static String getLogin() {
    return pref.get("login", login);
}

public static void setLogin(String login) {
    pref.set("login", login);
}

public static String getMot_de_passe() {
    return pref.get("mot_de_passe", mot_de_passe);
}

public static void setMot_de_passe(String mot_de_passe) {
    pref.set("mot_de_passe", mot_de_passe);
}

public static String getDate_de_naissance() {
    return pref.get("date_de_naissance", date_de_naissance);
}

public static void setDate_de_naissance(String date_de_naissance) {
    pref.set("date_de_naissance", date_de_naissance);
}

public static String getRegion() {
    return pref.get("region", region);
}

public static void setRegion(String region) {
    pref.set("region", region);
}

public static String getAdresse() {
    return pref.get("adresse", adresse);
}

public static void setAdresse(String adresse) {
    pref.set("adresse", adresse);
}

public static int getCode_postal() {
    return pref.get("code_postal", code_postal);
}

public static void setCode_postal(int code_postal) {
    pref.set("code_postal", code_postal);
}

public static String getRole() {
    return pref.get("role", role);
}

public static void setRole(String role) {
    pref.set("role", role);
}

public static String getGenre() {
    return pref.get("genre", genre);
}

public static void setGenre(String genre) {
    pref.set("genre", genre);
}

public static boolean isVerified() {
    return pref.get("isVerified", isVerified);
}

public static void setVerified(boolean isVerified) {
    pref.set("isVerified", isVerified);
}

    public static Utilisateur getCurrentUser() {
    Utilisateur user = new Utilisateur();
    user.setId_utilisateur(SessionManager.getId());
    user.setNom(SessionManager.getNom());
    user.setPrenom(SessionManager.getPrenom());
    user.setCin(SessionManager.getCin());
    user.setEmail(SessionManager.getEmail());
    user.setTelephone(SessionManager.getTelephone());
    user.setLogin(SessionManager.getLogin());
    user.setMot_de_passe(SessionManager.getMot_de_passe());
    user.setDate_de_naissance(SessionManager.getDate_de_naissance());
    user.setRegion(SessionManager.getRegion());
    user.setAdresse(SessionManager.getAdresse());
    user.setCode_postal(SessionManager.getCode_postal());
    user.setRole(SessionManager.getRole());
    user.setGenre(SessionManager.getGenre());
    user.setIsVerified(SessionManager.isVerified());
    return user;
}

    
    
}
