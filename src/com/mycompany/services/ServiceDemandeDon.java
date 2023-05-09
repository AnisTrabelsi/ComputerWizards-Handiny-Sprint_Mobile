/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.demande_don;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceDemandeDon {

    private static ServiceDemandeDon instance = null;
    public ConnectionRequest req;
    public ArrayList<demande_don> demande_dons;

    public boolean resultOK;

    public static ServiceDemandeDon getInstance() {
        if (instance == null) {
            instance = new ServiceDemandeDon();
        }
        return instance;
    }

    private ServiceDemandeDon() {
        req = new ConnectionRequest();
    }

    public ArrayList<demande_don> affichagedemande_donUser(int $id) {
        ArrayList<demande_don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/UserdemandedonJSON/" + $id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdemande_don = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdemande_don.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        demande_don d = new demande_don();

                        //float  id_don =  Float.parseFloat(obj.get("idDon").toString());
                        float  id_demande_don =  Float.parseFloat(obj.get("idDemandeDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String typeProduitDemande = obj.get("typeProduitDemande").toString();
                        String justificatifHandicap = obj.get("justificatifHandicap").toString();
                        String Remarques = obj.get("remarques").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateDemande").toString().substring(obj.get("dateDemande").toString().indexOf("2"), obj.get("dateDemande").toString().lastIndexOf("T"));
                        String etat = obj.get("etat").toString();

                         d.setId_demande_don((int) id_demande_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        //d.setId_don((int)id_don);
                        d.setType_produit_demande(typeProduitDemande);
                        d.setJustificatif_handicap(justificatifHandicap);
                        d.setRemarques(Remarques);
                        d.setDate_demande(DateConverter);
                        d.setEtat(etat);

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

    public boolean Adddemande_don(demande_don d) {
        //com.mycomany.entities.Utilisateur u = new com.mycomany.entities.Utilisateur();
      
        String url = Statics.BASE_URL + "/AdddemandedonJSON?idDon=" + d.getId_don() + "&justificatifHandicap=" + d.getJustificatif_handicap() + "&idUtilisateur=30"  + "&etat=" + d.getEtat() + "&remarques=" + d.getRemarques() + "&typeProduitDemande=" + d.getType_produit_demande();        //  String url = Statics.BASE_URL + "create";
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

    public boolean Updatedemande_dons(demande_don d) {
        String url = Statics.BASE_URL + "/Updatedemandedon/" + d.getId_demande_don() + "?remarques=" + d.getRemarques()+"&justificatifHandicap=" + d.getJustificatif_handicap()  ;        //  String url = Statics.BASE_URL + "create";
        req.setUrl(url);
         System.out.println(url);
         req.setPost(false);
        req.addResponseListener((e) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            String str = new String(req.getResponseData());
            System.out.println("data" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<demande_don> parsedemande_dons(String jsonText) {
        try {
            demande_dons = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> demande_donsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) demande_donsListJson.get("root");
            for (Map<String, Object> obj : list) {
                demande_don r = new demande_don();
                float id_demande_don = Float.parseFloat(obj.get("idDemandeDon").toString());
                /*  v.setId((int)id);
                v.setDestination(obj.get("Dest").toString());
                v.setNom_demande_don(obj.get("NomVoy").toString());
                v.setDuree_demande_don(obj.get("Duree").toString());
                v.setPrix_demande_don((int) obj.get("Prix"));
                v.setValabilite(obj.get("Valabilite").toString());
                v.setImage(obj.get("image").toString());*/
                demande_dons.add(r);
            }

        } catch (IOException ex) {

        }
        return demande_dons;
    }

    public ArrayList<demande_don> getAlldemande_dons() {
        String url = Statics.BASE_URL + "/Alldemande_donJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demande_dons = parsedemande_dons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demande_dons;
    }

    public boolean deleteddemande_don(int id) {

        String url = Statics.BASE_URL + "/DeletedemandedonJSON/" + id + "";
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

    
   public ArrayList<demande_don> order_By_typeJSON(int $id) 
     {
       ArrayList<demande_don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/ordertypeJSON/" + $id;
        req.setUrl(url);
         System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdemande_don = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdemande_don.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        demande_don d = new demande_don();

                        //float  id_don =  Float.parseFloat(obj.get("idDon").toString());
                        float  id_demande_don =  Float.parseFloat(obj.get("idDemandeDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String typeProduitDemande = obj.get("typeProduitDemande").toString();
                        String justificatifHandicap = obj.get("justificatifHandicap").toString();
                        String Remarques = obj.get("remarques").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateDemande").toString().substring(obj.get("dateDemande").toString().indexOf("2"), obj.get("dateDemande").toString().lastIndexOf("T"));
                        String etat = obj.get("etat").toString();

                         d.setId_demande_don((int) id_demande_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        //d.setId_don((int)id_don);
                        d.setType_produit_demande(typeProduitDemande);
                        d.setJustificatif_handicap(justificatifHandicap);
                        d.setRemarques(Remarques);
                        d.setDate_demande(DateConverter);
                        d.setEtat(etat);

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
    
    public ArrayList<demande_don> order_By_dateascJSON(int $id) 
     {
        ArrayList<demande_don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/orderdatecroissantJSON/" +$id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdemande_don = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdemande_don.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        demande_don d = new demande_don();

                        //float  id_don =  Float.parseFloat(obj.get("idDon").toString());
                        float  id_demande_don =  Float.parseFloat(obj.get("idDemandeDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String typeProduitDemande = obj.get("typeProduitDemande").toString();
                        String justificatifHandicap = obj.get("justificatifHandicap").toString();
                        String Remarques = obj.get("remarques").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateDemande").toString().substring(obj.get("dateDemande").toString().indexOf("2"), obj.get("dateDemande").toString().lastIndexOf("T"));
                        String etat = obj.get("etat").toString();

                         d.setId_demande_don((int) id_demande_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        //d.setId_don((int)id_don);
                        d.setType_produit_demande(typeProduitDemande);
                        d.setJustificatif_handicap(justificatifHandicap);
                        d.setRemarques(Remarques);
                        d.setDate_demande(DateConverter);
                        d.setEtat(etat);

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
    
    public ArrayList<demande_don> order_By_datedescJSON(int $id) 
     {
        ArrayList<demande_don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/orderdatedecroissantJSON/" + $id;
         System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdemande_don = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdemande_don.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        demande_don d = new demande_don();

                        //float  id_don =  Float.parseFloat(obj.get("idDon").toString());
                        float  id_demande_don =  Float.parseFloat(obj.get("idDemandeDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String typeProduitDemande = obj.get("typeProduitDemande").toString();
                        String justificatifHandicap = obj.get("justificatifHandicap").toString();
                        String Remarques = obj.get("remarques").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateDemande").toString().substring(obj.get("dateDemande").toString().indexOf("2"), obj.get("dateDemande").toString().lastIndexOf("T"));
                        String etat = obj.get("etat").toString();

                         d.setId_demande_don((int) id_demande_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        //d.setId_don((int)id_don);
                        d.setType_produit_demande(typeProduitDemande);
                        d.setJustificatif_handicap(justificatifHandicap);
                        d.setRemarques(Remarques);
                        d.setDate_demande(DateConverter);
                        d.setEtat(etat);

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
    
    public ArrayList<demande_don> voyagesearch(int $id,String s) 
     {
        ArrayList<demande_don> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/searchdemandedon/" + $id +"?searchjson="+s;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapdemande_don = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapdemande_don.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        demande_don d = new demande_don();

                        //float  id_don =  Float.parseFloat(obj.get("idDon").toString());
                        float  id_demande_don =  Float.parseFloat(obj.get("idDemandeDon").toString());
                        //float  id_utilisateur =  Float.parseFloat(obj.get("id_user").toString());
                        String typeProduitDemande = obj.get("typeProduitDemande").toString();
                        String justificatifHandicap = obj.get("justificatifHandicap").toString();
                        String Remarques = obj.get("remarques").toString();
                        // Date date_ajout = (Date) obj.get("dateAjout");
                        String DateConverter = obj.get("dateDemande").toString().substring(obj.get("dateDemande").toString().indexOf("2"), obj.get("dateDemande").toString().lastIndexOf("T"));
                        String etat = obj.get("etat").toString();

                         d.setId_demande_don((int) id_demande_don);
                        // d.setId_utilisateur((int) id_utilisateur);
                        //d.setId_don((int)id_don);
                        d.setType_produit_demande(typeProduitDemande);
                        d.setJustificatif_handicap(justificatifHandicap);
                        d.setRemarques(Remarques);
                        d.setDate_demande(DateConverter);
                        d.setEtat(etat);

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

    
      public void Qrcode(int $id) {
        String url = Statics.BASE_URL + "/QRcodeJSON/"+$id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demande_dons = parsedemande_dons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
    }

      
       public void PDF() {
        String url = Statics.BASE_URL + "/pdf/dons";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demande_dons = parsedemande_dons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
    }

}
