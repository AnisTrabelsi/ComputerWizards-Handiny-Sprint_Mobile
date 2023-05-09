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
public class ServiceVoiture {

    public ArrayList<Voiture> voitures;

    public static ServiceVoiture instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceVoiture() {
        req = new ConnectionRequest();
    }

    public static ServiceVoiture getInstance() {
        if (instance == null) {
            instance = new ServiceVoiture();
        }
        return instance;
    }
   
   

    

   



   public boolean addVoiture(Voiture t) {

        //int id_voiture=t.getId_voiture();
        String immatriculation = t.getImmatriculation();
        String marque = t.getMarque(); 
        String modele=t.getModele();
        String boite_vitesse=t.getBoite_vitesse();
        String kilometrage=t.getKilometrage();
        String carburant=t.getCarburant();
        float prix_location=t.getPrix_location();
        String image_voiture=t.getImage_voiture();
        String description=t.getDescription();
        String date_validation_technique=t.getString_validation_technique();
        
        

        int id_user_par_defaut=32;

        String url = "http://localhost/symfony/projects/Handiny_Symfony-12a56e8fc65c16e961a0ef6f85a0ecd6810d32db%20-%20Copie%20(2)/public/index.php/" 
            + "addVoitureJSON?immatriculation=" + immatriculation 
            + "&marque=" + marque + "&modele=" + modele + "&boite_vitesse=" + boite_vitesse 
            + "&kilometrage=" + kilometrage + "&carburant=" + carburant 
            + "&image_voiture=" + image_voiture   + "&prix_location=" + prix_location
            + "&description=" + description +"&date_validation_technique="+ date_validation_technique
            + "&id_user=" + id_user_par_defaut;
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

    
    
    public ArrayList<Voiture> affichageVoiture() {
        ArrayList<Voiture> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/listeVoituresMobile";
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
    
    
    public ArrayList<Voiture> getVoituresByIds(ArrayList<Integer> voitureIds) {
    ArrayList<Voiture> result = new ArrayList<>();
    // Boucle sur les IDs de voitures et récupère chaque voiture individuellement
    for (int id : voitureIds) {
        Voiture voiture = getVoitureById(id);
        if (voiture != null) {
            result.add(voiture);
        }
    }
    return result;
}
    
    
    


public Voiture getVoitureById(int voitureId) {
    for (Voiture voiture : affichageVoiture()) {
        if (voiture.getId_voiture() == voitureId) {
            return voiture;
        }
    }
    return null; // La voiture n'a pas été trouvée
}

    
     public ArrayList<Voiture> parsevoitures(String jsonText) {
        try {
            voitures = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> donsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) donsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Voiture r = new Voiture();
                float id_voiture = Float.parseFloat(obj.get("id_voiture").toString());
                /*  v.setId((int)id);
                v.setDestination(obj.get("Dest").toString());
                v.setNom_Voyage(obj.get("NomVoy").toString());
                v.setDuree_Voyage(obj.get("Duree").toString());
                v.setPrix_Voyage((int) obj.get("Prix"));
                v.setValabilite(obj.get("Valabilite").toString());
                v.setImage(obj.get("image").toString());*/
                voitures.add(r);
            }

        } catch (IOException ex) {

        }
        return voitures;
    }

      public boolean deleteVoiture(int id_voiture) {

        String url = Statics.BASE_URL + "/deleteVoitureJSON?id=" + id_voiture + "";
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

   
    public boolean UpdateVoitures(Voiture t){
        
       int id_voiture=t.getId_voiture();
        String immatriculation = t.getImmatriculation();
        String marque = t.getMarque(); 
        String modele=t.getModele();
        String boite_vitesse=t.getBoite_vitesse();
        String kilometrage=t.getKilometrage();
        String carburant=t.getCarburant();
        float prix_location=t.getPrix_location();
        String image_voiture=t.getImage_voiture();
        String description=t.getDescription();
        String date_validation_technique=t.getString_validation_technique();
        
        

        int id_user_par_defaut=32;

        String url = "http://localhost/symfony/projects/Handiny_Symfony-12a56e8fc65c16e961a0ef6f85a0ecd6810d32db%20-%20Copie%20(2)/public/index.php/" 
            + "updateVoitureJSON?id="+id_voiture+"&immatriculation=" + immatriculation 
            + "&marque=" + marque + "&modele=" + modele + "&boite_vitesse=" + boite_vitesse 
            + "&kilometrage=" + kilometrage + "&carburant=" + carburant 
            + "&image_voiture=" + image_voiture   + "&prix_location=" + prix_location
            + "&description=" + description +"&date_validation_technique="+ date_validation_technique
            + "&id_user=" + id_user_par_defaut;
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


}

