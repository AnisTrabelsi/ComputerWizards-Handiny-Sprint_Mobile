/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Sujet;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author bengh
 */
public class ServiceSujet {

    //singleton 
    public static ServiceSujet instance = null;
    public ArrayList<Sujet> sujets;
    public ArrayList<Commentaire> commentaires;
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceSujet getInstance() {
        if (instance == null) {
            instance = new ServiceSujet();
        }
        return instance;
    }

    public ServiceSujet() {
        req = new ConnectionRequest();

    }

    //ajout 
    public void ajoutSujet(Sujet sujet) {

        String url = Statics.BASE_URL + "/sujet/addsujetjson?titreSujet=" + sujet.getTitreSujet() + "&contenuSujet=" + sujet.getContenuSujet() + "&categorieId=" + sujet.getId_categorie() + "&userId=14" + "$etat=" + sujet.getEtat() + "&tags=" + sujet.getTags();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Sujet> parseSujets(String jsonText) {
        try {
            sujets = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> sujetsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) sujetsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Sujet s = new Sujet();
                float idSujet = Float.parseFloat(obj.get("idSujet").toString());
                String dateCreationSujet = obj.get("dateCreationSujet").toString().substring(obj.get("dateCreationSujet").toString().indexOf("2"), obj.get("dateCreationSujet").toString().lastIndexOf("T"));
                s.setIdSujet((int) idSujet);
                s.setTitreSujet(obj.get("titreSujet").toString());
                s.setContenuSujet(obj.get("contenuSujet").toString());
                s.setEtat(obj.get("etat").toString());
                s.setTags(obj.get("tags").toString());
                System.out.println(dateCreationSujet);
                String pattern = "yyyy-MM-dd"; // Format de la date dans la chaîne
                DateFormat dateFormat = new SimpleDateFormat(pattern);
                Date date = null;
                try {
                    date = dateFormat.parse(dateCreationSujet);
                    System.out.println(date);
                    s.setDateCreationSujet(date);
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                System.out.println(s.getDateCreationSujet());
                //s.setDateCreationSujet(date);
                s.toString();
                sujets.add(s);
            }

            return sujets;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return sujets;
    }

    public ArrayList<Sujet> listSujet() {
        String url = Statics.BASE_URL + "/sujet/listemessujetsjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sujets = parseSujets(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sujets;

    }

    public boolean modifierSujet(Sujet sujet) {
        String url = Statics.BASE_URL + "/sujet/updatesujetjson?idSujet=" + sujet.getIdSujet() + "&titreSujet=" + sujet.getTitreSujet() + "&contenuSujet=" + sujet.getContenuSujet() + "&etat=" + sujet.getEtat() + "&userId=14&categorieId=" + sujet.getId_categorie() + "&tags=" + sujet.getTags();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public void deleteSujet(int idSujet) {
        String url = Statics.BASE_URL + "/sujet/deletesujetjson?idSujet=" + idSujet;

        ConnectionRequest req = new ConnectionRequest();
        req.setHttpMethod("DELETE");
        req.setUrl(url);

        req.addResponseListener((e) -> {
            int status = e.getResponseCode();
            String str = new String(req.getResponseData());
            System.out.println("data" + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Sujet> AllSujet() {
        String url = Statics.BASE_URL + "/sujet/listesujetsjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                sujets = parseSujets(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return sujets;
    }

    public Sujet getSujetById(int idSujet) {
        for (Sujet sujet : sujets) {
            if (sujet.getIdSujet() == idSujet) {
                return sujet;
            }
        }
        return null;
    }
    
    public ArrayList<Commentaire> parseCommentaires(String jsonText) {
    try {
        ArrayList<Commentaire> commentaires = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Map<String, Object> commentairesListJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) commentairesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Commentaire commentaire = new Commentaire();
            float idCommentaire = Float.parseFloat(obj.get("idCommentaire").toString());
            commentaire.setIdCommentaire((int) idCommentaire);
            commentaire.setContenuCommentaire(obj.get("contenuCommentaire").toString());
            commentaire.setPiecejointe(obj.get("piecejointe").toString());

            commentaires.add(commentaire);
        }

        return commentaires;
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}


    public ArrayList<Commentaire> showCommentairesBySujet(Sujet sujet) {
        String url = Statics.BASE_URL + "/sujet/listecommentairesjson?idSujet=" + sujet.getIdSujet();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseCommentaires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaires;
    }

}
