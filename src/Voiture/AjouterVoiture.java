/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voiture;

import Don.ShowdonCrud;
import com.mycompany.services.ServiceDon;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Voiture;
import com.mycompany.services.ServiceVoiture;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author ASUS
 */
public class AjouterVoiture extends Form {

    public AjouterVoiture(Form previous) {
        setTitle("Ajout d'une voiture");
        setLayout(BoxLayout.y());
        
        
      TextField tfimmat = new TextField("", "Immatriculation");
      TextField tfmarque = new TextField("", "Marque");
      TextField tfmodele = new TextField("", "Modele");
      TextField tfcarb = new TextField("", "Carburant");
      TextField tfkilom = new TextField("", "Kilometrage");
      //TextField tfboiteVitesse = new TextField("", "Boite de vitesse");
   Vector<String> vectorRole;
        vectorRole = new Vector();

        vectorRole.add("Manuelle");
        vectorRole.add("Automatique");

        ComboBox<String> tfboiteVitesse = new ComboBox<>(vectorRole);




      TextField tfdesc = new TextField("", "Description");
      //TextField tfimage = new TextField("", "Image");
      TextField tfprix = new TextField("", "Prix de location");
      
       tfimmat.getStyle().setFgColor(154245);
       tfmarque.getStyle().setFgColor(154245);
       tfmodele.getStyle().setFgColor(154245);
       tfcarb.getStyle().setFgColor(154245);
       tfkilom.getStyle().setFgColor(154245);
       tfboiteVitesse.getStyle().setFgColor(154245);
       tfdesc.getStyle().setFgColor(154245);
       tfprix.getStyle().setFgColor(154245);
     
      
        Picker tfDateDeb = new Picker();
        tfDateDeb.getStyle().setFgColor(154245);
        tfDateDeb.setType(Display.PICKER_TYPE_DATE);
        tfDateDeb.setDate(new Date());
// Format the date as "yyyy-MM-dd"
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = dateFormat.format(tfDateDeb.getDate());

       
      

        Button photoButton = new Button("Ajouter une Image");
        photoButton.setTextPosition(Label.BOTTOM);

        CheckBox multiSelect = new CheckBox("Multi-select");
//
        photoButton.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {

                FileChooser.setOpenFilesInPlace(true);

                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg,.jpeg,.png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }
                    //menna 7atta el photoButton.setBadgeText(namePic); 9a3din ntal3ou fi esm taswira el 7a9ani 
                    String file = (String) e2.getSource();

                    System.out.println(file);
                    String extension = null;
                    if (file.lastIndexOf(".") > 0) {
                        extension = file.substring(file.lastIndexOf(".") + 1);
                        StringBuilder hi = new StringBuilder(file);

                        if (file.startsWith("file://")) {
                        } else {
                            hi.delete(0, 7);
                        }
                        int lastIndexPeriod = hi.toString().lastIndexOf(".");
                        Log.p(hi.toString());
                        String ext = hi.toString().substring(lastIndexPeriod);
                        String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                        try {
                            String namePic = saveFileToDevice(file, ext);
                            System.out.println(namePic);
                            //cr.setFilename("file",namePic);//any unique name you want
                            // photoButton.getIcon().setImageName(namePic);
                            photoButton.setBadgeText(namePic); //7atit l'esm fl badge ta3 button bch najjam nesta3mlou el berra ml cha9lalla hadhi
                        } catch (IOException ex) {
                        }
                    }
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;
                        try {
                            logo = Image.createImage(file).scaledSmallerRatio(256, 256);
                            photoButton.setIcon(logo); //lenna bch tatla3lk taswira 9bal ma ta3ml submit
                            //lenna nbdaw fl enregistrement ta3 taswira
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + photoButton.getBadgeText();
                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                //System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);//3mlna save lel image fi wost file system storage
                                System.out.println();
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }
                        revalidate();

                    }
                });
            }
        });

        Button btnValider = new Button("Valider");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfmarque.getText().isEmpty() || tfmodele.getText().isEmpty() || tfimmat.getText().isEmpty() || tfboiteVitesse.getSelectedItem() == null || tfkilom.getText().isEmpty() || tfcarb.getText().isEmpty() || tfprix.getText().isEmpty() || tfdesc.getText().isEmpty() || tfDateDeb.getText().isEmpty() || photoButton.getBadgeText().isEmpty()) {
    Dialog.show("Alerte", "Merci de remplir tous les champs", new Command("OK"));
} else {
    try {
        // Vérification du kilométrage positif
        float kilometrage = Float.parseFloat(tfkilom.getText());
        if (kilometrage < 0) {
            Dialog.show("Alerte", "Le kilométrage doit être un nombre positif", new Command("OK"));
            return; // Arrêter l'exécution du code si la condition n'est pas respectée
        }
        
        // Vérification du prix de location positif
        float prixLocation = Float.parseFloat(tfprix.getText());
        if (prixLocation < 0) {
            Dialog.show("Alerte", "Le prix de location doit être un nombre positif", new Command("OK"));
            return; // Arrêter l'exécution du code si la condition n'est pas respectée
        }
        
        // Tous les champs sont remplis et les contraintes sont respectées, vous pouvez récupérer les valeurs ici
        com.mycomany.entities.Voiture v = new Voiture(tfimmat.getText(), tfmarque.getText(), tfmodele.getText(),tfboiteVitesse.getSelectedItem(), tfkilom.getText(), tfcarb.getText(), photoButton.getBadgeText().toString(), tfdesc.getText(), Float.parseFloat(tfprix.getText()), formattedDate);
        System.out.println(v);
   
                       if (ServiceVoiture.getInstance().addVoiture(v)) {
            Dialog.show("Success", "Voiture ajoutée avec succès", new Command("OK"));
            new AffichageVoitures(new com.mycompany.myapp.HomeHandiny()).show();
        } else {
            Dialog.show("ERROR", "Erreur du serveur", new Command("OK"));
        }
    } catch (NumberFormatException e) {
        Dialog.show("ERROR", "Le kilométrage et le prix de location doivent être des nombres", new Command("OK"));
    }

                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        
        addAll(tfimmat, tfmarque, tfmodele, tfcarb, tfkilom, tfboiteVitesse, tfdesc, tfprix,tfDateDeb, photoButton, btnValider);
    }

    protected String saveFileToDevice(String hi, String ext) throws IOException {
        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest();
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "null";
    }

}
