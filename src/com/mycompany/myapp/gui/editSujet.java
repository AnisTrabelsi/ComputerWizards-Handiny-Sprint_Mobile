/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;

/**
 *
 * @author bengh
 */
public class editSujet extends Form {

    public editSujet(Sujet sujet, listSujet previous, Resources res) {
        setTitle("Modifier sujet");
        setLayout(BoxLayout.y());
        System.out.println(sujet.toString());
        Label labelTitre = new Label("Titre de sujet :");
        TextField titreSujet = new TextField(sujet.getTitreSujet(), "Titre de sujet");
        Label labelContenu = new Label("Contenu de sujet :");
        TextField contenuSujet = new TextField(sujet.getContenuSujet(), "Contenu de sujet");
        Label labelCategorie = new Label("Catégorie :");
        ComboBox<String> categorieSujet = new ComboBox<>("News", "Recrutement", "Dons");
        switch (sujet.getId_categorie()) {
            case 1:
                categorieSujet.setSelectedItem("News");
                break;
            case 2:
                categorieSujet.setSelectedItem("Recrutement");
                break;
            case 3:
                categorieSujet.setSelectedItem("Dons");
                break;
            default:
                categorieSujet.setSelectedItem("News");
        }
        Container etatContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        RadioButton etatOuvert = new RadioButton("Ouvert");
        RadioButton etatFerme = new RadioButton("Fermé");
        RadioButton etatBloque = new RadioButton("Bloqué");

        ButtonGroup etatGroup = new ButtonGroup(); // Créer un groupe de boutons radio
        etatGroup.add(etatOuvert);
        etatGroup.add(etatFerme);
        etatGroup.add(etatBloque);

        etatContainer.addAll(new Label("État :"), etatOuvert, etatFerme, etatBloque);

        switch (sujet.getEtat()) {
            case "Ouvert":
                etatOuvert.setSelected(true);
                break;
            case "Fermé":
                etatFerme.setSelected(true);
                break;
            case "Bloqué":
                etatBloque.setSelected(true);
                break;
            default:
                etatOuvert.setSelected(true);
        }
        Label labelTags = new Label("Hachtags :");
        TextField tags = new TextField(sujet.getTags(), "Tags #");
        ButtonGroup etatButtonGroup = new ButtonGroup();
        etatButtonGroup.add(etatOuvert);
        etatButtonGroup.add(etatFerme);
        etatButtonGroup.add(etatBloque);

        Button btnModifier = new Button("Modifier");
        btnModifier.addActionListener(evt -> {
            if ((titreSujet.getText().length() == 0) || (contenuSujet.getText().length() == 0)) {
                Dialog.show("Alert", "Veuillez remplir tous les champs ", "ok", null);
            } else {
                // Get the selected etat from the radio buttons
                if (!Character.isUpperCase(titreSujet.getText().charAt(0))) {
                    Dialog.show("Alert", "Le titre doit commencer par une majuscule", "OK", null);
                    return; // Arrêter l'exécution de l'action si la validation échoue
                }
                if (sujet.getContenuSujet().equals(contenuSujet.getText()) && (sujet.getTitreSujet().equals(titreSujet.getText()))) {
                    boolean returnToList = Dialog.show(
                            "Aucune modification",
                            "Aucune modification n'a été apportée. Voulez-vous revenir à la liste de sujets ?",
                            "Oui",
                            "Non"
                    );

                    if (returnToList) {
                        previous.refreshList(res);
                        previous.showBack();
                    }
                }
                else{
                
                String etat = "";
                if (etatOuvert.isSelected()) {
                    etat = etatOuvert.getText();
                } else if (etatFerme.isSelected()) {
                    etat = etatFerme.getText();
                } else if (etatBloque.isSelected()) {
                    etat = etatBloque.getText();
                }
                int cat = 0;
                if (null != categorieSujet.getSelectedItem()) {
                    switch (categorieSujet.getSelectedItem()) {
                        case "News":
                            cat = 1;
                            break;
                        case "Recrutement":
                            cat = 2;
                            break;
                        case "Dons":
                            cat = 3;
                            break;
                        default:
                            break;
                    }
                }
                sujet.setTitreSujet(titreSujet.getText());
                sujet.setContenuSujet(contenuSujet.getText());
                sujet.setEtat(etat);
                sujet.setTags(tags.getText());
                sujet.setId_categorie(cat);
                System.out.println(sujet.toString());
                ServiceSujet.getInstance().modifierSujet(sujet);
                Dialog.show("Alert", "Le sujet a été modifié avec succès", "ok", null);
                previous.refreshList(res);
                previous.showBack();}
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        addAll(labelTitre, titreSujet, labelContenu, contenuSujet, labelCategorie, categorieSujet, etatContainer, labelTags, tags, btnModifier);
    }

    editSujet(listSujet aThis, Resources res) {
    }

}
