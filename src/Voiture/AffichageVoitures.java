/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voiture;

import ReservationVoiture.AjouterReservationVoiture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycomany.entities.Voiture;
import com.mycomany.utils.FavorisManager;
import com.mycompany.services.ServiceDon;
import com.mycompany.services.ServiceVoiture;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AffichageVoitures extends Form {
  private Form F1,current;
    public AffichageVoitures(Form previous) {
        setTitle("Liste des voitures");
        setLayout(BoxLayout.y());
        ArrayList<com.mycomany.entities.Voiture> voitures = ServiceVoiture.getInstance().affichageVoiture();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.Voiture v : voitures) {
            //System.out.println(don.getType());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, v.getImage_voiture(), "http://localhost/symfony/projects/Handiny_Symfony-12a56e8fc65c16e961a0ef6f85a0ecd6810d32db%20-%20Copie%20(2)/public/front/images/voitures/" + v.getImage_voiture());
            MultiButton sp = new MultiButton(v.getMarque());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Marque : " + v.getMarque());
            sp.setTextLine2("Modèle : " + v.getModele());
            sp.setTextLine4("Prix de location : " + String.valueOf(v.getPrix_location()));

            
            
       Container buttonsContainer = new Container(BoxLayout.x());
        
         Button detailButton = new Button("voir Détails");
         Button resButton = new Button("réserver");
         
         /*favoris*/
Button favorisButton = new Button();
favorisButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, "Button", 4.5f));
favorisButton.addActionListener(e -> {
    // Code pour ajouter la voiture aux favoris
    int voitureId = v.getId_voiture(); // Id de la voiture actuelle
   int id_user=32;
   //int userId = getCurrentUserId(); // Id de l'utilisateur actuel (à définir selon votre logique d'authentification)
    FavorisManager favorisManager = new FavorisManager();
    favorisManager.addFavori(id_user, voitureId); // Ajouter le favori avec une note initiale de 0
    favorisManager.closeDatabase();
    favorisButton.setEnabled(false); // Désactiver le bouton après l'ajout aux favoris
});

resButton.addActionListener(e -> {
    // Code pour ajouter la voiture aux favoris
    int voitureId = v.getId_voiture(); // Id de la voiture actuelle
    int user_id=32;
   
      new AjouterReservationVoiture(this,v,user_id).show();
});

        buttonsContainer.addAll(detailButton,resButton,favorisButton);
        

        

       
            //   sp.setTextLine3("Description : "+don.getDescription());
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
