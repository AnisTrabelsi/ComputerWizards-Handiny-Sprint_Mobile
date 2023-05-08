/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bengh
 */
public class Sauvegardes {

    private static final String DATABASE_NAME = "sauvegardes.db";
    private static final String TABLE_NAME = "sauvegardesposts";

    private Database db;

    public Sauvegardes() {
        try {
            db = Database.openOrCreate(DATABASE_NAME);
            db.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id_sauv_sujet INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, idSujet INTEGER, date_ajout_sauv DATE)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSauvegarde(int userId, int idSujet) {
        try {
            // Vérifier si la voiture existe déjà dans les favoris de l'utilisateur
            boolean SauvExiste = checkSauvExiste(userId, idSujet);
            if (SauvExiste) {
                // Afficher le message d'erreur dans une boîte de dialogue
                Dialog.show("Erreur", "Ce sujet existe déjà dans vos sauvegardes !", "OK", null);
                return; // Sortir de la méthode sans ajouter le favori
            }

            Date dateAjout = new Date();
            db.execute("INSERT INTO " + TABLE_NAME + " (id_user, idSujet, date_ajout_sauv) VALUES (?, ?, ?)", new Object[]{userId, idSujet, dateAjout});
            Dialog.show("Succès", "Sujet ajoutée avec succès aux sauvegardes", "OK", null);
            // Afficher la voiture ajoutée aux favoris
            System.out.println("Sujet ajoutée aux sauvegardes - ID : " + idSujet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSauvExiste(int userId, int idSujet) {
        boolean SauvExiste = false;
        try {
            Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND idSujet = ?", new Object[]{userId, idSujet});
            if (cursor.next()) {
                SauvExiste = true; // Le favori existe déjà
            }
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SauvExiste;
    }

    public void affichageSauvegardes() {
        try {
            Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME);
            while (cursor.next()) {
                Row row = cursor.getRow();
                int id_sauv_sujet = row.getInteger(0);
                int userId = row.getInteger(1);
                int idSujet = row.getInteger(2);
                long date_ajout_sauv = row.getLong(3);
                Date dateAjout = new Date(date_ajout_sauv);

                System.out.println("Sauvegarde - ID : " + id_sauv_sujet);
                System.out.println("User ID : " + userId);
                System.out.println("Voiture ID : " + idSujet);
                System.out.println("Date d'ajout : " + date_ajout_sauv);
                System.out.println("----------------------");
            }
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean supprimerSujetSauvegarde(int userId, int idSujet) {
        try {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE id_user = ? AND idSujet = ?";
            db.execute(query, new Object[]{userId, idSujet});
            return true; // Suppression réussie
        } catch (IOException e) {
            e.printStackTrace();
            return false; // En cas d'erreur, retourne false
        }
    }

    public boolean isSauv(int userId, int idSujet) {
        try {
            Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND idSujet = ?", new Object[]{userId, idSujet});
            boolean hasRows = cursor.next(); // Check if the cursor has any rows
            cursor.close(); // Close the cursor after use
            return hasRows;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void closeDatabase() {
        try {
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Sujet> affichageSujetsSauv(int userId) {
       ArrayList<Sujet> sujetsSauv = new ArrayList<>();
    try {
        Cursor cursor = db.executeQuery("SELECT idSujet FROM " + TABLE_NAME + " WHERE id_user = ?", new Object[]{userId});
        while (cursor.next()) {
            Row row = cursor.getRow();
            int idSujet = row.getInteger(0); // Colonne indexée à partir de 0
            // Récupérer la voiture à partir de l'ID en utilisant ServiceVoiture
            Sujet sujet = ServiceSujet.getInstance().getSujetById(idSujet);
            if (sujet != null) {
                sujetsSauv.add(sujet);
            }
        }
        cursor.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return sujetsSauv;
    }

}
