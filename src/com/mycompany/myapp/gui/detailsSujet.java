/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;
import java.util.ArrayList;
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
        ArrayList<Commentaire> comments = ServiceSujet.getInstance().showCommentairesBySujet(sujet);
        if (!comments.isEmpty()) {
            displayComments(sujet);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            aThis.showBack();
        });
        show();

    }

    detailsSujet() {
    }

    private String formatDate(Date dateCreationSujet) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateCreationSujet);
    }

    private void displayComments(Sujet sujet) {
        // Fetch the comments for the subject from the service
        ArrayList<Commentaire> comments = ServiceSujet.getInstance().showCommentairesBySujet(sujet);
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        Label lb=new Label("Commentaires :");
        add(lb);
        // Create labels to display the comments
        for (Commentaire comment : comments) {
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            String pieceJointe = comment.getPiecejointe();
            System.out.println("Piece jointe: " + pieceJointe); // Debug output
            Image i = URLImage.createToStorage(placeholder, pieceJointe, "C:\\xampp8.1\\htdocs\\mobile");
            MultiButton sp = new MultiButton(pieceJointe);
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Contenu : " + comment.getContenuCommentaire());
            list.add(sp);
        }

        // Add the list container to the form
        this.add(list);

        // Refresh the form to display the comments
        revalidate();
    }

}
