/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.utils;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Dialog;
import com.mycomany.entities.Voiture;
import com.mycompany.services.ServiceVoiture;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavorisManager {
    private static final String DATABASE_NAME = "favoris.db";
    private static final String TABLE_NAME = "favoris_voitures";

    private Database db;

    public FavorisManager() {
        try {
            db = Database.openOrCreate(DATABASE_NAME);
            db.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id_favoris_voitures INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, id_voiture INTEGER, date_ajout_favoris DATE)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public void addFavori(int userId, int voitureId) {
    try {
        // Vérifier si la voiture existe déjà dans les favoris de l'utilisateur
        boolean favoriExiste = checkFavoriExiste(userId, voitureId);
        if (favoriExiste) {
            // Afficher le message d'erreur dans une boîte de dialogue
            Dialog.show("Erreur", "Cette voiture existe déjà dans vos favoris !", "OK", null);
            return; // Sortir de la méthode sans ajouter le favori
        }

        Date dateAjout = new Date();
        db.execute("INSERT INTO " + TABLE_NAME + " (id_user, id_voiture, date_ajout_favoris) VALUES (?, ?, ?)", new Object[]{userId, voitureId, dateAjout});
        Dialog.show("Succès", "Voiture ajoutée avec succès aux favoris", "OK", null);
        // Afficher la voiture ajoutée aux favoris
        System.out.println("Voiture ajoutée aux favoris - ID : " + voitureId);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
 
 private boolean checkFavoriExiste(int userId, int voitureId) {
    boolean favoriExiste = false;
    try {
        Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND id_voiture = ?", new Object[]{userId, voitureId});
        if (cursor.next()) {
            favoriExiste = true; // Le favori existe déjà
        }
        cursor.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return favoriExiste;
}

   
   public void affichageFavoris() {
    try {
        Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME);
        while (cursor.next()) {
            Row row = cursor.getRow();
            int idFavoris = row.getInteger(0);
            int userId = row.getInteger(1);
            int voitureId = row.getInteger(2);
            long dateAjoutLong = row.getLong(3);
            Date dateAjout = new Date(dateAjoutLong);

            System.out.println("Favoris - ID : " + idFavoris);
            System.out.println("User ID : " + userId);
            System.out.println("Voiture ID : " + voitureId);
            System.out.println("Date d'ajout : " + dateAjout);
            System.out.println("----------------------");
        }
        cursor.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



  public boolean supprimerVoitureFavori(int userId, int voitureId) {
    try {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id_user = ? AND id_voiture = ?";
        db.execute(query, new Object[]{userId, voitureId});
        return true; // Suppression réussie
    } catch (IOException e) {
        e.printStackTrace();
        return false; // En cas d'erreur, retourne false
    }
}


  public boolean isFavori(int userId, int voitureId) {
    try {
        Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND id_voiture = ?", new Object[]{userId, voitureId});
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
    
  

    public List<Voiture> affichageFavorisVoitures(int userId) {
       List<Voiture> voituresFavorites = new ArrayList<>();
    try {
        Cursor cursor = db.executeQuery("SELECT id_voiture FROM " + TABLE_NAME + " WHERE id_user = ?", new Object[]{userId});
        while (cursor.next()) {
            Row row = cursor.getRow();
            int idVoiture = row.getInteger(0); // Colonne indexée à partir de 0
            // Récupérer la voiture à partir de l'ID en utilisant ServiceVoiture
            Voiture voiture = ServiceVoiture.getInstance().getVoitureById(idVoiture);
            if (voiture != null) {
                voituresFavorites.add(voiture);
            }
        }
        cursor.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return voituresFavorites;
    }

   

}

