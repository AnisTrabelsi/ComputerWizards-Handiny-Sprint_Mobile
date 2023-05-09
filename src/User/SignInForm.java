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

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.services.ServiceUtilisateur;
import com.codename1.ui.Form;


/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends Form {

   public SignInForm() {
    setTitle("Authentification");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    // create the form components
    TextField username = new TextField("", "Email", 20, TextField.ANY);
    TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
    username.setSingleLineTextArea(false);
    password.setSingleLineTextArea(false);
    Button signIn = new Button("S'authentifier ");
    Button signUp = new Button("Inscription");
    Button mp = new Button("Mot de passe oublié ?", "CenterLabel");
    signUp.addActionListener(e -> new SignUpForm().show());
    signUp.setUIID("Link");
    Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");
    
    // add the components to the form
    add(username);
    add(password);
    add(signIn);
    add(mp);
    add(FlowLayout.encloseCenter(doneHaveAnAccount, signUp));
    
    // set up the sign-in action
    signIn.addActionListener(e -> {
        ServiceUtilisateur.getInstance().signin(username, password);
        AccueilForm accueil = new AccueilForm();
        accueil.show();
    });
    
    // set up the forgot password action
    mp.addActionListener(e -> {
        new ActivateForm().show();
    });
}


    
}
