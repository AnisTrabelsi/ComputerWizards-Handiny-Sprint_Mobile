

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

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
import com.mycomany.entities.don;
import Don.FavorisManager;
import com.mycompany.services.ServiceDon;

import java.util.List;

public class affichageFavorisDons extends Form {
     private Form F1,current;

    public affichageFavorisDons(Form previous) {
        setTitle("Dons favorits");
        setLayout(BoxLayout.y());
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        int userId = 30; // Récupérer l'ID de l'utilisateur connecté (à adapter selon votre logique d'authentification)
        FavorisManager favorisManager = new FavorisManager();
        List<com.mycomany.entities.don> donsFavorites = favorisManager.affichageFavorisDons(userId);
        //favorisManager.closeDatabase();

        DefaultListModel model = new DefaultListModel();

        for (com.mycomany.entities.don v : donsFavorites) {
           EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, v.getImage_don(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + v.getImage_don());
            MultiButton sp = new MultiButton(v.getType());
            sp.setIcon(i.fill(200, 200));
          sp.setTextLine1("Type : " + v.getType());
            sp.setTextLine2("Description : " + v.getDescription());
            sp.setTextLine4("Date d'ajout : " + v.getDate_ajout());

            
             
            
            
       Container buttonsContainer = new Container(BoxLayout.x());
        
        Button supprimerButton = new Button("Supprimer");
        Button detailButton = new Button("voir Détails");
        
    

supprimerButton.addActionListener(evt -> {
    
    
    int donId = v.getId_don(); // Récupérer l'ID de la don à supprimer
    System.out.println("id de don à supprimer"+donId);
   
    boolean suppressionReussie = favorisManager.supprimerDonFavori(userId, donId);
    favorisManager.closeDatabase();
    
    if (suppressionReussie) {
        Dialog.show("Succès", "La don a été supprimée des favoris", "OK", null);
         new affichageFavorisDons(previous).show();
    } else {
        Dialog.show("Erreur", "La suppression de la don a échoué", "OK", null);
    }
});

         
        buttonsContainer.addAll( supprimerButton,detailButton);
         list.add(sp);
         list.add(buttonsContainer);

               F1 = new Form("", BoxLayout.y());
            detailButton.addActionListener((evt) -> {
                
                   
                           
                                            
            Label imat = new Label("Immatriculation :" + v.getType());
           
            Label lbEm = new Label("Marque :" + v.getType());
            Label lbdate = new Label("Modèle :" + v.getType());
            Label carb = new Label("Carburant :" + v.getType());
             Label kilom = new Label("Immatriculation :" + v.getType());
            Label boite = new Label("Boite de vitesse :" + v.getType());
             Label date = new Label("Date de validation technique :" + v.getType());
            Label desc = new Label("Description :" + v.getDescription());
           Container cnt1 = new Container(BoxLayout.y());
            F1.getToolbar().addCommandToLeftBar("Retour", null, ev -> new affichageFavorisDons(current).show());
              cnt1.addAll(lbEm,lbdate,carb,boite,date,desc,imat,kilom);

        Container cnt = new Container(BoxLayout.y());
        //ImageViewer imgview = new ImageViewer(i);
         MultiButton sp2 = new MultiButton(v.getType());
            sp2.setIcon(i.fill(500, 500));
       cnt.addAll(sp2,cnt1);
            
            F1.addAll(cnt);
            System.out.println(v);
            F1.show();
            F1.getToolbar().addCommandToLeftBar("back", null, ev -> new affichageFavorisDons(current).show());
                                    
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




    
    



