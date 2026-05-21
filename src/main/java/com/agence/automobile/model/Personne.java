package com.agence.automobile.model;

/**
 * Modèle de base pour une personne de l'agence.
 * Cette classe est héritée par Vendeur, Acheteur et Agent.
 */
public abstract class Personne {
    private final int id;
    private String nom;
    private String telephone;

    protected Personne(int id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    /**
     * Identifiant unique de la personne.
     */
    public int getId() {
        return id;
    }

    /**
     * Nom complet de la personne.
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s", id, nom, telephone);
    }
}
