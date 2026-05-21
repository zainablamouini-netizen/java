package com.agence.automobile.model;

/**
 * Représente une voiture proposée par un vendeur.
 * Une voiture est identifiée, liée à un vendeur, et peut être disponible ou vendue.
 */
public class Voiture {
    private final int id;
    private String marque;
    private String modele;
    private int annee;
    private double prix;
    private boolean disponible;
    private final Vendeur vendeur;

    public Voiture(int id, String marque, String modele, int annee, double prix, Vendeur vendeur) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.prix = prix;
        this.disponible = true;
        this.vendeur = vendeur;
    }

    public int getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s (%d) - %.2f DH - disponible=%s - vendeur=%s",
                id, marque, modele, annee, prix, disponible ? "oui" : "non", vendeur.getNom());
    }
}
