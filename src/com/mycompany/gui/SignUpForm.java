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
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
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
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

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

        //Role 
        //Vector 3ibara ala array 7atit fiha roles ta3na ba3d nzidouhom lel comboBox
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

        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

Container content = BoxLayout.encloseY();
content.addAll(
    new FloatingHint(nom),
    createLineSeparator(),
    new FloatingHint(prenom),
    createLineSeparator(),
    new FloatingHint(email),
    createLineSeparator(),
    new FloatingHint(cin),
    createLineSeparator(),
    new FloatingHint(telephone),
    createLineSeparator(),
    new FloatingHint(login),
    createLineSeparator(),
    new FloatingHint(password),
    createLineSeparator(),
    new FloatingHint(confirmPassword),
    createLineSeparator(),
    new FloatingHint(region),
    createLineSeparator(),
    new FloatingHint(adresse),
    createLineSeparator(),
        roles,
        createLineSeparator(),
        genre,
         createLineSeparator(),
        date_de_naissance
);

    
content.setScrollableY(true);
add(BorderLayout.CENTER, content);
add(BorderLayout.SOUTH, BoxLayout.encloseY(
    next,
    FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
));
next.requestFocus();
next.addActionListener((e) -> {
    ServiceUtilisateur.getInstance().signup(nom, prenom, cin, email, telephone, login,
        password, confirmPassword, region, adresse, roles, genre, date_de_naissance, res);
    Dialog.show("Success", "account is saved", "OK", null);
    new SignInForm(res).show();
});

    }

}
