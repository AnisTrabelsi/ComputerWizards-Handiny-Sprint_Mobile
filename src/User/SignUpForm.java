/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package User;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;
import java.util.Vector;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends Form {

    public SignUpForm() {
       setTitle("Inscription ");
        setLayout(BoxLayout.y());
        
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField cin = new TextField("", "Cin", 20, TextField.ANY);
        TextField telephone = new TextField("", "telephone", 20, TextField.ANY);
        TextField login = new TextField("", "Login", 20, TextField.ANY);

        TextField password = new TextField("", " Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField region = new TextField("", "Region", 20, TextField.ANY);

        TextField adresse = new TextField("", "adresse", 20, TextField.ANY);

      
        Vector<String> vectorRole;
        vectorRole = new Vector();

        vectorRole.add("Locatire de voiture");
        vectorRole.add("Proprietaire de voiture ");

        ComboBox<String> roles = new ComboBox<>(vectorRole);

        Vector<String> vectorgenre;
        vectorgenre = new Vector();

        vectorgenre.add("Femme");
        vectorgenre.add("Homme");

        ComboBox<String> genre = new ComboBox<>(vectorgenre);

        Picker date_de_naissance = new Picker();
        date_de_naissance.setType(Display.PICKER_TYPE_DATE);

  
           nom.getStyle().setFgColor(154245);
       prenom.getStyle().setFgColor(154245);
       email.getStyle().setFgColor(154245);
       cin.getStyle().setFgColor(154245);
       telephone.getStyle().setFgColor(154245);
       login.getStyle().setFgColor(154245);
       password.getStyle().setFgColor(154245);
       confirmPassword.getStyle().setFgColor(154245);
              region.getStyle().setFgColor(154245);
       adresse.getStyle().setFgColor(154245);

       
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        cin.setSingleLineTextArea(false);
        telephone.setSingleLineTextArea(false);
        login.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        region.setSingleLineTextArea(false);
        adresse.setSingleLineTextArea(false);

        Button next = new Button("S'inscrire");
        Button signIn = new Button(" S'authentifier");
        signIn.addActionListener(e -> new SignInForm().show());
        signIn.setUIID("Link");
Label alreadyHaveAnAccount = new Label("Vous avez déja un compte ?");
alreadyHaveAnAccount.setUIID("Link");
alreadyHaveAnAccount.addPointerPressedListener((e) -> {
    new SignInForm().show();
});
  add(nom);
    add(prenom);
    add(email);
    add(cin);
    add(telephone);
    add(login);
    add(password);
    add(confirmPassword);
    add(region);
    add(adresse);
    add(roles);
    add(genre);
    add(date_de_naissance);
    add(next);
    add(signIn);
    add (alreadyHaveAnAccount);

next.requestFocus();
next.addActionListener((e) -> {
    ServiceUtilisateur.getInstance().signup(nom, prenom, cin, email, telephone, login,
        password, confirmPassword, region, adresse, roles, genre, date_de_naissance );
    Dialog.show("Success", "account is saved", "OK", null);
    new SignInForm().show();
});

    }

}
