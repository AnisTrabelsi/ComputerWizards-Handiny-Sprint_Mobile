/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Utilisateur;
import com.mycompany.services.ServiceUtilisateur;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    Utilisateur utilisateur = SessionManager.getCurrentUser();

    public ProfileForm( Resources res) {
        super("Profil", BoxLayout.y());
            Button menuButton = new Button("Menu");

menuButton.addActionListener(e -> getToolbar().openSideMenu());
    getToolbar().addCommandToSideMenu(new Command("Retour"){
    public void actionPerformed(ActionEvent evt) {
    new AccueilForm(res).show();}})
   

        ;

       getToolbar().addCommandToSideMenu(new Command("Modifier mon profil") {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Récupérer l'utilisateur courant
//Utilisateur currentUser = SessionManager.getCurrentUser();
// Récupérer l'utilisateur courant depuis la session
  
        new ProfileForm(res).show();
    }
        });
        TextField emailField = new TextField(utilisateur.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        emailField.getAllStyles().setFgColor(0x000000); // changer la couleur de texte en noir
        emailField.getAllStyles().setBorder(Border.createLineBorder(1, 0xCCCCCC)); // ajouter une bordure autour du champ

     
        TextField telephoneField = new TextField(utilisateur.getTelephone(), "Téléphone", 20, TextField.PHONENUMBER);
        telephoneField.getAllStyles().setFgColor(0x000000);
        telephoneField.getAllStyles().setBorder(Border.createLineBorder(1, 0xCCCCCC));
        
        TextField adresse = new TextField(utilisateur.getAdresse(), "Adresse", 20, TextField.PHONENUMBER);
        adresse.getAllStyles().setFgColor(0x000000);
        telephoneField.getAllStyles().setBorder(Border.createLineBorder(1, 0xCCCCCC));

        Button updateButton = new Button("Mettre à jour");
        updateButton.getAllStyles().setFgColor(0xFFFFFF); // changer la couleur du texte en blanc
        updateButton.getAllStyles().setBgColor(0x0000FF); // changer la couleur de fond en bleu
       updateButton.addActionListener(e -> {
    utilisateur.setEmail(emailField.getText());
    utilisateur.setTelephone(telephoneField.getText());
        utilisateur.setAdresse(adresse.getText());

    if(ServiceUtilisateur.getInstance().updateUser(utilisateur)) {
        Dialog.show("Succès", "Profil mis à jour avec succès", "OK", null);
    } else {
        Dialog.show("Erreur", "Erreur lors de la mise à jour du profil", "OK", null);
    }
});


        add(new Label("Mon profil"));
        add(new Label("E-Mail"));
        add(emailField);
        add(new Label("Téléphone"));
        add(telephoneField);
        add(new Label("Adresse"));
        add(adresse);
        add(updateButton);
    }


     public ProfileForm() {
        super();
    }

    

  

        
        
       
        
       

        
    

 
   
}
