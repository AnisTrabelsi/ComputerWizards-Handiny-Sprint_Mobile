package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Chauffeur;
import com.mycompany.myapp.services.ServiceChauffeur;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class AddChauffeurForm extends Form {
   private Form previous;
   
   public static final String ACCOUNT_SID = "AC6eff55ee1540f4ff7c31247296b619fb";
    public static final String AUTH_TOKEN = "cca87fe4bb3540b358e0d835b6c0582e";


    public AddChauffeurForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new product");
             
        TextField tfCIN = new TextField("", "CIN");
        TextField tfNom = new TextField("", "Nom");
        TextField tfAdresse = new TextField("", "Addresse");
        
       
        Button addAnnonceButton = new Button("Add chauffeur");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfCIN.getText().isEmpty()  || tfNom.getText().isEmpty() || tfAdresse.getText().isEmpty()  ) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Chauffeur chauffeur = new Chauffeur(tfCIN.getText(),tfNom.getText(), tfAdresse.getText());
                        if (ServiceChauffeur.getInstance().addAnnoncef(chauffeur)) {
                            Dialog.show("Success", "Announcement added", new Command("OK"));
                                                        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21625255556"),
                new com.twilio.type.PhoneNumber("+12706151770"),
                "Le chauffeur "+ tfNom.getText()+" est ajouté")
            .create();

        System.out.println(message.getSid());
                            
                            
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfCIN, tfNom, tfAdresse, addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      
