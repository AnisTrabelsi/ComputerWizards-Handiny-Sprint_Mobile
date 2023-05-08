/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceDon;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Showdon extends Form {
  private Form F1,current;
    public Showdon(Form previous) {
        setTitle("Liste des dons");
        setLayout(BoxLayout.y());
        ArrayList<com.mycomany.entities.don> dons = ServiceDon.getInstance().affichagedon();
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
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
            list.add(sp);

               F1 = new Form("Det", BoxLayout.y());
            sp.addActionListener((evt) -> {
                   
                           if (Dialog.show("Confirmation", "Que voulez vous faire ?", "afficher", "Demander")) {
                                            
                                                  Label lbn = new Label("Description :" + don.getDescription());
            Label lbEm = new Label("Type :" + don.getType());
            Label lbdate = new Label("Date :" + don.getDate_ajout());
           Container cnt1 = new Container(BoxLayout.y());
              cnt1.addAll(lbn,lbEm,lbdate);

        Container cnt = new Container(BoxLayout.y());
        //ImageViewer imgview = new ImageViewer(i);
         MultiButton sp2 = new MultiButton(don.getType());
            sp2.setIcon(i.fill(500, 500));
       cnt.addAll(sp2,cnt1);
            
            F1.addAll(cnt);
            System.out.println(don);
            F1.show();
            F1.getToolbar().addCommandToLeftBar("back", null, ev -> new Showdon(current).show());
                                    }
                                        else{ 
                                               
                                               new Modifierdon(this,don).show();
                                            
                                        }
                   //
                
                
                
         
   
            });
        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public Showdon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
