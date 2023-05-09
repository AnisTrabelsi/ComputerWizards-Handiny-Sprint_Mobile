/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.components.ImageViewer;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Covoiturage;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceCovoiturage;
import com.mycompany.myapp.services.ServiceTask;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Oubeid Mezni
 */
public class deleteTaskForm extends Form {
    
    public deleteTaskForm(Form previous) {
        setTitle("Delete tasks");
        setLayout(BoxLayout.y());

        ArrayList<Covoiturage> covs = ServiceCovoiturage.getInstance().getAllTasks();
        for (Covoiturage t : covs) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   

    private void addElement(Covoiturage covoiturage) {
         Label departLabel = new Label("depart: " + covoiturage.getDepart());
        Label destinationLabel = new Label("destination: " + covoiturage.getDestination());
        Label prixLabel = new Label("Prix: " + covoiturage.getPrix());
        Label nbrplaceLabel = new Label("Nbr Place: " + covoiturage.getNbrplace());
        Label dateLabel = new Label("Date: " + covoiturage.getDate_covoiturage());
        Label telephone = new Label("telephone: " + covoiturage.getTelephone());
        Label name = new Label("name: " + covoiturage.getNom());
               // Label latitude = new Label("telephone: " + covoiturage.getLatitude());
                // Label longitude = new Label("longitude: " + covoiturage.getLongitude());
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(e -> {
        boolean success = ServiceCovoiturage.getInstance().deleteTask(covoiturage.getId());
        if (success) {
            // Remove task from UI
            Component taskComponent = deleteButton.getParent();
            taskComponent.remove();
            taskComponent.setHidden(true);
        }
    });
        Container taskContainer = BoxLayout.encloseY(
                 departLabel,
                destinationLabel,
                prixLabel,
                nbrplaceLabel,
                dateLabel,
                telephone,
                name, 
                deleteButton
        );

        add(taskContainer);
    }

    
}
