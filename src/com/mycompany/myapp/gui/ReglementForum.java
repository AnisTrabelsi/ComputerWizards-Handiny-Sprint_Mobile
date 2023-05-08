/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.UnsupportedEncodingException;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bengh
 */
public class ReglementForum extends Form {

    public ReglementForum(Forum previous, Resources res) {
        setTitle("Réglements du forum");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        String url = "http://localhost/mobile/reglementsforum.txt";

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(url);

        con.addResponseListener(e -> {
            String rep = new String(con.getResponseData());
            SpanLabel spanLabel = new SpanLabel(rep);
            spanLabel.setTextBlockAlign(Component.LEFT); // Aligner le texte à gauche
            add(spanLabel);

            CheckBox acceptCheckBox = new CheckBox("J'accepte les règlements");
            Button envoie = new Button("Accepter");

            envoie.addActionListener(l -> {
                if (acceptCheckBox.isSelected()) {
                    sendMail();
                } else {
                    Dialog.show("Erreur", "Veuillez accepter les règlements", "OK", null);
                }
            });

            add(acceptCheckBox);
            add(envoie);
            revalidate(); // Redraw the form to reflect the changes
 });
        NetworkManager.getInstance().addToQueueAndWait(con);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    ReglementForum() {
    }

    private void sendMail() {
        try {

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp"); //SMTP protocol
            props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("ok"));
            msg.setRecipients(Message.RecipientType.TO, "oumaima.benghanem@esprit.tn");
            msg.setSubject("Bienvenue dans la communauté");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            msg.setText("Bonjour Mr/Madame\n"
                    + "Vous êtes le bienvenu à notre communauté Handiny,\n"
                    + "Maintenant, vous pouvez accéder à notre forum et bien évidemment créer vos postes\n"
                    + "Bien cordialement");

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");

            st.connect("smtp.gmail.com", 465, "chaima.lotfi@esen.tn", "chiwawanemsa12398KK?MA.");

            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : " + st.getLastServerResponse());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
