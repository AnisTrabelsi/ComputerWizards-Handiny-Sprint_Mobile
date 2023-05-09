/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;


import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycomany.utils.Statics;
import com.mycompany.services.ServiceDon;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowdonCrud extends Form {

    public ShowdonCrud(Form previous) {
         setTitle("Liste des dons de " /* seesion*/);
        setLayout(BoxLayout.y());
        ArrayList<com.mycomany.entities.don> dons = ServiceDon.getInstance().affichagedonUser(30);
        Container list = new Container(BoxLayout.y());
         list.setScrollableY(true);
        for (com.mycomany.entities.don don : dons) {
            
              EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
              Image i = URLImage.createToStorage(placeholder,don.getImage_don(),Statics.BASE_URL+"/uploads/"+don.getImage_don());
             MultiButton sp = new MultiButton(don.getDescription());
             sp.setIcon(i.fill(200, 200));
              sp.setTextLine1("Type : "+don.getType());
              sp.setTextLine2("Description : "+don.getDescription());
                     list.add(sp);
                     
                     sp.addActionListener((evt) -> {
                           if (Dialog.show("Confirmation", "Que voulez vous faire ?", "Supprimer", "Modifier")) {
                                            
                                                if(ServiceDon.getInstance().deleteddon(don.getId_don())){
                                                    {
                                                       Dialog.show("Success","Le don "+don.getType()+" a été supprimé avec succées",new Command("OK"));
                                                      new ShowdonCrud(previous).show();
                                                    }
                                        }
                                    }
                                        else{ 
                                               
                                               new Modifierdon(this,don).show();
                                            
                                        }
                     });
        }
        
         //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        this.add(list); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
}
