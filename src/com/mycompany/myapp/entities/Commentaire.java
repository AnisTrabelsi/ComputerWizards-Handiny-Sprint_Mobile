/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author bengh
 */
public class Commentaire {
    private int idCommentaire;
    private String contenuCommentaire;
    private String piecejointe;
    private int nbMentions;
    private Date datePublication;
    private int id_utilisateur, idSujet;
    
    
    public Commentaire(int idCommentaire, String contenuCommentaire, String piecejointe, int nbMentions, Date datePublication, int id_utilisateur, int idSujet) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.piecejointe = piecejointe;
        this.nbMentions = nbMentions;
        this.datePublication = datePublication;
        this.id_utilisateur = id_utilisateur;
        this.idSujet = idSujet;
    }

    public Commentaire(String contenuCommentaire, String piecejointe, int nbMentions, Date datePublication, int id_utilisateur, int idSujet) {
        this.contenuCommentaire = contenuCommentaire;
        this.piecejointe = piecejointe;
        this.nbMentions = nbMentions;
        this.datePublication = datePublication;
        this.id_utilisateur = id_utilisateur;
        this.idSujet = idSujet;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public String getPiecejointe() {
        return piecejointe;
    }

    public int getNbMentions() {
        return nbMentions;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getIdSujet() {
        return idSujet;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public void setPiecejointe(String piecejointe) {
        this.piecejointe = piecejointe;
    }

    public void setNbMentions(int nbMentions) {
        this.nbMentions = nbMentions;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", contenuCommentaire=" + contenuCommentaire + ", piecejointe=" + piecejointe + ", nbMentions=" + nbMentions + ", datePublication=" + datePublication + ", id_utilisateur=" + id_utilisateur + ", idSujet=" + idSujet + '}';
    }
    
    
}
