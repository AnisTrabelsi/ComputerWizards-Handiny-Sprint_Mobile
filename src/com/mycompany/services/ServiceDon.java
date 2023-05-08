/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.don;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Dialog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceDon {

    private static ServiceDon instance = null;
    public ConnectionRequest req;
    public ArrayList<don> dons;

    public boolean resultOK;

    public static ServiceDon getInstance() {
        if (instance == null) {
            instance = new ServiceDon();
        }
        return instance;
    }

    private ServiceDon() {
        req = new ConnectionRequest();
    }

    public ArrayList<don> affichagedon() {
        ArrayList<don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/AlldonJSON";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdon = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdon.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        don d = new don();

                        float id_don = Float.parseFloat(obj.get("idDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String type = obj.get("type").toString();
                        String image_don = obj.get("imageDon").toString();
                        String description = obj.get("description").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateAjout").toString().substring(obj.get("dateAjout").toString().indexOf("2"), obj.get("dateAjout").toString().lastIndexOf("T"));

                        d.setId_don((int) id_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        d.setType(type);
                        d.setImage_don(image_don);
                        d.setDescription(description);
                        d.setDate_ajout(DateConverter);

//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));      
                        //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //            String dateString = formatter.format(currentTime);
                        //            v.setDate(dateString);
                        result.add(d);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }

    public boolean pdf() {
        // Create a new ConnectionRequest object
        ConnectionRequest request = new ConnectionRequest();
        // Set the URL of the request
        request.setUrl(Statics.BASE_URL + "/pdfjson");
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    public ArrayList<don> affichagedonUser(int $id) {
        ArrayList<don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/UserdonJSON/" + $id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdon = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdon.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        don d = new don();

                        float id_don = Float.parseFloat(obj.get("idDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String type = obj.get("type").toString();
                        String image_don = obj.get("imageDon").toString();
                        String description = obj.get("description").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateAjout").toString().substring(obj.get("dateAjout").toString().indexOf("2"), obj.get("dateAjout").toString().lastIndexOf("T"));

                        d.setId_don((int) id_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        d.setType(type);
                        d.setImage_don(image_don);
                        d.setDescription(description);
                        d.setDate_ajout(DateConverter);

//                        String DateConverter=obj.get("date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10 , obj.get("Date").toString().lastIndexOf("}"));      
                        //             Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        //             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        //            String dateString = formatter.format(currentTime);
                        //            v.setDate(dateString);
                        result.add(d);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }

    public boolean Adddon(don d) {
        com.mycomany.entities.Utilisateur u = new com.mycomany.entities.Utilisateur();
        u.setId_utilisateur(d.getId_utilisateur());
        String url = Statics.BASE_URL + "/AdddonJSON?type=" + d.getType() + "&imageDon=" + d.getImage_don() + "&description=" + d.getDescription() + "&idUtilisateur=" + d.getId_utilisateur();        //  String url = Statics.BASE_URL + "create";
        System.out.println(url);
        req.setPost(false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean Updatedons(don d) {
        String url = Statics.BASE_URL + "/UpdatedonJSON/" + d.getId_don() + "?type=" + d.getType() + "&imageDon=" + d.getImage_don() + "&description=" + d.getDescription();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<don> parsedons(String jsonText) {
        try {
            dons = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> donsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) donsListJson.get("root");
            for (Map<String, Object> obj : list) {
                don r = new don();
                float id_don = Float.parseFloat(obj.get("id_don").toString());

                dons.add(r);
            }

        } catch (IOException ex) {

        }
        return dons;
    }

    public ArrayList<don> parsedons2(String jsonText) {
        try {
            dons = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> donsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) donsListJson.get("root");
            for (Map<String, Object> obj : list) {
                don t = new don();
                float id_don = Float.parseFloat(obj.get("id_don").toString());
                t.setId_don((int) id_don);
                t.setType(obj.get("type").toString());
                if (obj.get("name") == null) {
                    t.setType("null");
                } else {
                    t.setType(obj.get("type").toString());
                }
                dons.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return dons;
    }

    public ArrayList<don> getAlldons() {
        String url = Statics.BASE_URL + "/AlldonJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                dons = parsedons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return dons;
    }

    public boolean deleteddon(int id) {

        String url = Statics.BASE_URL + "/DeletedonJSON/" + id + "";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public don Detaildon(int id) {
        don re = new don();
        String url = Statics.BASE_URL + "/detailDon/?id=" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                String DateConverter = obj.get("dateAjout").toString().substring(obj.get("dateAjout").toString().indexOf("2"), obj.get("dateAjout").toString().lastIndexOf("T"));

                re.setId_don((int) id);
                re.setType(obj.get("type").toString());
                re.setDescription(obj.get("description").toString());
                re.setImage_don(obj.get("imageDon").toString());
                re.setDate_ajout(DateConverter);

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return re;

    }
    
    
    
    public ArrayList<don> getVoituresByIds(ArrayList<Integer> dons) {
    ArrayList<don> result = new ArrayList<>();
    // Boucle sur les IDs de voitures et récupère chaque voiture individuellement
    for (int id : dons) {
        don d = getDonById(id);
        if (d != null) {
            result.add(d);
        }
    }
    return result;
}
    
  
    
    


public don getDonById(int iddon) {
    for (don d : affichagedon()) {
        if (d.getId_don() == iddon) {
            return d;
        }
    }
    return null; // La voiture n'a pas été trouvée
}

}
