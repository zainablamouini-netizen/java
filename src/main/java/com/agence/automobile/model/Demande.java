package com.agence.automobile.model;

import com.agence.automobile.enums.EtatDemande;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une demande d'achat d'un acheteur, ainsi que les critères recherchés.
 * La demande peut être associée à plusieurs voitures candidate.
 */
public class Demande {
    private final int id;
    private final Acheteur acheteur;
    private final String criteres;
    private EtatDemande etat;
    private final List<Voiture> voituresAssociees;

    public Demande(int id, Acheteur acheteur, String criteres) {
        this.id = id;
        this.acheteur = acheteur;
        this.criteres = criteres;
        this.etat = EtatDemande.NOUVELLE;
        this.voituresAssociees = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Acheteur getAcheteur() {
        return acheteur;
    }

    public String getCriteres() {
        return criteres;
    }

    public EtatDemande getEtat() {
        return etat;
    }

    public void setEtat(EtatDemande etat) {
        this.etat = etat;
    }

    public List<Voiture> getVoituresAssociees() {
        return voituresAssociees;
    }

    public void ajouterVoiture(Voiture voiture) {
        if (!voituresAssociees.contains(voiture)) {
            voituresAssociees.add(voiture);
        }
    }

    @Override
    public String toString() {
        return String.format("Demande[%d] acheteur=%s criteres='%s' etat=%s voitures=%d",
                id, acheteur.getNom(), criteres, etat, voituresAssociees.size());
    }
}
