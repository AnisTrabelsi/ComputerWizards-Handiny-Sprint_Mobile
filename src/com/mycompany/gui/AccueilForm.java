/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import static com.codename1.ui.events.ActionEvent.Type.Command;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.layouts.BoxLayout.y;
import com.mycomany.entities.Utilisateur;

/**
 *
 * @author Chayma
 */

public class AccueilForm extends BaseForm {

 public AccueilForm(Resources res) {
    super("Accueil", BoxLayout.y());
    
ProfileForm pf = new ProfileForm ();
    // Contenu principal : le home
    Label home = new Label("Bienvenue sur Handiny !");
    home.setUIID("Accueil");
    add(home);

    // Barre latérale : le menu
    Button menuButton = new Button("Menu");
    menuButton.addActionListener(e -> getToolbar().openSideMenu());
    getToolbar().addCommandToSideMenu(new Command("Retour "));

    // Ajouter une option de menu pour la page de modification de profil
        ;

        // Ajouter une option de menu pour la page de modification de profil
       getToolbar().addCommandToSideMenu(new Command("Modifier mon profil") {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Récupérer l'utilisateur courant
//Utilisateur currentUser = SessionManager.getCurrentUser();
// Récupérer l'utilisateur courant depuis la session
  
        new ProfileForm(res).show();
    }
   // System.out.println(SessionManager.getEmail());
    
}
       
       );
       
 }}

   



