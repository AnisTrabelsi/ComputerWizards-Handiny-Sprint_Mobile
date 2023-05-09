/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReservationVoiture;

import Don.ShowdonCrud;
import com.mycompany.services.ServiceDon;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.mycomany.entities.Reservation_voiture;
import com.mycomany.entities.Voiture;
import com.mycompany.services.ServiceReservationVoiture;
import com.mycompany.services.ServiceVoiture;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
 import java.text.ParseException;

import java.util.Date;
/**
 *
 * @author ASUS
 */
public class ModifierReservationVoiture extends Form {

    public ModifierReservationVoiture(Form previous,Reservation_voiture r) throws com.codename1.l10n.ParseException {
        setTitle("Modification d'une réservation de voiture");
        setLayout(BoxLayout.y());
        
   
      TextField tfdesc = new TextField(r.getDescription_reservation(), "Description");
      tfdesc.getStyle().setFgColor(154245);
      
     
    

// ...

String dd = r.getString_debut_reservation();
String df = r.getString_fin_reservation();

// Formatter pour convertir les chaînes en objets Date
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date dateDebut, dateFin;


    dateDebut = dateFormat.parse(dd);



    dateFin = dateFormat.parse(df);


Picker tfDateDeb = new Picker();
tfDateDeb.getStyle().setFgColor(154245);
tfDateDeb.setType(Display.PICKER_TYPE_DATE);
tfDateDeb.setDate(dateDebut);

Picker tfDateFin = new Picker();
tfDateFin.getStyle().setFgColor(154245);
tfDateFin.setType(Display.PICKER_TYPE_DATE);
tfDateFin.setDate(dateFin);

// ...

       

// Format the date as "yyyy-MM-dd"

String formattedDate = dateFormat.format(tfDateDeb.getDate());
String formattedDateFin = dateFormat.format(tfDateFin.getDate());
String formattedDatedem = dateFormat.format(new Date());
Button btnValider = new Button("Modifier");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (  tfdesc.getText().isEmpty() || tfDateDeb.getText().isEmpty() || tfDateFin.getText().isEmpty()) {
    Dialog.show("Alerte", "Merci de remplir tous les champs", new Command("OK"));
} else {
    try {
          r.setId_reservation_voiture((int) r.getId_reservation_voiture());
          r.setId_user(r.getId_user());
          r.setId_voiture(r.getId_voiture());
          r.setDescription_reservation(tfdesc.getText());
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String formattedDate = dateFormat.format(tfDateDeb.getDate());
          String formattedDateFin = dateFormat.format(tfDateFin.getDate());
          r.setString_debut_reservation(formattedDate);
          r.setString_fin_reservation(formattedDateFin);
          r.setEtat_demande_reservation(r.getEtat_demande_reservation());
          r.setString_demande_reservation(r.getString_demande_reservation());
      
       
        // Tous les champs sont remplis et les contraintes sont respectées, vous pouvez récupérer les valeurs ici
        com.mycomany.entities.Reservation_voiture v = new Reservation_voiture(  formattedDate,formattedDateFin, tfdesc.getText());
        
   
                       if (ServiceReservationVoiture.getInstance().ModifierReservationVoiture(v)) {
            Dialog.show("Success", "Réservation modifiée avec succès", new Command("OK"));
            //new AffichageVoitures(new com.mycompany.myapp.HomeHandiny()).show();
        } else {
            Dialog.show("ERROR", "Erreur du serveur", new Command("OK"));
        }
    } catch (NumberFormatException e) {
        Dialog.show("ERROR", "Le kilométrage et le prix de location doivent être des nombres", new Command("OK"));
    }

                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        
        addAll(tfDateDeb, tfDateFin,tfdesc, btnValider);
    }

  

}
