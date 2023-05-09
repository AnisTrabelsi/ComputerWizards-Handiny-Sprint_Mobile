/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidevv;

import Don.Ajouterdon;
import Don.Showdemandedon;
import Don.Showdon;
import Don.ShowdonCrud;
import Don.affichageFavorisDons;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author anis
 */
public class HomeHandiny extends Form {

    Form current;
    String c;
    private Resources theme;

    public HomeHandiny() {
        current = this; //Back 

        add(new Label("Bienvenue sur notre application mobile Handiny"));
        setTitle("Handiny");
        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(600, 500, 0xff0000ff), true);
        String imagePath = "http://localhost/mobile/logo.png"; // Chemin relatif de l'image dans votre projet
        Image image = URLImage.createToStorage(placeholder, "image_key2", imagePath);
        ImageViewer img1 = new ImageViewer(image);
        add(img1);
  getToolbar().addCommandToSideMenu("Page 1", null, e->new HomeHandinyDon().show());
       getToolbar().addCommandToSideMenu("Page 2", null, e->new HomeHandiny().show());
        getToolbar().addCommandToSideMenu("Page 3", null, e->new HomeHandiny().show());
   

        getToolbar().addCommandToOverflowMenu("Exit", null,
                ev -> Display.getInstance().exitApplication());
    }

}