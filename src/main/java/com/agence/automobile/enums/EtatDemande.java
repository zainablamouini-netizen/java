package com.agence.automobile.enums;

/**
 * États possibles pour une demande d'achat d'un acheteur.
 */
public enum EtatDemande {
    /** Demande enregistrée mais pas encore traitée. */
    NOUVELLE,
    /** Demande en cours de traitement. */
    EN_COURS,
    /** Demande traitée avec succès. */
    TRAITE,
    /** Demande annulée. */
    ANNULEE
}
