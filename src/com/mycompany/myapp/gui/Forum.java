/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sujet;
import java.util.ArrayList;

/**
 *
 * @author bengh
 */
public class Forum extends Form {

    public Forum(Resources res) {
        ImageViewer img1 = new ImageViewer(res.getImage("logo2.png"));
        add(img1);
        //setLayout(BoxLayout.y());
        Container cnt = new Container(BoxLayout.y());
        Button btnval = new Button("Accueil");
        cnt.add(btnval);
        add(cnt);

        getToolbar().addCommandToSideMenu("Ajouter un sujet", null, ev -> {
            new ajoutSujet(this, res).show();
        });
        getToolbar().addCommandToSideMenu("Mes sujets", null, ev -> {
            new listSujet(this, res).show();
        });
        getToolbar().addCommandToSideMenu("Règlement du Forum", null, ev -> {
            new ReglementForum(this, res).show();
        });
        getToolbar().addCommandToSideMenu("Mes sauvegardes", null, ev -> {
            new SauvegardesSujets(this, res).show();
        });
        btnval.addActionListener(e -> {
            new AllSujets(this, res).show();
        });
    }

}
