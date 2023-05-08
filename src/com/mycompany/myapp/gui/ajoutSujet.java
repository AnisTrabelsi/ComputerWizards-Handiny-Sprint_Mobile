/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;
import java.util.Date;

/**
 *
 * @author bengh
 */
public class ajoutSujet extends Form {

    public ajoutSujet(Forum aThis, Resources res) {
        setTitle("Ajout sujet");
        setLayout(BoxLayout.y());
        Label labelTitre = new Label("Titre de sujet :");
        TextField titreSujet = new TextField("", "Titre de sujet");
        Label labelContenu = new Label("Contenu de sujet :");
        TextField contenuSujet = new TextField("", "Contenu de sujet");
        Label labelCategorie = new Label("Catégorie :");

        ComboBox<String> categorieSujet = new ComboBox<>("News", "Recrutement", "Dons");
        Label labelTags = new Label("Hachtags :");
        TextField tags = new TextField("", "Tags #");

        Container etatContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        RadioButton etatOuvert = new RadioButton("Ouvert");
        RadioButton etatFerme = new RadioButton("Fermé");
        RadioButton etatBloque = new RadioButton("Bloqué");

        ButtonGroup etatGroup = new ButtonGroup(); // Créer un groupe de boutons radio
        etatGroup.add(etatOuvert);
        etatGroup.add(etatFerme);
        etatGroup.add(etatBloque);

        etatContainer.addAll(new Label("État :"), etatOuvert, etatFerme, etatBloque);

        Button btnadd = new Button("Ajouter");

        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (titreSujet.getText().isEmpty() || contenuSujet.getText().isEmpty()) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", "OK", null);
                } else {
                    // Vérifier si le titre commence par une majuscule
                    if (!Character.isUpperCase(titreSujet.getText().charAt(0))) {
                        Dialog.show("Alert", "Le titre doit commencer par une majuscule", "OK", null);
                        return; // Arrêter l'exécution de l'action si la validation échoue
                    }
                    // Get the selected etat from the radio buttons
                    String etat = "";
                    if (etatOuvert.isSelected()) {
                        etat = etatOuvert.getText();
                    } else if (etatFerme.isSelected()) {
                        etat = etatFerme.getText();
                    } else if (etatBloque.isSelected()) {
                        etat = etatBloque.getText();
                    }
                    int cat = 0;
                    if (categorieSujet.getSelectedItem() == "News") {
                        cat = 26;
                    } else if (categorieSujet.getSelectedItem() == "Recrutement") {
                        cat = 24;
                    } else if (categorieSujet.getSelectedItem() == "Dons") {
                        cat = 27;
                    }
                    Date date = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(date);
                    System.out.println(formattedDate);
                    Date datef = null;
                    try {
                        datef = dateFormat.parse(formattedDate);
                    } catch (ParseException ex) {
                        System.out.println(ex);
                    }
                    Sujet sujet = new Sujet(
                            titreSujet.getText(),
                            contenuSujet.getText(),
                            etat,
                            tags.getText(),
                            datef,
                            0,
                            cat,
                            14
                    );

                    ServiceSujet.getInstance().ajoutSujet(sujet);
                    Dialog.show("Alert", "Le sujet a été ajouté avec succès", "ok", null);
                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            aThis.showBack();
        });

        addAll(labelTitre, titreSujet, labelContenu, contenuSujet, labelCategorie, categorieSujet, etatContainer, labelTags, tags, btnadd);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev -> aThis.show());
    }

}
