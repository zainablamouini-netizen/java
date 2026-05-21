package com.agence.automobile.model;

import com.agence.automobile.enums.EtatTransaction;

/**
 * Représente une transaction conclue entre un acheteur et un vendeur.
 * Une transaction contient le montant, l'état et les frais de médiation.
 */
public class Transaction {
    private final int id;
    private final Annonce annonce;
    private final Acheteur acheteur;
    private final Agent agent;
    private final double montant;
    private EtatTransaction etat;

    public Transaction(int id, Annonce annonce, Acheteur acheteur, Agent agent, double montant) {
        this.id = id;
        this.annonce = annonce;
        this.acheteur = acheteur;
        this.agent = agent;
        this.montant = montant;
        this.etat = EtatTransaction.EN_COURS;
    }

    public int getId() {
        return id;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public Acheteur getAcheteur() {
        return acheteur;
    }

    public Agent getAgent() {
        return agent;
    }

    public double getMontant() {
        return montant;
    }

    public EtatTransaction getEtat() {
        return etat;
    }

    public void setEtat(EtatTransaction etat) {
        this.etat = etat;
    }

    /**
     * Calcule les frais de médiation à 5 % du montant de la transaction.
     */
    public double calculerFraisMediation() {
        return montant * 0.05;
    }

    @Override
    public String toString() {
        return String.format("Transaction[%d] annonce=%d acheteur=%s agent=%s montant=%.2f etat=%s frais=%.2f",
                id, annonce.getId(), acheteur.getNom(), agent.getNom(), montant, etat, calculerFraisMediation());
    }
}
