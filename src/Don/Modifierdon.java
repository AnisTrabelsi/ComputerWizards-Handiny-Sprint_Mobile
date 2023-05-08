/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don;

import com.mycomany.entities.don;
import com.codename1.components.InfiniteProgress;
import com.mycompany.services.ServiceDon;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.services.ServiceDon;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 *
 * @author ASUS
 */
public class Modifierdon extends Form {

    public Modifierdon(Form previous, don r) {
        setTitle("Modifier don");
        setLayout(BoxLayout.y());
        TextField Type = new TextField(r.getType(), "Type");
        Type.getStyle().setFgColor(154245);
        TextField Description = new TextField(r.getDescription(), "Description");
        Description.getStyle().setFgColor(154245);
        Button photoButton = new Button("Ajouter une Image");
        photoButton.setTextPosition(Label.BOTTOM);
         photoButton.setBadgeText(r.getImage_don());
        CheckBox multiSelect = new CheckBox("Multi-select");
        
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
                if ((Type.getText().length() == 0) || (Description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    try {
                        r.setId_don((int) r.getId_don());
                        r.setId_utilisateur((int) r.getId_utilisateur());
                        r.setType(Type.getText());
                        r.setImage_don(photoButton.getBadgeText().toString());
                        r.setDescription(Description.getText());
                        // r.setDate_ajout(date_ajout);
                          if (photoButton.getBadgeText().toString() != null)
                        r.setImage_don(photoButton.getBadgeText().toString());
                        if (ServiceDon.getInstance().Updatedons(r)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                              // new ShowdonCrud(previous).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                      /*  new com.mycompany.myapp.HomeHandiny().show();
                        new ShowdonCrud(previous).show();*/
                        
                         new ShowdonCrud(new com.mycompany.myapp.HomeHandiny()).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                       // new com.mycompany.myapp.HomeHandiny().show();
                         new ShowdonCrud(previous).show();
                    }

                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
           // new com.mycompany.myapp.HomeHandiny().show();
            new ShowdonCrud(new com.mycompany.myapp.HomeHandiny()).show();
        });
        addAll(Type, Description, photoButton, btnValider);
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
