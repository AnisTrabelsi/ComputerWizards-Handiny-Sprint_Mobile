/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voiture;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import com.mycomany.entities.Voiture;
import com.mycomany.utils.FavorisManager;
import com.mycompany.services.ServiceVoiture;

import java.util.List;

public class AffichageFavorisVoitures extends Form {
     private Form F1,current;

    public AffichageFavorisVoitures(Form previous) {
        setTitle("Voitures favorites");
        setLayout(BoxLayout.y());
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        int userId = 32; // Récupérer l'ID de l'utilisateur connecté (à adapter selon votre logique d'authentification)
        FavorisManager favorisManager = new FavorisManager();
        List<Voiture> voituresFavorites = favorisManager.affichageFavorisVoitures(userId);
        //favorisManager.closeDatabase();

        DefaultListModel model = new DefaultListModel();

        for (Voiture v : voituresFavorites) {
           EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, v.getImage_voiture(), "http://localhost/symfony/projects/Handiny_Symfony-12a56e8fc65c16e961a0ef6f85a0ecd6810d32db%20-%20Copie%20(2)/public/front/images/voitures/" + v.getImage_voiture());
            MultiButton sp = new MultiButton(v.getMarque());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Marque : " + v.getMarque());
            sp.setTextLine2("Modèle : " + v.getModele());
            sp.setTextLine4("Prix de location : " + String.valueOf(v.getPrix_location()));

            
            
       Container buttonsContainer = new Container(BoxLayout.x());
        
        Button supprimerButton = new Button("Supprimer");
        Button detailButton = new Button("voir Détails");
        
    

supprimerButton.addActionListener(evt -> {
    
    
    int voitureId = v.getId_voiture(); // Récupérer l'ID de la voiture à supprimer
    System.out.println("id de voiture à supprimer"+voitureId);
   
    boolean suppressionReussie = favorisManager.supprimerVoitureFavori(userId, voitureId);
    favorisManager.closeDatabase();
    
    if (suppressionReussie) {
        Dialog.show("Succès", "La voiture a été supprimée des favoris", "OK", null);
         new AffichageFavorisVoitures(previous).show();
    } else {
        Dialog.show("Erreur", "La suppression de la voiture a échoué", "OK", null);
    }
});

         
        buttonsContainer.addAll( supprimerButton,detailButton);
         list.add(sp);
         list.add(buttonsContainer);

               F1 = new Form("", BoxLayout.y());
           detailButton.addActionListener((evt) -> {
                
                   
                           
                                            
             Label imat = new Label("Immatriculation :" + v.getImmatriculation());
    Label marque = new Label("Marque :" + v.getMarque());
    Label modele = new Label("Modèle :" + v.getModele());
    Label carb = new Label("Carburant :" + v.getCarburant());
    Label kilom = new Label("Kilométrage :" + v.getKilometrage());
    Label boite = new Label("Boite de vitesse :" + v.getBoite_vitesse());
    Label date = new Label("Date de validation technique :" + v.getString_validation_technique());
    Label desc = new Label("Description :" + v.getDescription());
           Container cnt1 = new Container(BoxLayout.y());
            F1.getToolbar().addCommandToLeftBar("back", null, ev -> new AffichageVoitures(current).show());
              cnt1.addAll(imat, marque, modele, carb, boite, date, desc, kilom);

        Container cnt = new Container(BoxLayout.y());
        //ImageViewer imgview = new ImageViewer(i);
         MultiButton sp2 = new MultiButton(v.getMarque());
            sp2.setIcon(i.fill(500, 500));
       cnt.addAll(sp2,cnt1);
            
            F1.addAll(cnt);
            System.out.println(v);
            F1.show();
            F1.getToolbar().addCommandToLeftBar("back", null, ev -> new AffichageVoitures(current).show());
                                    
                                       /* else{ 
                                               
                                               new Modifierdon(this,don).show();
                                            
                                        }*/
                   //
                
                
                
         
   
            });
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   

}
