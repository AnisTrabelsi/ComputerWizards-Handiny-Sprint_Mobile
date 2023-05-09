/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.HomeHandiny;
import com.mycompany.services.ServiceDemandeDon;
import com.mycompany.services.ServiceDon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Showdon extends Form {

    private Form F1, current;
    private TextField searchField;

    public Showdon(Form previous) {
        setTitle("Liste des dons");
        setLayout(BoxLayout.y());
        ArrayList<com.mycomany.entities.don> dons = ServiceDon.getInstance().affichagedon();
        Container list = new Container(BoxLayout.y());
        
        
        list.setScrollableY(true);
             Style searchStyle = new Style();
        searchStyle.setBorder(RoundRectBorder.create().cornerRadius(2));
        searchStyle.setBgColor(0xEFEFEF); // Couleur de fond
        searchStyle.setFgColor(0x000000); // Couleur du texte

        int searchFieldMaxWidth = 200; // Largeur maximale souhaitée pour la barre de recherche

        searchField = new TextField();
        searchField.setHint("Rechercher...");
        searchField.setUIID("SearchTextField"); // Appliquer un UIID personnalisé pour éviter les styles par défaut
        searchField.getAllStyles().merge(searchStyle); // Fusionner le style personnalisé avec les styles existants
        searchField.setMaxSize(searchFieldMaxWidth); // Définir la largeur maximale
                Button PDF = new Button("PDF");



                
                
                
        list.addAll(searchField,PDF);
        
        
        for (com.mycomany.entities.don don : dons) {
            System.out.println(don.getType());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, don.getImage_don(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + don.getImage_don());
            MultiButton sp = new MultiButton(don.getType());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + don.getType());
            sp.setTextLine2("Description : " + don.getDescription());
            sp.setTextLine4("Date d'ajout : " + don.getDate_ajout());
            //   sp.setTextLine3("Description : "+don.getDescription());
            
            
             /*favoris*/
Button favorisButton = new Button();
favorisButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, "Button", 4.5f));
favorisButton.addActionListener(e -> {
    // Code pour ajouter la voiture aux favoris
    int donId = don.getId_don(); // Id de la don actuelle
   int id_user=30;
   //int userId = getCurrentUserId(); // Id de l'utilisateur actuel (à définir selon votre logique d'authentification)
    FavorisManager favorisManager = new FavorisManager();
    favorisManager.addFavori(id_user, donId); // Ajouter le favori avec une note initiale de 0
    favorisManager.closeDatabase();
    favorisButton.setEnabled(false); // Désactiver le bouton après l'ajout aux favoris
});


