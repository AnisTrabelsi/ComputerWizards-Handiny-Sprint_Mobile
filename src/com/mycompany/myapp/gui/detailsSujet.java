/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import java.util.Date;

/**
 *
 * @author bengh
 */
public class detailsSujet extends Form {

    public detailsSujet(Sujet sujet, AllSujets aThis, Resources res) {
        try {
            if (sujet.getId_categorie() == 24) {
                ImageViewer img1 = new ImageViewer(res.getImage("infra.jpg"));
                add(img1);
            } else if (sujet.getId_categorie() == 27) {
                ImageViewer img1 = new ImageViewer(res.getImage("don.jpg"));
                add(img1);
            } else {
                ImageViewer img1 = new ImageViewer(res.getImage("new.jpg"));
                add(img1);
            }

            revalidate();
        } catch (Exception e) {
            e.printStackTrace();
            Label errorLabel = new Label("Error loading image");
            add(errorLabel);
            revalidate();
        }
        setTitle("Détails du sujet");

        // Create labels to display the subject information
        Label titreLabel = new Label("Titre: " + sujet.getTitreSujet());
        Label contenuLabel = new Label("Contenu: " + sujet.getContenuSujet());
        Label tagsLabel = new Label("Tags: " + sujet.getTags());
        Label dateCreationLabel = new Label("Date de création: " + formatDate(sujet.getDateCreationSujet()));

        // Add the labels to the form
        add(titreLabel);
        add(contenuLabel);
        add(tagsLabel);
        add(dateCreationLabel);

        // Add a command to go back to the previous form
        //setBackCommand(back -> aThis.show());
        // Show the form
        show();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            aThis.showBack();
        });
    }

    detailsSujet() {
    }

    private String formatDate(Date dateCreationSujet) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateCreationSujet);
    }

}
