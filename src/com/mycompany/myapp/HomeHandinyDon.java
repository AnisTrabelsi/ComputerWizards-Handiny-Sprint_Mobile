/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Don.Ajouterdon;
import Don.Modifierdon;
import Don.Showdemandedon;
import Don.Showdon;
import Don.ShowdonCrud;
import Don.affichageFavorisDons;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.don;
import com.mycompany.services.ServiceDemandeDon;
import com.mycompany.services.ServiceDon;

/**
 *
 * @author ASUS
 */
public class HomeHandinyDon extends Form {

    Form current;
    String c;
    private Resources theme;

    public HomeHandinyDon() {
        current = this; //Back 

        add(new Label("          Bienvenue a Handiny : Gestion Don"));
        setTitle("Handiny");
        setLayout(BoxLayout.y());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(600, 500, 0xff0000ff), true);
        String imagePath = "http://localhost/mobile/logo.png"; // Chemin relatif de l'image dans votre projet
        Image image = URLImage.createToStorage(placeholder, "image_key2", imagePath);
        ImageViewer img1 = new ImageViewer(image);
        add(img1);

        Button BUTaffiche = new Button("afficher don");
        Button BUTajouter = new Button("ajouter don");
        Button BUTmodifier = new Button("modifier don");
        Button BUTsupprimer = new Button("supprimer ou modifier don");
        Button BUTpdf = new Button("pdf don");

        Button BUTaffiched = new Button("afficher demande don");

        BUTaffiche.addActionListener((evt) -> new Showdon(current).show());
        BUTajouter.addActionListener((evt) -> new Ajouterdon(current).show());
        /*don d=new don();
d.setId_don(402);
BUTmodifier.addActionListener((evt) -> new Modifierdon(current,d).show());*/
        BUTsupprimer.addActionListener((evt) -> new ShowdonCrud(current).show());

        BUTaffiched.addActionListener((evt) -> new Showdemandedon(current, 0, c).show());

        Button BtnFavDon = new Button("Mes favoris");
        BtnFavDon.addActionListener((evt) -> new affichageFavorisDons(current).show());

        addAll(BUTaffiche, BUTajouter, BUTsupprimer, BUTaffiched, BtnFavDon);

        getToolbar().addCommandToOverflowMenu("Exit", null,
                ev -> Display.getInstance().exitApplication());
    }

}
