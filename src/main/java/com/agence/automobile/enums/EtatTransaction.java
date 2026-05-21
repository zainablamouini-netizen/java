package com.agence.automobile.enums;

/**
 * États possibles pour une transaction entre acheteur et vendeur.
 */
public enum EtatTransaction {
    /** Transaction en cours de traitement. */
    EN_COURS,
    /** Transaction finalisée et conclue. */
    FINALISEE,
    /** Transaction annulée. */
    ANNULEE
}
