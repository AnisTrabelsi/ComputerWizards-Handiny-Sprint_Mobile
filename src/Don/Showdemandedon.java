/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.HomeHandiny;
import com.mycompany.services.ServiceDemandeDon;
import com.mycompany.services.ServiceDon;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Showdemandedon extends Form {

    private Form F1, current;
    int n = 0;
    private Resources theme;

    public Showdemandedon(Form previous, int n, String c) {
        setTitle("Liste des demande_dons");
        setLayout(BoxLayout.y());
  Button BUTActualiser = new Button("Actualiser");
        BUTActualiser.addActionListener((evt) -> new Showdemandedon(current,0,c).show());
            Button BUTTrieNom = new Button("Trie selon Type ");
         BUTTrieNom.addActionListener((evt) -> new Showdemandedon(current,1,c).show());
            Button BUTTriePrix = new Button("Trie selon Date croissant ");
        BUTTriePrix.addActionListener((evt) -> new Showdemandedon(current,2,c).show());
            Button BUTTrieDest = new Button("Trie selon Date decroissant ");
        BUTTrieDest.addActionListener((evt) -> new Showdemandedon(current,3,c).show());
            addAll(BUTTrieNom,BUTTriePrix,BUTTrieDest,BUTActualiser);
                    TextField Recherche = new TextField("", "Donesearch");
                        Recherche.getStyle().setFgColor(154245);
                        Button Voyagesearch = new Button("Donesearch");
        Voyagesearch.addActionListener((evt) -> new Showdemandedon(current,4,Recherche.getText()).show());
        
                add(Recherche);
                add(Voyagesearch);
        
         if(n==0)
            {
        ArrayList<com.mycomany.entities.demande_don> demande_dons = ServiceDemandeDon.getInstance().affichagedemande_donUser(30); 
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.demande_don demande_don : demande_dons) {
            System.out.println(demande_don.getRemarques());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, demande_don.getJustificatif_handicap(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + demande_don.getJustificatif_handicap());
            MultiButton sp = new MultiButton(demande_don.getType_produit_demande());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + demande_don.getRemarques());
            sp.setTextLine2("Description : " + demande_don.getType_produit_demande());
            sp.setTextLine4("Date d'ajout : " + demande_don.getEtat());
            //   sp.setTextLine3("Description : "+demande_don.getDescription());
            list.add(sp);

            F1 = new Form("Det", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "modifier")) {

                    if (ServiceDemandeDon.getInstance().deleteddemande_don(demande_don.getId_demande_don())) {
                        {
                            Dialog.show("Success", "La demande don de " + demande_don.getType_produit_demande() + " a été supprimé avec succées", new Command("OK"));
                            new Showdemandedon(previous,0,c).show();
                        }
                    }
                } else {
                    new Modifierdemandedon(this, demande_don).show();
                }

            });
            Button validerButton = new Button("afficher");
 Button QRcode= new Button("QRcode"); 
 
            validerButton.addActionListener(e -> {
                Label lbn = new Label("Description :" + demande_don.getRemarques());
                Label lbEm = new Label("Type :" + demande_don.getRemarques());
                Label lbdate = new Label("Date :" + demande_don.getRemarques());
                Container cnt1 = new Container(BoxLayout.y());
                cnt1.addAll(lbn, lbEm, lbdate);

                Container cnt = new Container(BoxLayout.y());
                //ImageViewer imgview = new ImageViewer(i);
                MultiButton sp2 = new MultiButton(demande_don.getType_produit_demande());
                sp2.setIcon(i.fill(500, 500));
                cnt.addAll(sp2, cnt1);
                F1.addAll(cnt);
                System.out.println(demande_don);
                F1.show();
                F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdemandedon(current,0,c).show());
            });
            
            QRcode.addActionListener(e -> {
            
            ServiceDemandeDon.getInstance().Qrcode(demande_don.getId_demande_don());
            
            });
            
            list.addAll(validerButton,QRcode);

            
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
        
         if(n==1)
            {
        ArrayList<com.mycomany.entities.demande_don> demande_dons = ServiceDemandeDon.getInstance().order_By_typeJSON(30); 
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.demande_don demande_don : demande_dons) {
            System.out.println(demande_don.getRemarques());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, demande_don.getJustificatif_handicap(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + demande_don.getJustificatif_handicap());
            MultiButton sp = new MultiButton(demande_don.getType_produit_demande());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + demande_don.getRemarques());
            sp.setTextLine2("Description : " + demande_don.getType_produit_demande());
            sp.setTextLine4("Date d'ajout : " + demande_don.getEtat());
            //   sp.setTextLine3("Description : "+demande_don.getDescription());
            list.add(sp);

            F1 = new Form("Det", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "modifier")) {

                    if (ServiceDemandeDon.getInstance().deleteddemande_don(demande_don.getId_demande_don())) {
                        {
                            Dialog.show("Success", "La demande don de " + demande_don.getType_produit_demande() + " a été supprimé avec succées", new Command("OK"));
                            new Showdemandedon(previous,1,c).show();
                        }
                    }
                } else {
                    new Modifierdemandedon(this, demande_don).show();
                }

            });
            Button validerButton = new Button("afficher");

            validerButton.addActionListener(e -> {
                Label lbn = new Label("Description :" + demande_don.getRemarques());
                Label lbEm = new Label("Type :" + demande_don.getRemarques());
                Label lbdate = new Label("Date :" + demande_don.getRemarques());
                Container cnt1 = new Container(BoxLayout.y());
                cnt1.addAll(lbn, lbEm, lbdate);

                Container cnt = new Container(BoxLayout.y());
                //ImageViewer imgview = new ImageViewer(i);
                MultiButton sp2 = new MultiButton(demande_don.getType_produit_demande());
                sp2.setIcon(i.fill(500, 500));
                cnt.addAll(sp2, cnt1);
                F1.addAll(cnt);
                System.out.println(demande_don);
                F1.show();
                F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdemandedon(current,1,c).show());
            });
            list.add(validerButton);

            
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
        
          if(n==2)
            {
        ArrayList<com.mycomany.entities.demande_don> demande_dons = ServiceDemandeDon.getInstance().order_By_dateascJSON(30); 
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.demande_don demande_don : demande_dons) {
            System.out.println(demande_don.getRemarques());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, demande_don.getJustificatif_handicap(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + demande_don.getJustificatif_handicap());
            MultiButton sp = new MultiButton(demande_don.getType_produit_demande());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + demande_don.getRemarques());
            sp.setTextLine2("Description : " + demande_don.getType_produit_demande());
            sp.setTextLine4("Date d'ajout : " + demande_don.getEtat());
            //   sp.setTextLine3("Description : "+demande_don.getDescription());
            list.add(sp);

            F1 = new Form("Det", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "modifier")) {

                    if (ServiceDemandeDon.getInstance().deleteddemande_don(demande_don.getId_demande_don())) {
                        {
                            Dialog.show("Success", "La demande don de " + demande_don.getType_produit_demande() + " a été supprimé avec succées", new Command("OK"));
                            new Showdemandedon(previous,2,c).show();
                        }
                    }
                } else {
                    new Modifierdemandedon(this, demande_don).show();
                }

            });
            Button validerButton = new Button("afficher");

            validerButton.addActionListener(e -> {
                Label lbn = new Label("Description :" + demande_don.getRemarques());
                Label lbEm = new Label("Type :" + demande_don.getRemarques());
                Label lbdate = new Label("Date :" + demande_don.getRemarques());
                Container cnt1 = new Container(BoxLayout.y());
                cnt1.addAll(lbn, lbEm, lbdate);

                Container cnt = new Container(BoxLayout.y());
                //ImageViewer imgview = new ImageViewer(i);
                MultiButton sp2 = new MultiButton(demande_don.getType_produit_demande());
                sp2.setIcon(i.fill(500, 500));
                cnt.addAll(sp2, cnt1);
                F1.addAll(cnt);
                System.out.println(demande_don);
                F1.show();
                F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdemandedon(current,2,c).show());
            });
            list.add(validerButton);

            
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
         if(n==3)
            {
        ArrayList<com.mycomany.entities.demande_don> demande_dons = ServiceDemandeDon.getInstance().order_By_datedescJSON(30); 
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.demande_don demande_don : demande_dons) {
            System.out.println(demande_don.getRemarques());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, demande_don.getJustificatif_handicap(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + demande_don.getJustificatif_handicap());
            MultiButton sp = new MultiButton(demande_don.getType_produit_demande());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + demande_don.getRemarques());
            sp.setTextLine2("Description : " + demande_don.getType_produit_demande());
            sp.setTextLine4("Date d'ajout : " + demande_don.getEtat());
            //   sp.setTextLine3("Description : "+demande_don.getDescription());
            list.add(sp);

            F1 = new Form("Det", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "modifier")) {

                    if (ServiceDemandeDon.getInstance().deleteddemande_don(demande_don.getId_demande_don())) {
                        {
                            Dialog.show("Success", "La demande don de " + demande_don.getType_produit_demande() + " a été supprimé avec succées", new Command("OK"));
                            new Showdemandedon(previous,3,c).show();
                        }
                    }
                } else {
                    new Modifierdemandedon(this, demande_don).show();
                }

            });
            Button validerButton = new Button("afficher");

            validerButton.addActionListener(e -> {
                Label lbn = new Label("Description :" + demande_don.getRemarques());
                Label lbEm = new Label("Type :" + demande_don.getRemarques());
                Label lbdate = new Label("Date :" + demande_don.getRemarques());
                Container cnt1 = new Container(BoxLayout.y());
                cnt1.addAll(lbn, lbEm, lbdate);

                Container cnt = new Container(BoxLayout.y());
                //ImageViewer imgview = new ImageViewer(i);
                MultiButton sp2 = new MultiButton(demande_don.getType_produit_demande());
                sp2.setIcon(i.fill(500, 500));
                cnt.addAll(sp2, cnt1);
                F1.addAll(cnt);
                System.out.println(demande_don);
                F1.show();
                F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdemandedon(current,3,c).show());
            });
            list.add(validerButton);

            
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
        
         if(n==4)
            {
        ArrayList<com.mycomany.entities.demande_don> demande_dons = ServiceDemandeDon.getInstance().voyagesearch(30,c); 
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycomany.entities.demande_don demande_don : demande_dons) {
            System.out.println(demande_don.getRemarques());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, demande_don.getJustificatif_handicap(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + demande_don.getJustificatif_handicap());
            MultiButton sp = new MultiButton(demande_don.getType_produit_demande());
            sp.setIcon(i.fill(200, 200));
            sp.setTextLine1("Type : " + demande_don.getRemarques());
            sp.setTextLine2("Description : " + demande_don.getType_produit_demande());
            sp.setTextLine4("Date d'ajout : " + demande_don.getEtat());
            //   sp.setTextLine3("Description : "+demande_don.getDescription());
            list.add(sp);

            F1 = new Form("Det", BoxLayout.y());
            sp.addActionListener((evt) -> {

                if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "modifier")) {

                    if (ServiceDemandeDon.getInstance().deleteddemande_don(demande_don.getId_demande_don())) {
                        {
                            Dialog.show("Success", "La demande don de " + demande_don.getType_produit_demande() + " a été supprimé avec succées", new Command("OK"));
                            new Showdemandedon(previous,4,c).show();
                        }
                    }
                } else {
                    new Modifierdemandedon(this, demande_don).show();
                }

            });
            Button validerButton = new Button("afficher");

            validerButton.addActionListener(e -> {
                Label lbn = new Label("Description :" + demande_don.getRemarques());
                Label lbEm = new Label("Type :" + demande_don.getRemarques());
                Label lbdate = new Label("Date :" + demande_don.getRemarques());
                Container cnt1 = new Container(BoxLayout.y());
                cnt1.addAll(lbn, lbEm, lbdate);

                Container cnt = new Container(BoxLayout.y());
                //ImageViewer imgview = new ImageViewer(i);
                MultiButton sp2 = new MultiButton(demande_don.getType_produit_demande());
                sp2.setIcon(i.fill(500, 500));
                cnt.addAll(sp2, cnt1);
                F1.addAll(cnt);
                System.out.println(demande_don);
                F1.show();
                F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdemandedon(current,4,c).show());
            });
            list.add(validerButton);

            
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
            }
        
        
        
        
        
        
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeHandiny().showBack());

    }

    public Showdemandedon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
