/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import User.SessionManager;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Utilisateur;
import com.mycomany.utils.Statics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
        public boolean resultOK;

    
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField nom ,TextField prenom,TextField cin,TextField email,TextField telephone,TextField login,
            TextField password ,TextField confirmPassword,TextField region, TextField adresse,ComboBox<String> roles , 
            ComboBox<String> genre, Picker date_de_naissance) {
        
     
        date_de_naissance.setType(Display.PICKER_TYPE_DATE);
        date_de_naissance.setDate(new Date());
        
       

// Format the date as "yyyy-MM-dd"
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String formattedDate = dateFormat.format(date_de_naissance.getDate());

System.out.println("Selected date: " + formattedDate); // prints the selected date in the desired format

        
        String url = Statics.BASE_URL+"/addUserJSON?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&cin="+cin.getText().toString()+
                "&email="+email.getText().toString()+"&telephone="+telephone.getText().toString()+"&login="+login.getText().toString()+
                "&password="+password.getText().toString()+"&region="+region.getText().toString()+"&adresse="+adresse.getText().toString()+
                "&roles="+roles.getSelectedItem().toString()+"&genre="+genre.getSelectedItem().toString()+"&dateDeNaissance=" + formattedDate;
        
        req.setUrl(url);
                        System.out.println(url);

       
        if(nom.getText().equals(" ") && prenom.getText().equals(" ") && cin.getText().equals(" ")&& email.getText().equals(" ") && login.getText().equals(" ") && password.getText().equals(" ")
                && region.getText().equals(" ") && adresse.getText().equals(" ") && region.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        req.addResponseListener((e)-> {
         
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data); 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    //SignIn
    
    public void signin(TextField email,TextField password ) {
        
        
        String url = Statics.BASE_URL+"/LoginJSON?email="+email.getText().toString()+"&mot_de_passe="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        System.out.println(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
    //Session 
                System.out.println(user);
                float id = Float.parseFloat(user.get("id_user").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i            
               SessionManager.setNom(user.get("nom").toString());
               SessionManager.setEmail(user.get("email").toString());
SessionManager.setPrenom(user.get("prenom").toString());
SessionManager.setCin(user.get("cin").toString());
SessionManager.setTelephone(user.get("telephone").toString());
SessionManager.setLogin(user.get("login").toString());
//SessionManager.setDate_de_naissance(user.get("date_de_naissance").toString());
SessionManager.setRegion(user.get("region").toString());
SessionManager.setAdresse(user.get("adresse").toString());
//SessionManager.setCode_postal(Integer.parseInt(user.get("code_postal").toString()));
//SessionManager.setRole(user.get("roles").toString());
SessionManager.setGenre(user.get("genre").toString());
//SessionManager.setVerified(Boolean.parseBoolean(user.get("isVerified").toString()));

        
                    }
            }catch(Exception ex) {
                ex.printStackTrace();
            }

        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    


public boolean updateUser(Utilisateur user) {
    String url = Statics.BASE_URL + "/editprofil?id=" + user.getId_utilisateur() + "&email=" + user.getEmail() + "&password=" + user.getMot_de_passe() + "&telephone=" + user.getTelephone();
    req.setUrl(url);
    req.addResponseListener((e) -> {
        resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        String str = new String(req.getResponseData());
        System.out.println("data" + str);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

  //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email) {
        
        
        String url = Statics.BASE_URL+"/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }



}
