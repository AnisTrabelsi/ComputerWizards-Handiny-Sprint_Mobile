package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Chauffeur;
import com.mycompany.myapp.gui.ListChauffeurForm;
import com.mycompany.myapp.services.ServiceChauffeur;

public class ModifierChauffeurForm extends Form {
    private Form previous;
    private Chauffeur Chauffeur;
 
    private TextField tfCIN;
    private TextField tfNom;
    private TextField tfAdresse;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierChauffeurForm(Form previous, Chauffeur Chauffeur) {
        super("Modifier Chauffeur", BoxLayout.y());
        this.previous = previous;
        this.Chauffeur = Chauffeur;

        tfCIN= new TextField(Chauffeur.getCIN(), "CIN du Chauffeur");
        tfNom = new TextField(Chauffeur.getNom(), "Nom du Chauffeur");
        tfAdresse = new TextField(Chauffeur.getAdresse(), "Adresse du Chauffeur");

       
        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            
            
            
             Chauffeur.setCIN(tfCIN.getText());
            Chauffeur.setNom(tfNom.getText());
            Chauffeur.setAdresse(tfAdresse.getText());
            
            if (ServiceChauffeur.getInstance().modifierChauffeur(Chauffeur)) {
                Dialog.show(" Chauffeur modifiée", "Votre Chauffeur a été modifiée avec succès", "OK", null);
                new ListChauffeurForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListChauffeurForm(previous).show());
        addAll(tfCIN, tfNom,tfAdresse,btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}

