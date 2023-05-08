/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author bengh
 */
public class ServiceCommentaire {
    //singleton 
    public static ServiceCommentaire instance = null;
    public ArrayList<Commentaire> sujets;
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceCommentaire getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }

    public ServiceCommentaire() {
        req = new ConnectionRequest();
    }
    //ajout 
    public void ajoutCommentaire(Commentaire commentaire) {

        String url = Statics.BASE_URL + "/commentaire/addcommentairejson?contenuCommentaire=" + commentaire.getContenuCommentaire() + "&idSujet=" + commentaire.getIdSujet() + "&pieceJointe=" + commentaire.getPiecejointe() + "&id_user" + commentaire.getId_utilisateur();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
}
