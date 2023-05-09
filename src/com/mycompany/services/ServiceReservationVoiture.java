/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author Chaima
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Reservation_voiture;
import com.mycomany.entities.Voiture;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReservationVoiture {

    public ArrayList<Reservation_voiture> reservations;

    public static ServiceReservationVoiture instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservationVoiture() {
        req = new ConnectionRequest();
    }

    public static ServiceReservationVoiture getInstance() {
        if (instance == null) {
            instance = new ServiceReservationVoiture();
        }
        return instance;
    }
    
   
   

    

   



   public boolean addReservationVoiture(Reservation_voiture t) {

        int id_reservation_voiture=t.getId_reservation_voiture();
        String dateDeb = t.getString_debut_reservation();
        String dateFin = t.getString_fin_reservation();
        String dateDem=t.getString_demande_reservation();
        String des=t.getDescription_reservation();
        String etat=t.getEtat_demande_reservation();
        
        
        

        int id_user_par_defaut=32;
        int id_voiture_par_defaut=77;

        String url = "http://localhost/symfony/projects/Handiny_Symfony-12a56e8fc65c16e961a0ef6f85a0ecd6810d32db%20-%20Copie%20(2)/public/index.php" 
            + "/addReserveJSON?datedeb=" + dateDeb
            + "&datefin=" + dateFin + "&desc=" + des 
            + "&id_user=" + id_user_par_defaut  + "&id_voiture=" + id_voiture_par_defaut;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean ModifierReservationVoiture(Reservation_voiture t) {
        //int id_reservation_voiture=t.getId_reservation_voiture();
         int id_reservation_voiture=58;
        String dateDeb = t.getString_debut_reservation();
        String dateFin = t.getString_fin_reservation();
        String dateDem=t.getString_demande_reservation();
        String des=t.getDescription_reservation();
        String etat=t.getEtat_demande_reservation();
        
        
        

        int id_user_par_defaut=32;
        int id_voiture_par_defaut=77;

        String url = "http://localhost/symfony/projects/Handiny_Symfony-12a56e8fc65c16e961a0ef6f85a0ecd6810d32db%20-%20Copie%20(2)/public/index.php" 
            + "/updateReserveJSON?id_reservation="+id_reservation_voiture+"&datedeb=" + dateDeb
            + "&datefin=" + dateFin + "&desc=" + des 
            + "&id_user=" + id_user_par_defaut  + "&id_voiture=" + id_voiture_par_defaut;
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
    
     public ArrayList<Reservation_voiture> AffichageReservationsVoiture() {
        ArrayList<Reservation_voiture> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/listeReservationsMobile";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapvoitures = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapvoitures.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Reservation_voiture d = new  Reservation_voiture();

                        float  id_reserv_voiture =  Float.parseFloat(obj.get("id_reservation_voiture").toString());
                        
                       String desc= obj.get("description_reservation").toString();
                       String datedeb= obj.get("date_debut_reservation").toString();
                       String dateFin= obj.get("date_fin_reservation").toString();
                       String demande= obj.get("date_demande_reservation").toString();
                       String etat= obj.get("etat_demande_reservation").toString();
                       
                       
                        d.setId_reservation_voiture((int)id_reserv_voiture);
                        d.setString_debut_reservation(datedeb);
                        d.setString_fin_reservation(dateFin);
                        d.setDescription_reservation(desc);
                      d.setEtat_demande_reservation(etat);
                       
                       d.setString_demande_reservation(demande);
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
    
        public ArrayList<Voiture> affichageVoituresByUser(int $id) {
        ArrayList<Voiture> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/listeVoituresMobileByUser?id_user="+$id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapvoitures = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapvoitures.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Voiture d = new Voiture();

                        float  id_voiture =  Float.parseFloat(obj.get("id_voiture").toString());
                         float  prix_loc =  Float.parseFloat(obj.get("prix_location").toString());
                        String marque = obj.get("marque").toString();
                        String image_voiture = obj.get("image_voiture").toString();
                        String modele = obj.get("modele").toString();
                        String carburant = obj.get("carburant").toString();
                       String boite_vitesse= obj.get("boite_vitesse").toString();
                       String desc= obj.get("description").toString();
                       String date= obj.get("date_validation_technique").toString();
                       String kilom= obj.get("kilometrage").toString();
                       String imm= obj.get("immatriculation").toString();
                       
                       
                        d.setId_voiture((int) id_voiture);
                        d.setMarque(marque);
                        d.setImage_voiture(image_voiture);
                        d.setModele(modele);
                        d.setCarburant(carburant);
                        d.setDescription(desc);
                        d.setBoite_vitesse(boite_vitesse);
                        d.setCarburant(carburant);
                        d.setPrix_location(prix_loc);
                         d.setImmatriculation(imm);
                         d.setKilometrage(kilom);
                        d.setString_validation_technique(date);
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
        
         public ArrayList<Reservation_voiture> AffichageReservationsVoitureByUser(int $id) {
        ArrayList<Reservation_voiture> result = new ArrayList<>();
       
        String url = Statics.BASE_URL + "/listeVoituresMobileByUse?id_user="+ $id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapvoitures = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapvoitures.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Reservation_voiture d = new  Reservation_voiture();

                        float  id_reserv_voiture =  Float.parseFloat(obj.get("id_reservation_voiture").toString());
                        
                       String desc= obj.get("description_reservation").toString();
                       String datedeb= obj.get("date_debut_reservation").toString();
                       String dateFin= obj.get("date_fin_reservation").toString();
                       String demande= obj.get("date_demande_reservation").toString();
                       String etat= obj.get("etat_demande_reservation").toString();
                       
                       
                        d.setId_reservation_voiture((int)id_reserv_voiture);
                        d.setString_debut_reservation(datedeb);
                        d.setString_fin_reservation(dateFin);
                        d.setDescription_reservation(desc);
                      d.setEtat_demande_reservation(etat);
                       
                       d.setString_demande_reservation(demande);
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

    public boolean deleteReservationVoiture(int reservation_id ) {
         String url = Statics.BASE_URL + "/deleteReservJSON?id_reservation=" + reservation_id+ "";
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
     
    
    

  

    



}

