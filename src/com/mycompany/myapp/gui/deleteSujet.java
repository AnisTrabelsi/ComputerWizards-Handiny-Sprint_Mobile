/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.services.ServiceSujet;

/**
 *
 * @author bengh
 */
public class deleteSujet extends Form{

    public deleteSujet(Sujet sujet, listSujet aThis, Resources res) {
        boolean confirmation = Dialog.show("Confirmation", "Voulez-vous vraiment supprimer ce sujet ?", "Oui", "Non");
        
        if (confirmation) {
            ServiceSujet.getInstance().deleteSujet(sujet.getIdSujet());
            Dialog.show("Alert", "Le sujet a été supprimé avec succès", "OK", null);
            aThis.refreshList(res);
            aThis.showBack();            
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            aThis.showBack();
        });
    }

    deleteSujet() {
    }

    
}
