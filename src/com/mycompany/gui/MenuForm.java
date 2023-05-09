/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Chayma
 */


    public class MenuForm extends BaseForm {

    public MenuForm() {
        super(new BorderLayout());
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Ajouter les éléments du menu
        addMenuItem("Accueil", AccueilForm.class);
        addMenuItem("Profil", ProfileForm.class);
    }

    private void addMenuItem(String label, Class formClass) {
        Button menuItem = new Button(label);
        menuItem.addActionListener(e -> {
            try {
                Form form = (Form) formClass.newInstance();
                form.show();
            } catch (InstantiationException | IllegalAccessException ex) {
                Log.e(ex);
            }
        });
        add(menuItem);
    }
}

    

