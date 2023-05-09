package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Chauffeur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceChauffeur {
    private static ServiceChauffeur instance;
    private ConnectionRequest req;
    
    private ServiceChauffeur() {
        req = new ConnectionRequest();
    }
    
    public static ServiceChauffeur getInstance() {
        if (instance == null) {
            instance = new ServiceChauffeur();
        }
        return instance;
    }
        
    public boolean addAnnoncef(Chauffeur l) {
        String url = Statics.BASE_URL + "/Addchauffeurjson/?cin=" + l.getCIN() + "&nom=" + l.getNom() + "&adresse=" + l.getAdresse()  ;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Chauffeur> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Chauffeur> annonces = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Chauffeur a = new Chauffeur();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int id_chauffeur = (int) Float.parseFloat(obj.get("idChauffeur").toString());
            a.setId_chauffeur(id_chauffeur);
            a.setCIN(obj.get("cin").toString());
            a.setNom(obj.get("nom").toString());
            a.setAdresse(obj.get("adresse").toString());
           
            
            
            annonces.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(annonces);
    return annonces;
}
public ArrayList<Chauffeur> getAllChauffeurs() {
    String url = Statics.BASE_URL + "/AllChauffeur";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parseAnnonces(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deleteChauffeur(int idf) {
    String url = Statics.BASE_URL + "/deletejson/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierChauffeur(Chauffeur l){
     
String url = Statics.BASE_URL + "/updatejson/" + l.getId_chauffeur() + "?cin=" + l.getCIN() + "&nom=" + l.getNom() + "&adresse=" + l.getAdresse()  ;
       // req.setUrl(url);
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;
            

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
}