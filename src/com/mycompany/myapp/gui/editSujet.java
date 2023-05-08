/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
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
public class editSujet extends Form{

    public editSujet(Sujet sujet, listSujet previous, Resources res) {
        setTitle("Modifier sujet");
        setLayout(BoxLayout.y());
        System.out.println(sujet.toString());
        TextField titreSujet = new TextField(sujet.getTitreSujet(), "Titre de sujet");
        TextField contenuSujet = new TextField(sujet.getContenuSujet(), "Contenu de sujet");
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
        RadioButton etatOuvert = new RadioButton("Ouvert");
        RadioButton etatFerme = new RadioButton("Fermé");
        RadioButton etatBloque = new RadioButton("Bloqué");
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
        Label dateCreationSujet = new Label("Date de création : " + sujet.getDateCreationSujet());
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
                String etat = "";
                if (etatOuvert.isSelected()) {
                    etat = etatOuvert.getText();
                } else if (etatFerme.isSelected()) {
                    etat = etatFerme.getText();
                } else if (etatBloque.isSelected()) {
                    etat = etatBloque.getText();
                }
                int cat = 0;
                if (null != categorieSujet.getSelectedItem()) switch (categorieSujet.getSelectedItem()) {
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
                sujet.setTitreSujet(titreSujet.getText());
                sujet.setContenuSujet(contenuSujet.getText());
                sujet.setEtat(etat);
                sujet.setTags(tags.getText());
                sujet.setId_categorie(cat);
                System.out.println(sujet.toString());
                ServiceSujet.getInstance().modifierSujet(sujet);
                Dialog.show("Alert", "Le sujet a été modifié avec succès", "ok", null);
                previous.refreshList(res);
                previous.showBack();
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        addAll(titreSujet, contenuSujet, categorieSujet, etatOuvert, etatFerme, etatBloque, dateCreationSujet, tags, btnModifier);
    }

    editSujet(listSujet aThis, Resources res) {
    }
    
}
