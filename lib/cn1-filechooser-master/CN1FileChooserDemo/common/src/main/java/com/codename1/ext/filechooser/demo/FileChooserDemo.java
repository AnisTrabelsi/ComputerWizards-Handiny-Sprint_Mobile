package com.codename1.ext.filechooser.demo;


import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;


/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class FileChooserDemo {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("Hi World");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        hi.addComponent(new Label("Hi World"));
        Button b = new Button("Browse Files");
        CheckBox multiSelect = new CheckBox("Multi-select");
        b.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".xls, .csv, text/plain", e2-> {
                    if (e2 == null || e2.getSource() == null) {
                        hi.add("No file was selected");
                        hi.revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[])e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                            
                        }
                        return;
                    }
                    
                    String file = (String)e2.getSource();
                    if (file == null) {
                        hi.add("No file was selected");
                        hi.revalidate();
                    } else {
                       System.out.println("File path: " + file);
                       
                       String extension = null;
                       if (file.lastIndexOf(".") > 0) {
                           extension = file.substring(file.lastIndexOf(".")+1);
                       }
                        FileSystemStorage fs = FileSystemStorage.getInstance();
                       if ("txt".equals(extension)) {
                          
                           try {
                               InputStream fis = fs.openInputStream(file);
                               hi.addComponent(new SpanLabel(Util.readToString(fis)));
                           } catch (Exception ex) {
                               Log.e(ex);
                           }
                       } else {
                           hi.add("Selected file "+file);
                       }
                       File f = new File(file);
                       String contents = "";
                       try (InputStream fis = fs.openInputStream(file)) {
                           contents = Util.readToString(fis);
                       } catch (Exception ex) {
                           Log.e(ex);
                           return;
                       }
                       contents += "\nTest";
                       try (OutputStreamWriter writer = new OutputStreamWriter(fs.openOutputStream(file, 0), "UTF-8")) {
                          writer.write(contents);
                       } catch (IOException ex) {
                            Log.e(ex);
                            return;
                        }
                       
                       
                    }
                    hi.revalidate();
                });
            }
        });
        hi.addComponent(b);
        
        Button testImage = new Button("Browse Images");
        testImage.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[])e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            try {
                                Image img = Image.createImage(path);
                                hi.add(new Label(img));
                                hi.revalidate();
                            } catch (Exception ex) {
                                Log.e(ex);
                            }
                            
                        }
                        return;
                       
                    }
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        try {
                            Image img = Image.createImage(file);
                            hi.add(new Label(img));
                            hi.revalidate();
                        } catch (Exception ex) {
                            Log.e(ex);
                        }


                    }
               });
            }
        });
        hi.add(testImage);
        hi.add(multiSelect);
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
