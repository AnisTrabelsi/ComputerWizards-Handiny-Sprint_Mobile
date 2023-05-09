package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.itextpdf.text.DocumentException;
import com.mycompany.myapp.entities.Covoiturage;
import com.mycompany.myapp.services.ServiceCovoiturage;
import java.io.IOException;
import java.util.ArrayList;

public class ListTasksForm extends Form {
    private TextField searchField;
    
    public ListTasksForm(Form previous) {
        setTitle("List covoiturages");
        setLayout(BoxLayout.y());

        ArrayList<Covoiturage> covs = ServiceCovoiturage.getInstance().getAllTasks();
        for (Covoiturage t : covs) {
            addElement(t);
        }

        searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            ListTasksForm results = new ListTasksForm(this);
            results.filter(query);
            results.show();
        });
        Container searchContainer = BoxLayout.encloseY(searchField, searchButton);
        add(searchContainer);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(Covoiturage covoiturage) {
        Label departLabel = new Label("Depart: " + covoiturage.getDepart());
        Label destinationLabel = new Label("Destination: " + covoiturage.getDestination());
        Label prixLabel = new Label("Prix: " + covoiturage.getPrix());
        Label nbrplaceLabel = new Label("Nbr Place: " + covoiturage.getNbrplace());
        Label dateLabel = new Label("Date: " + covoiturage.getDate_covoiturage());
        Label telephone = new Label("telephone: " + covoiturage.getTelephone());
        Label name = new Label("name: " + covoiturage.getNom());
        Label latitude = new Label("latitude: " + covoiturage.getLatitude());
        Label Longitude = new Label("Longitude: " + covoiturage.getLongitude());
        
        Button Fact = new Button("FACTURE");
 Fact.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, Fact.getUnselectedStyle()));
       Fact.addActionListener((evv)-> {
           
           if (Dialog.show("Confirmation", "Voulez-vous obtenir detail de l'evenement en forme pdf  ?", "Oui", "Non")){
       
               try {
                   ServiceCovoiturage.getInstance().recupererpdf(covoiturage);
               } catch (DocumentException ex) {
            //       Logger.getLogger(AfficherLivraison.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
             //      Logger.getLogger(AfficherLivraison.class.getName()).log(Level.SEVERE, null, ex);
               }
               Dialog.show("PDF "
                       , "Enregistré avec succés", "", "OK");
            
      }
       });
add(Fact);
        
        
        
        Button btnUpdate = new Button("Update");
        btnUpdate.addPointerPressedListener(e -> {
            new UpdateTaskForm(this,covoiturage).show();
        });

        Container covoiturageContainer = BoxLayout.encloseY(
                departLabel,
                destinationLabel,
                prixLabel,
                nbrplaceLabel,
                dateLabel,
                telephone,
                name, btnUpdate
        );


        add(covoiturageContainer);
    }

    public void filter(String query) {
        removeAll();
        setLayout(BoxLayout.y());

        ArrayList<Covoiturage> covs = ServiceCovoiturage.getInstance().searchTask(query);
        for (Covoiturage t : covs) {
            addElement(t);
        }
    }
}
