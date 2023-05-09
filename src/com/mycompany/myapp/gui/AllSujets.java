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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;
import com.mycompany.myapp.utils.Sauvegardes;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bengh
 */
public class AllSujets extends Form {

    private TextField searchField;

    public AllSujets(Forum previous, Resources res) {
        setTitle("Accueil");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Style searchStyle = new Style();
        searchStyle.setBorder(RoundRectBorder.create().cornerRadius(2));
        searchStyle.setBgColor(0xEFEFEF); // Couleur de fond
        searchStyle.setFgColor(0x000000); // Couleur du texte

        int searchFieldMaxWidth = 200; // Largeur maximale souhaitée pour la barre de recherche

        searchField = new TextField();
        searchField.setHint("Rechercher...");
        searchField.setUIID("SearchTextField"); // Appliquer un UIID personnalisé pour éviter les styles par défaut
        searchField.getAllStyles().merge(searchStyle); // Fusionner le style personnalisé avec les styles existants
        searchField.setMaxSize(searchFieldMaxWidth); // Définir la largeur maximale

        add(searchField);
        ArrayList<Sujet> sujets = ServiceSujet.getInstance().AllSujet();
        searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            ArrayList<Sujet> filteredSujets = filterSujets(sujets, searchText);
            refreshList(filteredSujets, res);
        });

        for (Sujet sujet : sujets) {
            Container sujetContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            sujetContainer.setScrollableY(true);

            MultiButton sujetButton = new MultiButton(sujet.getTitreSujet());
            sujetButton.setTextLine1(sujet.getContenuSujet());
            sujetButton.setTextLine2("Tags : " + sujet.getTags());
            sujetButton.setTextLine4("Date de création : " + formatDate(sujet.getDateCreationSujet()));

            Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
            Button detailsButton = new Button("Afficher Détails");
            Button saveButton = new Button();
            Button commentButton = new Button("Commenter");
            saveButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SAVE, saveButton.getUnselectedStyle()));
            detailsButton.addActionListener(e -> new detailsSujet(sujet, this, res).show());
            Sauvegardes s = new Sauvegardes();
            saveButton.addActionListener(e -> s.addSauvegarde(14, sujet.getIdSujet()));
            commentButton.addActionListener(e-> new AjoutCommentaire(sujet, this, res).show());
            buttonsContainer.addAll(commentButton, detailsButton, saveButton);

            sujetContainer.addAll(sujetButton, buttonsContainer);
            this.add(sujetContainer);
        }
        getToolbar().addCommandToOverflowMenu("Back", null, ev -> previous.show());
        getToolbar().addCommandToOverflowMenu("Exit", null,
                ev -> Display.getInstance().exitApplication());
    }

    AllSujets() {
    }

    private String formatDate(Date dateCreationSujet) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateCreationSujet);
    }

    private ArrayList<Sujet> filterSujets(ArrayList<Sujet> sujets, String searchText) {
        ArrayList<Sujet> filteredSujets = new ArrayList<>();

        if (searchText.isEmpty()) {
            // Si le champ de recherche est vide, retourner la liste complète des sujets
            return sujets;
        }

        for (Sujet sujet : sujets) {
            String titreSujet = sujet.getTitreSujet().toLowerCase();
            String contenuSujet = sujet.getContenuSujet().toLowerCase();
            if (titreSujet.contains(searchText.toLowerCase()) || contenuSujet.contains(searchText.toLowerCase())) {
                filteredSujets.add(sujet);
            }
        }
        return filteredSujets;
    }

    private void refreshList(ArrayList<Sujet> sujets, Resources res) {
        this.removeAll(); // Supprimer tous les composants de la Form actuelle

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);

        for (com.mycompany.myapp.entities.Sujet sujet : sujets) {
            MultiButton sp = new MultiButton(sujet.getTitreSujet());
            sp.setTextLine1("Contenu de sujet : " + sujet.getContenuSujet());
            sp.setTextLine2("tags : " + sujet.getTags());
            sp.setTextLine4("Date de création : " + sujet.getDateCreationSujet());

            list.add(sp);
        }

        this.add(list);
        this.revalidate(); // Revalider la Form pour afficher les nouveaux composants ajoutés
    }
    
}
