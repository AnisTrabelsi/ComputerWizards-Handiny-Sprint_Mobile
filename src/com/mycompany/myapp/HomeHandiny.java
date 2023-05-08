/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;



import Don.Ajouterdon;
import Don.Modifierdon;
import Don.Showdon;
import Don.ShowdonCrud;
import ReservationVoiture.AffichageReservationsVoitures;
import ReservationVoiture.AjouterReservationVoiture;
import ReservationVoiture.ModifierReservationVoiture;
import Voiture.AffichageFavorisVoitures;
import Voiture.AffichageVoitures;
import Voiture.AffichageVoituresPropriètaire;
import Voiture.AjouterVoiture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Voiture;
import com.mycomany.entities.don;
import com.mycomany.utils.FavorisManager;
import java.util.List;
import javafx.scene.image.ImageView;

//
/**
 *
 * @author ASUS
 */
public class HomeHandiny extends Form{
Form current;
private Resources theme;
    public HomeHandiny() {
                current=this; //Back 
                
        add(new Label("Bienvenue sur notre application mobile Handiny"));
        setTitle("Handiny");
        setLayout(BoxLayout.y());
       EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(600, 500, 0xff0000ff), true);
String imagePath = "http://localhost/mobile/logooo.png"; // Chemin relatif de l'image dans votre projet
Image image = URLImage.createToStorage(placeholder, "image_key2", imagePath);
ImageViewer img1 = new ImageViewer(image);
add(img1);
  
   /* Button BUTaffiche= new Button("afficher don");
    Button BUTajouter= new Button("ajouter don");
    Button BUTmodifier= new Button("modifier don");
    Button BUTsupprimer= new Button("supprimer don");*/
    
    
    /*chaima lotfi*/
    Button BtnAffichageVoitures= new Button("affichage des voitures");
    Button BtnAjouterVoiture= new Button("ajoute une voiture");
    Button BtnFavVoiture= new Button("Mes favoris");
    Button BtnSupprimerVoiture=new Button("supprimer une voiture");
    Button BtnAffichageVoitProp=new Button("Mes voitures");
    Button BtnAffichageALLFavoris=new Button("Favoris");
    Button BtnAjouterRser=new Button("Ajouter une réservation");
    Button BtnAffichageReservations=new Button("Liste de mes réservations");

/*
BUTaffiche.addActionListener((evt) -> new Showdon(current).show());
BUTajouter.addActionListener((evt) -> new Ajouterdon(current).show());
/*don d=new don();
d.setId_don(402);
BUTmodifier.addActionListener((evt) -> new Modifierdon(current,d).show());
BUTsupprimer.addActionListener((evt) -> new ShowdonCrud(current).show());

*/
/*chaima lotfi*/
BtnAffichageVoitures.addActionListener((evt) -> new AffichageVoitures(current).show());
BtnAjouterVoiture.addActionListener((evt) -> new AjouterVoiture(current).show());
BtnFavVoiture.addActionListener((evt) -> new AffichageFavorisVoitures(current).show()); 
BtnAffichageVoitProp.addActionListener((evt) -> new AffichageVoituresPropriètaire(current).show()); 
BtnAffichageReservations.addActionListener((evt) -> new AffichageReservationsVoitures(current).show()); 




 BtnAffichageALLFavoris.addActionListener((evt) -> {
    FavorisManager favorisManager = new FavorisManager();
    favorisManager.affichageFavoris();
    favorisManager.closeDatabase();
});





        addAll(BtnAffichageVoitures,BtnAjouterVoiture,BtnFavVoiture,BtnAffichageALLFavoris,BtnAffichageVoitProp,BtnAjouterRser,BtnAffichageReservations);
    
        
    }
    
   

     
}
