/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author bengh
 */
public class Sujet {
    private int idSujet;
    private String titreSujet, contenuSujet, etat, tags;
    private Date dateCreationSujet;
    private int nbCommentaires, id_categorie, id_utilisateur;

    public Sujet(int idSujet, String titreSujet, String contenuSujet, String etat, String tags, Date dateCreationSujet, int nbCommentaires, int id_categorie, int id_utilisateur) {
        this.idSujet = idSujet;
        this.titreSujet = titreSujet;
        this.contenuSujet = contenuSujet;
        this.etat = etat;
        this.tags = tags;
        this.dateCreationSujet = dateCreationSujet;
        this.nbCommentaires = nbCommentaires;
        this.id_categorie = id_categorie;
        this.id_utilisateur = id_utilisateur;
    }

    public Sujet(String titreSujet, String contenuSujet, String etat, String tags, Date dateCreationSujet, int nbCommentaires, int id_categorie, int id_utilisateur) {
        this.titreSujet = titreSujet;
        this.contenuSujet = contenuSujet;
        this.etat = etat;
        this.tags = tags;
        this.dateCreationSujet = dateCreationSujet;
        this.nbCommentaires = nbCommentaires;
        this.id_categorie = id_categorie;
        this.id_utilisateur = id_utilisateur;
    }

    public Sujet() {
    }

    public int getIdSujet() {
        return idSujet;
    }

    public String getTitreSujet() {
        return titreSujet;
    }

    public String getContenuSujet() {
        return contenuSujet;
    }

    public String getEtat() {
        return etat;
    }

    public String getTags() {
        return tags;
    }

    public Date getDateCreationSujet() {
        return dateCreationSujet;
    }

    public int getNbCommentaires() {
        return nbCommentaires;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setTitreSujet(String titreSujet) {
        this.titreSujet = titreSujet;
    }

    public void setContenuSujet(String contenuSujet) {
        this.contenuSujet = contenuSujet;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setDateCreationSujet(Date dateCreationSujet) {
        this.dateCreationSujet = dateCreationSujet;
    }

    public void setNbCommentaires(int nbCommentaires) {
        this.nbCommentaires = nbCommentaires;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }    

    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    
    @Override
    public String toString() {
        return "Sujet{" + "titreSujet=" + titreSujet + ", contenuSujet=" + contenuSujet + ", etat=" + etat + ", tags=" + tags + ", dateCreationSujet=" + dateCreationSujet + ", nbCommentaires=" + nbCommentaires + ", id_categorie=" + id_categorie + ", id_utilisateur=" + id_utilisateur + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sujet other = (Sujet) obj;
        if (this.idSujet != other.idSujet) {
            return false;
        }
        if (this.nbCommentaires != other.nbCommentaires) {
            return false;
        }
        if (this.id_categorie != other.id_categorie) {
            return false;
        }
        if (this.id_utilisateur != other.id_utilisateur) {
            return false;
        }
        if (!Objects.equals(this.titreSujet, other.titreSujet)) {
            return false;
        }
        if (!Objects.equals(this.contenuSujet, other.contenuSujet)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        if (!Objects.equals(this.dateCreationSujet, other.dateCreationSujet)) {
            return false;
        }
        return true;
    }
    
    
}
