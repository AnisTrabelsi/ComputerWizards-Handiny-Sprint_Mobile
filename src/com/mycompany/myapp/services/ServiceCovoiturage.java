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
import com.codename1.io.Storage;
import com.codename1.ui.events.ActionListener;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.mycompany.myapp.entities.Covoiturage;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abbes
 */
public class ServiceCovoiturage {
    
      public ArrayList<Covoiturage> cov;
    
    public static ServiceCovoiturage instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceCovoiturage() {
        req = new ConnectionRequest();
    }
    
    public static ServiceCovoiturage getInstance() {
        if (instance == null) {
            instance = new ServiceCovoiturage();
        }
        return instance;
    }
    
    public boolean addTask(Covoiturage t) {
        
        String depart = t.getDepart();
        String destination = t.getDestination();
        int prix = t.getPrix();
                int nbrplace = t.getNbrplace();
int id_utilisateur = t.getId_utilisateur() ; 
        float latitude = t.getLatitude();
              float longitude = t.getLongitude() ; 
      String nom = t.getNom();
        String telephone = t.getTelephone();
   //     int user = t.getUser() ; 
        String date_covoiturage = t.getDate_covoiturage();
        System.out.println(t);

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        // http://127.0.0.1:8000/covoiturage/addVoitureJSON?depart=%22radeqq%22&date_covoiturage=2023-04-08&prix=84&destination=%22deemej%22&id_utilisateur=29&nbrplace=27&user=27&longitude=27.3&latitude=27.0&nom=aaa&telephone=20108815
            String url = Statics.BASE_URL + "/addVoitureJSON?depart=" +depart+ "&destination=" + destination + "&prix=" + prix + "&nbrplace=" + nbrplace + "&id_utilisateur=" +29 +"&latitude=" + 36.895 + "&longitude=" + 10.1885 + "&nom="+ nom +  "&telephone=" + telephone + "&date_covoiturage=" + date_covoiturage ;

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
    
public ArrayList<Covoiturage> parseCovoiturages(String jsonText) {
     try {
   // ArrayList<Covoiturage> cov = new ArrayList<>();
   cov = new ArrayList<>() ; 
        JSONParser parser = new JSONParser();
        Map<String, Object> covoituragesJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) covoituragesJson.get("root");
        for (Map<String, Object> obj : list) {
            Covoiturage covoiturage = new Covoiturage();
            covoiturage.setId((int)Float.parseFloat(obj.get("id").toString()));
            covoiturage.setDepart(obj.get("depart").toString());
            covoiturage.setDestination(obj.get("destination").toString());
           covoiturage.setTelephone(obj.get("telephone").toString());
            covoiturage.setNom(obj.get("nom").toString());
            covoiturage.setPrix((int)Float.parseFloat(obj.get("Prix").toString()));
            covoiturage.setNbrplace((int)Float.parseFloat(obj.get("nbrplace").toString()));
            covoiturage.setLatitude(Float.parseFloat(obj.get("latitude").toString()));
            covoiturage.setLongitude(Float.parseFloat(obj.get("longitude").toString()));
        //    covoiturage.setUser((int)Float.parseFloat(obj.get("user").toString()));
            covoiturage.setDate_covoiturage(obj.get("date_covoiturage").toString());
          
            cov.add(covoiturage);
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return cov;
}
    
   public ArrayList<Covoiturage> getAllTasks() {
    String url = Statics.BASE_URL + "/listeVoituresMobileqqq";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (req.getResponseData() != null) {
                cov = parseCovoiturages(new String(req.getResponseData()));
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return cov;
}

    
    public boolean deleteTask(int id) {
        String url = Statics.BASE_URL + "/alllaaa/mmm/" + id;
       
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
    
    public boolean updateTask(Covoiturage t) {
        
   //     String url = Statics.BASE_URL + "/updateVoitureJSONs/sssss/"+t.getId()+"?name=" + t.getName() + "&description=" + t.getDescription() + "&state=" + t.isState() + "&priority=" + t.getPriority() + "&child=" +14;
                String url = Statics.BASE_URL + "/updateVoitureJSONs/sssss" +"?id="+t.getId()+"&depart=" + t.getDepart()+ "&destination=" + t.getDestination()+ "&prix=" + t.getPrix()+ "&nbrplace=" + t.getNbrplace()+ "&id_utilisateur=" +  29 +"&latitude=" + 36.895 + "&longitude=" + 10.1885 + "&nom="+ t.getNom() +  "&telephone=" + t.getTelephone()  + "&date_covoiturage=" + t.getDate_covoiturage() ;

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
    public ArrayList<Covoiturage> searchTask(String query) {
    ArrayList<Covoiturage> result = new ArrayList<>();
    for (Covoiturage covoiturage : cov) {
        if (covoiturage.getDepart().toLowerCase().contains(query.toLowerCase()) ||
            covoiturage.getDestination().toLowerCase().contains(query.toLowerCase()) 
           ) {
            result.add(covoiturage);
        }
    }
    return result;
}

    
    
    public Task getTaskById(int taskId) throws IOException {
        String url = "http://localhost:8000/tasks/display/{id}" + taskId;
        ConnectionRequest request = new ConnectionRequest(url);
        request.setHttpMethod("GET");
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        byte[] data = request.getResponseData();
        if (data == null) {
            return null;
        }
        
        String content = new String(data);
        JSONParser parser = new JSONParser();
        Map<String, Object> response = parser.parseJSON(new CharArrayReader(content.toCharArray()));
        
        Task task = new Task();
        task.setId((int) response.get("id"));
        task.setName((String) response.get("name"));
        task.setDescription((String) response.get("description"));
        task.setPriority((int) response.get("priority"));
        task.setState((boolean) response.get("state"));
        return task;
    }
    
      public void recupererpdf(Covoiturage colab) throws DocumentException, BadElementException, IOException {
    
            
    // Ouvrir le document PDF
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, Storage.getInstance().createOutputStream("cov.pdf"));
    document.open();

    // Ajouter le titre de la facture
    //Font fontTitre = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.GREEN);
    Font fontTitre = FontFactory.getFont(FontFactory.COURIER_BOLD, 25, new BaseColor(10, 150, 100));
    Chunk chunkTitre = new Chunk("Handiny                                      ", fontTitre);
    document.add(chunkTitre);
    
    LineSeparator line = new LineSeparator();
line.setLineColor(new BaseColor(10, 150, 100));
line.setLineWidth(2);
document.add(line);
    
    
  /*  Image image1 = Image.getInstance("C:\\Users\\farah\\OneDrive\\Desktop\\log.png"); 
image1.setAbsolutePosition(520, PageSize.A4.getHeight() - -120 - image1.getHeight());
image1.scaleToFit(100, 70);
document.add(image1);
*/
 /* Image image = Image.getInstance("C://Users//farah//.cn1//My project-1.jpg"); 
image.setAbsolutePosition(PageSize.A4.getWidth() - 200, 300);
document.add(image);
  
    */// Ajouter le tableau
    PdfPTable table = new PdfPTable(4); // Nombre de colonnes
    table.setWidthPercentage(100); // Largeur de la table
    table.setSpacingBefore(10f); // Espace avant la table
    table.setSpacingAfter(10f); // Espace après la table
    // Ajouter les cellules
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("Depart"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Date Covoiturage"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Destination"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Prix"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    // Ajouter les données du tableau
    // Récupérer la valeur du TextField
       

// Récupérer les données du tableau



    // Vérifier si le numéro de livraison correspond à celui entré dans le TextField
    
        table.addCell(colab.getDepart());
        table.addCell(colab.getDate_covoiturage().toString());

        table.addCell(colab.getDestination());
        table.addCell(String.valueOf(colab.getPrix()));

    // Centrer le tableau
    table.setTotalWidth(new float[] { 50f, 130f, 200f, 50f }); // ajustez les valeurs en fonction de vos besoins

    PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = canvas.getPdfDocument().getPageSize();
    //table.setTotalWidth(table.getTotalWidth());
    table.writeSelectedRows(0, -1, (rect.getLeft() + rect.getRight() - table.getTotalWidth()) / 2, rect.getTop() - table.getTotalHeight() - 200, canvas);

    
  
    
    // Ajouter une image en bas à droite de la page
/*Image image = Image.getInstance("C:\\Users\\farah\\.cn1\\My project-1.jpg");
image.setAbsolutePosition(PageSize.A4.getWidth() - 200, 300);
document.add(image);






*/PdfContentByte cb = writer.getDirectContent();
cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "Handiny", PageSize.A4.getWidth() - 20, 400, 0);
cb.endText();
/*
//PdfContentByte cb = writer.getDirectContent();
cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "Signature du Livreur : ", PageSize.A4.getWidth() - 399, 400, 0);
cb.endText();
*/
cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 200, 0)).getBaseFont(), 18);
cb.showTextAligned(Element.ALIGN_RIGHT, "FACTURE : ", PageSize.A4.getWidth() - 260, 700, 0);
cb.endText();
/*
cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "Signature du Client : ", PageSize.A4.getWidth() - 407, 350, 0);
cb.endText(); 

/*Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
Paragraph p = new Paragraph("BUGBUSTERS", font);
p.setAlignment(Element.ALIGN_RIGHT);
document.add(p);

   */ // Fermer le document PDF
   
    document.close();
     }
         
         
         
         
         
         
         
         
         
         
         
         
         
         
    
}
