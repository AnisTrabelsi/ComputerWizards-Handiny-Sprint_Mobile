/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Dialog;
import com.mycompany.services.ServiceDon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anis
 */
public class FavorisManager {
    private static final String DATABASE_NAME = "favoris.db";
    private static final String TABLE_NAME = "favoris_dons";

    private Database db;

    public FavorisManager() {
        try {
            db = Database.openOrCreate(DATABASE_NAME);
            db.execute("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id_favoris_dons INTEGER PRIMARY KEY AUTOINCREMENT, id_user INTEGER, id_don INTEGER, date_ajout_favoris DATE)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public void addFavori(int userId, int donId) {
    try {
        // Vérifier si la don existe déjà dans les favoris de l'utilisateur
        boolean favoriExiste = checkFavoriExiste(userId, donId);
        if (favoriExiste) {
            // Afficher le message d'erreur dans une boîte de dialogue
            Dialog.show("Erreur", "Cette don existe déjà dans vos favoris !", "OK", null);
            return; // Sortir de la méthode sans ajouter le favori
        }

        Date dateAjout = new Date();
        db.execute("INSERT INTO " + TABLE_NAME + " (id_user, id_don, date_ajout_favoris) VALUES (?, ?, ?)", new Object[]{userId, donId, dateAjout});
        Dialog.show("Succès", "Don ajoutée avec succès aux favoris", "OK", null);
        // Afficher la don ajoutée aux favoris
        System.out.println("Don ajoutée aux favoris - ID : " + donId);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
 
 private boolean checkFavoriExiste(int userId, int donId) {
    boolean favoriExiste = false;
    try {
        Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND id_don = ?", new Object[]{userId, donId});
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
            int donId = row.getInteger(2);
            long dateAjoutLong = row.getLong(3);
            Date dateAjout = new Date(dateAjoutLong);

            System.out.println("Favoris - ID : " + idFavoris);
            System.out.println("User ID : " + userId);
            System.out.println("Don ID : " + donId);
            System.out.println("Date d'ajout : " + dateAjout);
            System.out.println("----------------------");
        }
        cursor.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



  public boolean supprimerDonFavori(int userId, int donId) {
    try {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id_user = ? AND id_don = ?";
        db.execute(query, new Object[]{userId, donId});
        return true; // Suppression réussie
    } catch (IOException e) {
        e.printStackTrace();
        return false; // En cas d'erreur, retourne false
    }
}


  public boolean isFavori(int userId, int donId) {
    try {
        Cursor cursor = db.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_user = ? AND id_don = ?", new Object[]{userId, donId});
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
    
  

    public List<com.mycomany.entities.don> affichageFavorisDons(int userId) {
       List<com.mycomany.entities.don> donsFavorites = new ArrayList<>();
    try {
        Cursor cursor = db.executeQuery("SELECT id_don FROM " + TABLE_NAME + " WHERE id_user = ?", new Object[]{userId});
        while (cursor.next()) {
            Row row = cursor.getRow();
            int idDon = row.getInteger(0); // Colonne indexée à partir de 0
            // Récupérer la don à partir de l'ID en utilisant ServiceDon
            com.mycomany.entities.don don = ServiceDon.getInstance().getDonById(idDon);
            if (don != null) {
                donsFavorites.add(don);
            }
        }
        cursor.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return donsFavorites;
    }
}
