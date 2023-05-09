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
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reservation_voiture;
import com.mycomany.entities.Voiture;
import com.mycompany.services.ServiceReservationVoiture;
import com.mycompany.services.ServiceVoiture;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author ASUS
 */
public class AjouterReservationVoiture extends Form {

    public AjouterReservationVoiture(Form previous,Voiture voit,int user_id) {
        setTitle("Ajout d'une réservation de voiture");
        setLayout(BoxLayout.y());
        
   
      TextField tfdesc = new TextField("", "Description");
      tfdesc.getStyle().setFgColor(154245);
      
     
      
        Picker tfDateDeb = new Picker();
        tfDateDeb.getStyle().setFgColor(154245);
        tfDateDeb.setType(Display.PICKER_TYPE_DATE);
        tfDateDeb.setDate(new Date());
        
         Picker tfDateFin = new Picker();
        tfDateFin.getStyle().setFgColor(154245);
        tfDateFin.setType(Display.PICKER_TYPE_DATE);
        tfDateFin.setDate(new Date());
       

// Format the date as "yyyy-MM-dd"
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = dateFormat.format(tfDateDeb.getDate());
String formattedDateFin = dateFormat.format(tfDateFin.getDate());
String formattedDatedem = dateFormat.format(new Date());
Button btnValider = new Button("Ajouter");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (  tfdesc.getText().isEmpty() || tfDateDeb.getText().isEmpty() || tfDateFin.getText().isEmpty()) {
    Dialog.show("Alerte", "Merci de remplir tous les champs", new Command("OK"));
} else {
    try {
      
       
        // Tous les champs sont remplis et les contraintes sont respectées, vous pouvez récupérer les valeurs ici
        com.mycomany.entities.Reservation_voiture v = new Reservation_voiture(  formattedDate,formattedDateFin, tfdesc.getText(), user_id,voit.getId_voiture());
        v.setEtat_demande_reservation("en cours"); // État par défaut
        v.setString_demande_reservation(formattedDatedem); // Date système
        v.setId_user(v.getId_user());
        v.setId_voiture(v.getId_voiture());
        
   
                       if (ServiceReservationVoiture.getInstance().addReservationVoiture(v)) {
                           
                            System.out.println(v);
            Dialog.show("Success", "Réservation ajoutée avec succès", new Command("OK"));
            v.setId_voiture(v.getId_voiture());
             sendMail(v,voit);
             //sendMail(v);
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
public void sendMail(com.mycomany.entities.Reservation_voiture v,com.mycomany.entities.Voiture voit) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("ok"));
            msg.setRecipients(Message.RecipientType.TO, "chaima.lotfi@esprit.tn");
            msg.setSubject("Demande de réservation de voiture ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
          /* String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
           String txt = "Bienvenue sur AppNom : Tapez ce mot de passe : "+mp+" dans le champs requis et appuiez sur confirmer";
           */
           
          // msg.setText("Monsieur,Madame \n \n"+"Votre réservation ayant l'immatriculation : " + d.getId_voiture().getImmatriculation() + "a été réservée auprès de Mr/Madame : " + d.getId_user().getNom() );

            msg.setText("Monsieur,Madame \n \n"+"Votre réservation ayant l'immatriculation : "+ voit.getImmatriculation());
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"chaima.lotfi@esen.tn","chiwawanemsa12398KK?MA.");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
    }
    
}
  


