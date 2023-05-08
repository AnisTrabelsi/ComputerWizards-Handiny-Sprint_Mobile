/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class don extends Form{
Form current;
    public don(Resources res, Form previous) {
         current=this; //Back 
        add(new Label("Don"));
        setTitle(" --Don-- ");
        setLayout(BoxLayout.y());
        
    Button BUTAdd = new Button("Add Don");
    Button BUTShow = new Button("Show Don");
    //Button BUTCam = new Button("Camera ");
    BUTShow.addActionListener((evt) -> new Showdon(current).show());
    BUTAdd.addActionListener((evt) -> new Showdon(current).show());
        addAll(BUTAdd,BUTShow);
    
   /* getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
  new ProfileForm(res,this).show();        });*/ }

    }
   
