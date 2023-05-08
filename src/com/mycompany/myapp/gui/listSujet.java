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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;
import java.util.ArrayList;
import java.util.Date;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author bengh
 */
public class listSujet extends Form {

    private TextField searchField;

    public listSujet(Forum previous, Resources res) {
        setTitle("Mes sujets");
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

        ArrayList<Sujet> sujets = ServiceSujet.getInstance().listSujet();

        for (Sujet sujet : sujets) {
            Container sujetContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            sujetContainer.setScrollableY(true);

            MultiButton sujetButton = new MultiButton(sujet.getTitreSujet());
            sujetButton.setTextLine1(sujet.getContenuSujet());
            sujetButton.setTextLine2("Tags : " + sujet.getTags());
            sujetButton.setTextLine4("Date de création : " + formatDate(sujet.getDateCreationSujet()));

            Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");
            Button detailsButton = new Button("Afficher Détails");
            editButton.addActionListener(e -> new editSujet(sujet, this, res).show());
            deleteButton.addActionListener(e -> new deleteSujet(sujet, this, res).show());
            buttonsContainer.addAll(editButton, deleteButton);

            sujetContainer.addAll(sujetButton, buttonsContainer);
            this.add(sujetContainer);
        }
        searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            ArrayList<Sujet> filteredSujets = filterSujets(sujets, searchText);
            refreshList(filteredSujets, res);
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refreshList(ArrayList<Sujet> sujets, Resources res) {
        this.removeAll(); // Supprimer tous les composants de la Form actuelle

        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);

        for (com.mycompany.myapp.entities.Sujet sujet : sujets) {
            MultiButton sp = new MultiButton(sujet.getTitreSujet());
            sp.setTextLine1("Contenu de sujet : " + sujet.getContenuSujet());
            sp.setTextLine2("tags : " + sujet.getTags());
            sp.setTextLine4("Date de création : " + sujet.getDateCreationSujet());

            // Ajouter les boutons "Edit" et "Delete"
            Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            editButton.addActionListener(l5 -> new editSujet(sujet, this, res).show());
            deleteButton.addActionListener(l4 -> new deleteSujet(sujet, this, res).show());

            buttonsContainer.addAll(editButton, deleteButton);

            // Ajouter le conteneur des boutons et le MultiButton à la liste
            list.add(buttonsContainer);
            list.add(sp);
        }

        this.add(list);
        this.revalidate(); // Revalider la Form pour afficher les nouveaux composants ajoutés
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

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public void refreshList(Resources res) {
        this.removeAll(); // Supprimer tous les composants de la Form actuelle

        ArrayList<com.mycompany.myapp.entities.Sujet> sujets = ServiceSujet.getInstance().listSujet();
        Container list = new Container(BoxLayout.y());

        for (com.mycompany.myapp.entities.Sujet sujet : sujets) {
            MultiButton sp = new MultiButton(sujet.getTitreSujet());
            sp.setTextLine1("Contenu de sujet : " + sujet.getContenuSujet());
            sp.setTextLine2("tags : " + sujet.getTags());
            sp.setTextLine4("Date de création : " + sujet.getDateCreationSujet());

            // Ajouter les boutons "Edit" et "Delete"
            Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
            Button editButton = new Button("Edit");
            Button deleteButton = new Button("Delete");

            editButton.addActionListener(l5 -> new editSujet(sujet, this, res).show());
            deleteButton.addActionListener(l4 -> new deleteSujet(sujet, this, res).show());

            buttonsContainer.addAll(editButton, deleteButton);

            // Ajouter le conteneur des boutons et le MultiButton à la liste
            list.add(buttonsContainer);
            list.add(sp);
        }
        list.scrollComponentToVisible(list);
        this.add(list);
        list.setScrollableY(true);
        this.revalidate(); // Revalider la Form pour afficher les nouveaux composants ajoutés
    }

}
