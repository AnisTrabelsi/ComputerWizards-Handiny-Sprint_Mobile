/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.utils.Sauvegardes;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bengh
 */
public class SauvegardesSujets extends Form {

    public SauvegardesSujets(Forum previous, Resources res) {
        setTitle("Sujets Sauvegardés");
        setLayout(BoxLayout.y());
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        int userId = 14;
        Sauvegardes sauvegardes = new Sauvegardes();
        ArrayList<Sujet> sujetssauvegardes = sauvegardes.affichageSujetsSauv(userId);
        for (Sujet sujet : sujetssauvegardes) {
            Container sujetContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            sujetContainer.setScrollableY(true);

            MultiButton sujetButton = new MultiButton(sujet.getTitreSujet());
            sujetButton.setTextLine1(sujet.getContenuSujet());
            sujetButton.setTextLine2("Tags : " + sujet.getTags());
            sujetButton.setTextLine4("Date de création : " + formatDate(sujet.getDateCreationSujet()));

            Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
            Button deleteSauvegarde = new Button("Supprimer sauvegarde");
            buttonsContainer.addAll(deleteSauvegarde);
            deleteSauvegarde.addActionListener(evt -> {

                int idSujet = sujet.getIdSujet(); 
                System.out.println("id de sujet à supprimer" + idSujet);

                boolean suppressionReussie = sauvegardes.supprimerSujetSauvegarde(userId, idSujet);
                sauvegardes.closeDatabase();

                if (suppressionReussie) {
                    Dialog.show("Succès", "Le sujet a été supprimée des sauvegardes", "OK", null);
                    new SauvegardesSujets(previous,res).show();
                } else {
                    Dialog.show("Erreur", "La suppression de sujet a échoué", "OK", null);
                }
            });
            sujetContainer.addAll(sujetButton, buttonsContainer);
            this.add(sujetContainer);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private String formatDate(Date dateCreationSujet) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateCreationSujet);
    }

}
