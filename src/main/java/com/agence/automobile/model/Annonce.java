package com.agence.automobile.model;

import com.agence.automobile.enums.EtatAnnonce;

/**
 * Représente une annonce de vente pour une voiture.
 * L'annonce est associée à une voiture et possède un état.
 */
public class Annonce {
    private final int id;
    private final Voiture voiture;
    private String description;
    private EtatAnnonce etat;

    public Annonce(int id, Voiture voiture, String description) {
        this.id = id;
        this.voiture = voiture;
        this.description = description;
        this.etat = EtatAnnonce.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EtatAnnonce getEtat() {
        return etat;
    }

    public void setEtat(EtatAnnonce etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return String.format("Annonce[%d] %s - %s - %s",
                id, voiture, description, etat);
    }
}