PDF.addActionListener(e -> { 
   
                try {
                    Document document = new Document();
                    String outputPath = "file:///C:/xampp4/pdff/don" + don.getType() + ".pdf";
                    PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));
                    
                    document.open();
                    
                    document.add(new Paragraph("Fiche de evenement"));
                    document.add(new Paragraph("Nom :" + don.getType()));
                    document.add(new Paragraph("Type :" + don.getType()));
                    document.add(new Paragraph("Description :" + don.getType()));
                    
                    document.close();
                    Dialog.show("Enregistré", "", "", "OK");
                    
                    Log.p("PDF file successfully created!");
                } catch (IOException ex) {
                    Logger.getLogger(Showdon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(Showdon.class.getName()).log(Level.SEVERE, null, ex);
                }
   
});


            list.addAll(sp,favorisButton);

         
            
            
            ////////////////
            F1 = new Form("Affichage détails", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "afficher", "Demander")) {

                    Label lbn = new Label("Description :" + don.getDescription());
                    Label lbEm = new Label("Type de don : " + don.getType());
                    Label lbdate = new Label("Date d'ajout :" + don.getDate_ajout());
                    Container cnt1 = new Container(BoxLayout.y());
                    cnt1.addAll(lbEm, lbn, lbdate);

                    Container cnt = new Container(new BorderLayout());
                    cnt.getStyle().setAlignment(Component.CENTER, true);
                    cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));        //ImageViewer imgview = new ImageViewer(i);
                    MultiButton sp2 = new MultiButton(don.getType());
                    sp2.setIcon(i.fill(500, 500));
                    cnt.addAll(sp2, cnt1);

                    F1.addAll(cnt);
                    System.out.println(don);
                    F1.show();

                    // F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdon(current).show());
                    F1.getToolbar().addCommandToSideMenu("", null, ev -> F1.show());
                    F1.getToolbar().addMaterialCommandToSideMenu("list",
                            FontImage.MATERIAL_ACCOUNT_CIRCLE, ev -> new Showdon(current).show());

                    F1.getToolbar().addCommandToOverflowMenu("Exit", null,
                            ev -> Display.getInstance().exitApplication());
                } else {

                    new AddReservationdon(this, don).show();

                }

            });
            /////////////////////////////////
        
            
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
                new HomeHandiny().show();
            });
        }
  searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            ArrayList<com.mycomany.entities.don> filteredDons = filterDons(dons, searchText);
            refreshList(filteredDons);
        });
        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeHandiny().showBack());

    }

    
       private ArrayList<com.mycomany.entities.don> filterDons(ArrayList<com.mycomany.entities.don> dons, String searchText) {
        ArrayList<com.mycomany.entities.don> filteredDons = new ArrayList<>();

        if (searchText.isEmpty()) {
            // Si le champ de recherche est vide, retourner la liste complète des sujets
            return dons;
        }

        for (com.mycomany.entities.don don : dons) {
            String titreDon= don.getType().toLowerCase();
            String contenuDons = don.getType().toLowerCase();
            if (titreDon.contains(searchText.toLowerCase()) || contenuDons.contains(searchText.toLowerCase())) {
                filteredDons.add(don);
            }
        }
        return filteredDons;
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    
     public void refreshList(ArrayList<com.mycomany.entities.don> dons) {
    this.removeAll(); 
    
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
   setTitle("Liste des dons");
        setLayout(BoxLayout.y());
     
        
    
            
        
        
        for (com.mycomany.entities.don don : dons) {
            System.out.println(don.getType());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, don.getImage_don(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + don.getImage_don());
            MultiButton sp = new MultiButton(don.getType());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + don.getType());
            sp.setTextLine2("Description : " + don.getDescription());
            sp.setTextLine4("Date d'ajout : " + don.getDate_ajout());
            //   sp.setTextLine3("Description : "+don.getDescription());
            Button favorisButton = new Button();
favorisButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, "Button", 4.5f));
favorisButton.addActionListener(e -> {
    // Code pour ajouter la voiture aux favoris
    int donId = don.getId_don(); // Id de la don actuelle
   int id_user=30;
   //int userId = getCurrentUserId(); // Id de l'utilisateur actuel (à définir selon votre logique d'authentification)
    FavorisManager favorisManager = new FavorisManager();
    favorisManager.addFavori(id_user, donId); // Ajouter le favori avec une note initiale de 0
    favorisManager.closeDatabase();
    favorisButton.setEnabled(false); // Désactiver le bouton après l'ajout aux favoris
});
            list.addAll(sp,favorisButton);

         
            
            
            ////////////////
            F1 = new Form("Affichage détails", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "afficher", "Demander")) {

                    Label lbn = new Label("Description :" + don.getDescription());
                    Label lbEm = new Label("Type de don : " + don.getType());
                    Label lbdate = new Label("Date d'ajout :" + don.getDate_ajout());
                    Container cnt1 = new Container(BoxLayout.y());
                    cnt1.addAll(lbEm, lbn, lbdate);

                    Container cnt = new Container(new BorderLayout());
                    cnt.getStyle().setAlignment(Component.CENTER, true);
                    cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));        //ImageViewer imgview = new ImageViewer(i);
                    MultiButton sp2 = new MultiButton(don.getType());
                    sp2.setIcon(i.fill(500, 500));
                    cnt.addAll(sp2, cnt1);

                    F1.addAll(cnt);
                    System.out.println(don);
                    F1.show();

                    // F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdon(current).show());
                    F1.getToolbar().addCommandToSideMenu("", null, ev -> F1.show());
                    F1.getToolbar().addMaterialCommandToSideMenu("list",
                            FontImage.MATERIAL_ACCOUNT_CIRCLE, ev -> new Showdon(current).show());

                    F1.getToolbar().addCommandToOverflowMenu("Exit", null,
                            ev -> Display.getInstance().exitApplication());
                } else {

                    new AddReservationdon(this, don).show();

                }

            });
            /////////////////////////////////
            
            
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
                new HomeHandiny().show();
            });
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.addAll(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeHandiny().showBack());

        
        
        
     this.revalidate();
     }
    
     
  
    
    public Showdon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
