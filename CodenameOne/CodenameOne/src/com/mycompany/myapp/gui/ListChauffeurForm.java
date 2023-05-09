/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.entites.Chauffeur;
import com.mycompany.myapp.services.ServiceChauffeur;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author imtinen
 */
public class ListChauffeurForm extends Form {
private Form previous;
public ListChauffeurForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des Chauffeurs");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<Chauffeur> chauffeurs = ServiceChauffeur.getInstance().getAllChauffeurs();
    
    for (Chauffeur chauffeur : chauffeurs) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label CINLabel = new Label("CIN : " + chauffeur.getCIN());
         Label NomLabel = new Label("Nom : " + chauffeur.getNom());
        Label AdresseLabel = new Label("Adresse : " + chauffeur.getAdresse());
       
        
      

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette chauffeur ?", "Oui", "Non")) {
        if (ServiceChauffeur.getInstance().deleteChauffeur(chauffeur.getId_chauffeur() )) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de chauffeur", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierChauffeurForm(this, chauffeur).show());

       Button pdf=new Button("obtenir fiche pdf");
            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, pdf.getUnselectedStyle()));
           pdf.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C:/xampp4/pdff/chauffeur" + chauffeur.getId_chauffeur() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        
        //        // Ajouter le logo  C:\xampp\htdocs
//       String logoPath = "file:///C:/xampp/htdocs/CodenameOne/logo.jpg"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
    

 DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        String currentDate = dateFormat.format(new Date());

        
//        //        // Ajouter le logo
//       String logoPath = "file:///C:/xampp/htdocs/logo.png"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
//        
//       String imagePath = "file:///C:/xampp/htdocs/logo.png";
//    EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(imagePath), false);
//    Image logo = encodedImage.scaledEncoded(70, 70);

        document.open();
        document.add(new Paragraph("Date : " + currentDate));
        document.add(new Paragraph("Fiche de CHAUFFEUR"));
        document.add(new Paragraph("Nom :" + chauffeur.getNom()));
        document.add(new Paragraph("CIN :" + chauffeur.getCIN()));
        document.add(new Paragraph("ADRESSE :" + chauffeur.getAdresse()));
       
        
        
        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});
       
       
        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(CINLabel);
        container.add(NomLabel);
        container.add(AdresseLabel);
        
      
        container.add(buttonSupprimer);
        container.add(buttonModifier);
        container.add(pdf);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddChauffeurForm(this).show());
}
}