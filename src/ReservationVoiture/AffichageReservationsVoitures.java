/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationVoiture;

import Voiture.ModifierVoiture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.l10n.ParseException;
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
import com.mycompany.services.ServiceReservationVoiture;
import com.mycompany.services.ServiceVoiture;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class AffichageReservationsVoitures extends Form {
  private Form F1,current;
    public AffichageReservationsVoitures (Form previous){
        setTitle("Liste des réservations de voitures");
        setLayout(BoxLayout.y());
        ArrayList<com.mycomany.entities.Reservation_voiture> voitures = ServiceReservationVoiture.getInstance().AffichageReservationsVoiture();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.Reservation_voiture v : voitures) {
            //System.out.println(don.getType());
            
            
            
            
           // Extraire la partie de la date de début
String debutDateString = v.getString_debut_reservation();
String debutDate = debutDateString.substring(0, 10);

// Extraire la partie de la date de fin
String finDateString = v.getString_fin_reservation();
String finDate = finDateString.substring(0, 10);

// Créer les labels avec les dates formatées
Label l1 = new Label("Date de début : " + debutDate);
Label l2 = new Label("Date de fin : " + finDate);
            Label l3=new Label("Description : " +v.getDescription_reservation());
            Label l4=new Label("Date de demande de réservation : " +v.getDescription_reservation());
            Label l5=new Label("état de réservation : " +v.getEtat_demande_reservation());
            

            
            
       Container buttonsContainer = new Container(BoxLayout.x());
        
         Button modifButton = new Button("Modifier");
         Button suppButton = new Button("Supprimer");
         
          // Gérer les événements de clic pour les boutons "Modifier" et "Supprimer"
     modifButton.addActionListener(evt -> {
    try {
        new ModifierReservationVoiture(this, v).show();
    } catch (ParseException e) {
        e.printStackTrace(); // Afficher la trace de l'erreur (optionnel)
        // Gérer l'erreur de parsing ici
    }
});
     
       suppButton.addActionListener(evt -> {
    if (Dialog.show("Confirmation", "Vous voulez vraiment supprimer cette réservations ?", "Oui", "Non")) {
        if (ServiceReservationVoiture.getInstance().deleteReservationVoiture(v.getId_reservation_voiture())) {
            Dialog.show("Success", "Cette voiture a été supprimée avec succès", null, "OK");
           
            new AffichageReservationsVoitures(previous).show();
        }
    }
});

   
        buttonsContainer.addAll(modifButton,suppButton);
        

        

       
            //   sp.setTextLine3("Description : "+don.getDescription());
            list.addAll(l1,l2,l3,l4,l5);
            list.add(buttonsContainer);

              
            
           
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   

}

