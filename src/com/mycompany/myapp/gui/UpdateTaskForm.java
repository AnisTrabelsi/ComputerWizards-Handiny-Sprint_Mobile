package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Covoiturage;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceCovoiturage;
import com.mycompany.myapp.services.ServiceTask;

import java.util.ArrayList;

public class UpdateTaskForm extends Form {

    public UpdateTaskForm(Form previous, Covoiturage t) {
        setTitle("update");
        setLayout(BoxLayout.y());
  Label departLabels = new Label("Depart: " );
        TextField departLabel = new TextField(t.getDepart(), "depart");
        TextField destinationLabel = new TextField(t.getDestination(), "destination");
         TextField dateLabel = new TextField(t.getDate_covoiturage(), "date_covoiturage");
        TextField telephonelab = new TextField(t.getTelephone(), "telephone");
        TextField namelab = new TextField(t.getNom(), "name");
           //     TextField userlab = new TextField(Integer.toString(t.getUser()), "user");
                TextField tfnbrplace = new TextField(Integer.toString(t.getNbrplace()), "nbrplace");
                TextField tfprix = new TextField(Integer.toString(t.getPrix()), "prix");
           //     TextField tfidutilisateur = new TextField(Integer.toString(t.getId_utilisateur()), "idutilisateur");
          //      TextField latitudelab = new TextField(Float.toString(t.getLatitude()), "latitude");
            //    TextField longitudelab = new TextField(Float.toString(t.getLongitude()), "longitude");

  
      
       

         
        
        
        
        Button btnUpdate = new Button("Update");
        btnUpdate.addPointerPressedListener(e -> {
            t.setDepart(departLabel.getText());
            t.setDestination(destinationLabel.getText());
                        t.setDate_covoiturage(dateLabel.getText());
            t.setTelephone(telephonelab.getText());
                        t.setNom(namelab.getText());
        //    int user = Integer.parseInt(userlab.getText());
            int nbrplace = Integer.parseInt(tfnbrplace.getText());
         //   int id_utilisateur = Integer.parseInt(tfidutilisateur.getText());
            int prix = Integer.parseInt(tfprix.getText());
       //     t.setUser(user);
                        t.setNbrplace(nbrplace);
            t.setPrix(prix);

     //     t.setId_utilisateur(id_utilisateur);
            
            boolean success = ServiceCovoiturage.getInstance().updateTask(t);
            if (success) {
                // Remove task from UI
                previous.showBack();
            }

        }
        );
        addAll(departLabel, destinationLabel, dateLabel, telephonelab, namelab , tfnbrplace , tfprix   ,  btnUpdate);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
 
    
    
    
}
