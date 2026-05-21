package com.agence.automobile.service;

import com.agence.automobile.enums.EtatAnnonce;
import com.agence.automobile.enums.EtatDemande;
import com.agence.automobile.enums.EtatTransaction;
import com.agence.automobile.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Service métier de l'agence de médiation automobile.
 * Cette classe gère les opérations sur les personnes, voitures, annonces, demandes et transactions.
 */
public class AgenceService {
    // Listes d'entités stockées en mémoire
    private final List<Vendeur> vendeurs = new ArrayList<>();
    private final List<Acheteur> acheteurs = new ArrayList<>();
    private final List<Agent> agents = new ArrayList<>();
    private final List<Voiture> voitures = new ArrayList<>();
    private final List<Annonce> annonces = new ArrayList<>();
    private final List<Demande> demandes = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    // Compteurs pour générer des identifiants uniques
    private int nextPersonneId = 1;
    private int nextVoitureId = 1;
    private int nextAnnonceId = 1;
    private int nextDemandeId = 1;
    private int nextTransactionId = 1;

    /**
     * Menu de gestion des personnes : vendeurs, acheteurs et agents.
     */
    public void handlePersonManagement(Scanner scanner) {
        boolean boucle = true;
        while (boucle) {
            System.out.println("\n-- Gestion des personnes --");
            System.out.println("1. Ajouter un vendeur");
            System.out.println("2. Ajouter un acheteur");
            System.out.println("3. Ajouter un agent");
            System.out.println("4. Lister les personnes");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> ajouterVendeur(scanner);
                case "2" -> ajouterAcheteur(scanner);
                case "3" -> ajouterAgent(scanner);
                case "4" -> listerPersonnes();
                case "0" -> boucle = false;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Menu de gestion des voitures.
     */
    public void handleVoitureManagement(Scanner scanner) {
        boolean boucle = true;
        while (boucle) {
            System.out.println("\n-- Gestion des voitures --");
            System.out.println("1. Ajouter une voiture");
            System.out.println("2. Lister les voitures");
            System.out.println("3. Rechercher une voiture");
            System.out.println("4. Modifier une voiture");
            System.out.println("5. Supprimer une voiture non disponible");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> ajouterVoiture(scanner);
                case "2" -> listerVoitures();
                case "3" -> rechercherVoiture(scanner);
                case "4" -> modifierVoiture(scanner);
                case "5" -> supprimerVoitureNonDisponible(scanner);
                case "0" -> boucle = false;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Menu de gestion des annonces.
     */
    public void handleAnnonceManagement(Scanner scanner) {
        boolean boucle = true;
        while (boucle) {
            System.out.println("\n-- Gestion des annonces --");
            System.out.println("1. Créer une annonce");
            System.out.println("2. Afficher les annonces");
            System.out.println("3. Modifier une annonce");
            System.out.println("4. Désactiver une annonce");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> creerAnnonce(scanner);
                case "2" -> afficherAnnonces();
                case "3" -> modifierAnnonce(scanner);
                case "4" -> desactiverAnnonce(scanner);
                case "0" -> boucle = false;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Menu de gestion des demandes.
     */
    public void handleDemandeManagement(Scanner scanner) {
        boolean boucle = true;
        while (boucle) {
            System.out.println("\n-- Gestion des demandes --");
            System.out.println("1. Enregistrer une demande");
            System.out.println("2. Associer une voiture à une demande");
            System.out.println("3. Consulter les demandes");
            System.out.println("4. Modifier l'état d'une demande");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> enregistrerDemande(scanner);
                case "2" -> associerVoitureADemande(scanner);
                case "3" -> consulterDemandes();
                case "4" -> modifierEtatDemande(scanner);
                case "0" -> boucle = false;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Menu de gestion des transactions.
     */
    public void handleTransactionManagement(Scanner scanner) {
        boolean boucle = true;
        while (boucle) {
            System.out.println("\n-- Gestion des transactions --");
            System.out.println("1. Créer une transaction");
            System.out.println("2. Suivre l'état d'une transaction");
            System.out.println("3. Finaliser ou annuler une transaction");
            System.out.println("0. Retour");
            System.out.print("Choix : ");
            switch (scanner.nextLine().trim()) {
                case "1" -> creerTransaction(scanner);
                case "2" -> suivreTransaction(scanner);
                case "3" -> modifierEtatTransaction(scanner);
                case "0" -> boucle = false;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    /**
     * Ajoute un nouveau vendeur dans la liste.
     */
    private void ajouterVendeur(Scanner scanner) {
        System.out.print("Nom du vendeur : ");
        String nom = scanner.nextLine().trim();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine().trim();
        Vendeur vendeur = new Vendeur(nextPersonneId++, nom, telephone);
        vendeurs.add(vendeur);
        System.out.println("Vendeur ajouté : " + vendeur);
    }

    /**
     * Ajoute un nouvel acheteur dans la liste.
     */
    private void ajouterAcheteur(Scanner scanner) {
        System.out.print("Nom de l'acheteur : ");
        String nom = scanner.nextLine().trim();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine().trim();
        Acheteur acheteur = new Acheteur(nextPersonneId++, nom, telephone);
        acheteurs.add(acheteur);
        System.out.println("Acheteur ajouté : " + acheteur);
    }

    /**
     * Ajoute un nouvel agent dans la liste.
     */
    private void ajouterAgent(Scanner scanner) {
        System.out.print("Nom de l'agent : ");
        String nom = scanner.nextLine().trim();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine().trim();
        Agent agent = new Agent(nextPersonneId++, nom, telephone);
        agents.add(agent);
        System.out.println("Agent ajouté : " + agent);
    }

    /**
     * Affiche toutes les personnes enregistrées dans l'agence.
     */
    private void listerPersonnes() {
        System.out.println("\nVendeurs :");
        vendeurs.forEach(System.out::println);
        System.out.println("\nAcheteurs :");
        acheteurs.forEach(System.out::println);
        System.out.println("\nAgents :");
        agents.forEach(System.out::println);
    }

    /**
     * Ajoute une voiture associée à un vendeur existant.
     */
    private void ajouterVoiture(Scanner scanner) {
        if (vendeurs.isEmpty()) {
            System.out.println("Aucun vendeur disponible. Ajoutez d'abord un vendeur.");
            return;
        }
        System.out.println("Sélectionnez un vendeur :");
        vendeurs.forEach(v -> System.out.println(v.getId() + ". " + v.getNom()));
        int vendeurId = lireEntier(scanner, "Id du vendeur : ");
        Vendeur vendeur = trouverVendeur(vendeurId);
        if (vendeur == null) {
            System.out.println("Vendeur introuvable.");
            return;
        }
        System.out.print("Marque : ");
        String marque = scanner.nextLine().trim();
        System.out.print("Modèle : ");
        String modele = scanner.nextLine().trim();
        int annee = lireEntier(scanner, "Année : ");
        double prix = lireDouble(scanner, "Prix : ");
        Voiture voiture = new Voiture(nextVoitureId++, marque, modele, annee, prix, vendeur);
        voitures.add(voiture);
        System.out.println("Voiture ajoutée : " + voiture);
    }

    /**
     * Affiche toutes les voitures enregistrées.
     */
    private void listerVoitures() {
        if (voitures.isEmpty()) {
            System.out.println("Aucune voiture enregistrée.");
        } else {
            voitures.forEach(System.out::println);
        }
    }

    /**
     * Recherche une voiture par marque ou modèle.
     */
    private void rechercherVoiture(Scanner scanner) {
        System.out.print("Critère de recherche (marque/modèle) : ");
        String critere = scanner.nextLine().trim().toLowerCase();
        voitures.stream()
                .filter(v -> v.getMarque().toLowerCase().contains(critere)
                        || v.getModele().toLowerCase().contains(critere))
                .forEach(System.out::println);
    }

    /**
     * Modifie les caractéristiques d'une voiture existante.
     */
    private void modifierVoiture(Scanner scanner) {
        int id = lireEntier(scanner, "Id de la voiture à modifier : ");
        Optional<Voiture> optionalVoiture = voitures.stream().filter(v -> v.getId() == id).findFirst();
        if (optionalVoiture.isEmpty()) {
            System.out.println("Voiture introuvable.");
            return;
        }
        Voiture voiture = optionalVoiture.get();
        System.out.print("Nouvelle marque (laisser vide pour garder) : ");
        String marque = scanner.nextLine().trim();
        if (!marque.isEmpty()) {
            voiture.setMarque(marque);
        }
        System.out.print("Nouveau modèle (laisser vide pour garder) : ");
        String modele = scanner.nextLine().trim();
        if (!modele.isEmpty()) {
            voiture.setModele(modele);
        }
        System.out.print("Nouvelle année (0 pour garder) : ");
        int annee = lireEntier(scanner, "Année : ");
        if (annee > 0) {
            voiture.setAnnee(annee);
        }
        System.out.print("Nouveau prix (-1 pour garder) : ");
        double prix = lireDouble(scanner, "Prix : ");
        if (prix >= 0) {
            voiture.setPrix(prix);
        }
        System.out.println("Voiture mise à jour : " + voiture);
    }

    /**
     * Supprime une voiture si elle n'est plus disponible.
     */
    private void supprimerVoitureNonDisponible(Scanner scanner) {
        int id = lireEntier(scanner, "Id de la voiture à supprimer : ");
        Optional<Voiture> optionalVoiture = voitures.stream().filter(v -> v.getId() == id).findFirst();
        if (optionalVoiture.isEmpty()) {
            System.out.println("Voiture introuvable.");
            return;
        }
        Voiture voiture = optionalVoiture.get();
        if (voiture.isDisponible()) {
            System.out.println("La voiture est encore disponible; elle ne peut pas être supprimée.");
            return;
        }
        voitures.remove(voiture);
        System.out.println("Voiture supprimée : " + voiture);
    }

    /**
     * Crée une annonce pour une voiture existante.
     */
    private void creerAnnonce(Scanner scanner) {
        if (voitures.isEmpty()) {
            System.out.println("Aucune voiture enregistrée pour créer une annonce.");
            return;
        }
        System.out.println("Sélectionnez une voiture :");
        voitures.forEach(v -> System.out.println(v.getId() + ". " + v));
        int voitureId = lireEntier(scanner, "Id de la voiture : ");
        Optional<Voiture> optionalVoiture = voitures.stream().filter(v -> v.getId() == voitureId).findFirst();
        if (optionalVoiture.isEmpty()) {
            System.out.println("Voiture introuvable.");
            return;
        }
        Voiture voiture = optionalVoiture.get();
        System.out.print("Description de l'annonce : ");
        String description = scanner.nextLine().trim();
        Annonce annonce = new Annonce(nextAnnonceId++, voiture, description);
        annonces.add(annonce);
        System.out.println("Annonce créée : " + annonce);
    }

    /**
     * Affiche toutes les annonces enregistrées.
     */
    private void afficherAnnonces() {
        if (annonces.isEmpty()) {
            System.out.println("Aucune annonce disponible.");
        } else {
            annonces.forEach(System.out::println);
        }
    }

    /**
     * Modifie la description ou l'état d'une annonce.
     */
    private void modifierAnnonce(Scanner scanner) {
        int id = lireEntier(scanner, "Id de l'annonce à modifier : ");
        Optional<Annonce> optionalAnnonce = annonces.stream().filter(a -> a.getId() == id).findFirst();
        if (optionalAnnonce.isEmpty()) {
            System.out.println("Annonce introuvable.");
            return;
        }
        Annonce annonce = optionalAnnonce.get();
        System.out.print("Nouvelle description : ");
        String description = scanner.nextLine().trim();
        if (!description.isEmpty()) {
            annonce.setDescription(description);
        }
        System.out.print("Nouvel état (ACTIVE/DESACTIVE/VENDUE) : ");
        String etatString = scanner.nextLine().trim().toUpperCase();
        try {
            if (!etatString.isEmpty()) {
                annonce.setEtat(EtatAnnonce.valueOf(etatString));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("État invalide, définition ignorée.");
        }
        System.out.println("Annonce mise à jour : " + annonce);
    }

    /**
     * Désactive une annonce existante.
     */
    private void desactiverAnnonce(Scanner scanner) {
        int id = lireEntier(scanner, "Id de l'annonce à désactiver : ");
        Optional<Annonce> optionalAnnonce = annonces.stream().filter(a -> a.getId() == id).findFirst();
        if (optionalAnnonce.isEmpty()) {
            System.out.println("Annonce introuvable.");
            return;
        }
        Annonce annonce = optionalAnnonce.get();
        annonce.setEtat(EtatAnnonce.DESACTIVE);
        System.out.println("Annonce désactivée : " + annonce);
    }

    /**
     * Enregistre une demande d'acheteur avec ses critères.
     */
    private void enregistrerDemande(Scanner scanner) {
        if (acheteurs.isEmpty()) {
            System.out.println("Aucun acheteur enregistré. Ajoutez d'abord un acheteur.");
            return;
        }
        System.out.println("Sélectionnez un acheteur :");
        acheteurs.forEach(a -> System.out.println(a.getId() + ". " + a.getNom()));
        int acheteurId = lireEntier(scanner, "Id de l'acheteur : ");
        Acheteur acheteur = trouverAcheteur(acheteurId);
        if (acheteur == null) {
            System.out.println("Acheteur introuvable.");
            return;
        }
        System.out.print("Critères de recherche : ");
        String criteres = scanner.nextLine().trim();
        Demande demande = new Demande(nextDemandeId++, acheteur, criteres);
        demandes.add(demande);
        System.out.println("Demande enregistrée : " + demande);
    }

    /**
     * Associe une voiture à une demande existante.
     */
    private void associerVoitureADemande(Scanner scanner) {
        if (demandes.isEmpty() || voitures.isEmpty()) {
            System.out.println("Il faut au moins une demande et une voiture pour réaliser cette opération.");
            return;
        }
        int demandeId = lireEntier(scanner, "Id de la demande : ");
        Optional<Demande> optionalDemande = demandes.stream().filter(d -> d.getId() == demandeId).findFirst();
        if (optionalDemande.isEmpty()) {
            System.out.println("Demande introuvable.");
            return;
        }
        Demande demande = optionalDemande.get();
        System.out.println("Sélectionnez une voiture :");
        voitures.forEach(v -> System.out.println(v.getId() + ". " + v));
        int voitureId = lireEntier(scanner, "Id de la voiture : ");
        Optional<Voiture> optionalVoiture = voitures.stream().filter(v -> v.getId() == voitureId).findFirst();
        if (optionalVoiture.isEmpty()) {
            System.out.println("Voiture introuvable.");
            return;
        }
        demande.ajouterVoiture(optionalVoiture.get());
        System.out.println("Voiture associée à la demande : " + demande);
    }

    /**
     * Affiche toutes les demandes enregistrées.
     */
    private void consulterDemandes() {
        if (demandes.isEmpty()) {
            System.out.println("Aucune demande enregistrée.");
        } else {
            demandes.forEach(System.out::println);
        }
    }

    /**
     * Change l'état d'une demande existante.
     */
    private void modifierEtatDemande(Scanner scanner) {
        int id = lireEntier(scanner, "Id de la demande : ");
        Optional<Demande> optionalDemande = demandes.stream().filter(d -> d.getId() == id).findFirst();
        if (optionalDemande.isEmpty()) {
            System.out.println("Demande introuvable.");
            return;
        }
        Demande demande = optionalDemande.get();
        System.out.print("Nouvel état (NOUVELLE/EN_COURS/TRAITE/ANNULEE) : ");
        String EtatString = scanner.nextLine().trim().toUpperCase();
        try {
            demande.setEtat(EtatDemande.valueOf(EtatString));
            System.out.println("Etat de la demande mis à jour: " + demande);
        } catch (IllegalArgumentException e) {
            System.out.println("État invalide.");
        }
    }

    /**
     * Crée une transaction liée à une annonce, un acheteur et un agent.
     */
    private void creerTransaction(Scanner scanner) {
        if (annonces.isEmpty() || acheteurs.isEmpty() || agents.isEmpty()) {
            System.out.println("Ajoutez au moins une annonce, un acheteur et un agent pour créer une transaction.");
            return;
        }
        System.out.println("Sélectionnez une annonce :");
        annonces.forEach(a -> System.out.println(a.getId() + ". " + a));
        int annonceId = lireEntier(scanner, "Id de l'annonce : ");
        Optional<Annonce> optionalAnnonce = annonces.stream().filter(a -> a.getId() == annonceId).findFirst();
        if (optionalAnnonce.isEmpty()) {
            System.out.println("Annonce introuvable.");
            return;
        }
        Annonce annonce = optionalAnnonce.get();
        System.out.println("Sélectionnez un acheteur :");
        acheteurs.forEach(a -> System.out.println(a.getId() + ". " + a.getNom()));
        int acheteurId = lireEntier(scanner, "Id de l'acheteur : ");
        Acheteur acheteur = trouverAcheteur(acheteurId);
        if (acheteur == null) {
            System.out.println("Acheteur introuvable.");
            return;
        }
        System.out.println("Sélectionnez un agent :");
        agents.forEach(a -> System.out.println(a.getId() + ". " + a.getNom()));
        int agentId = lireEntier(scanner, "Id de l'agent : ");
        Agent agent = trouverAgent(agentId);
        if (agent == null) {
            System.out.println("Agent introuvable.");
            return;
        }
        double montant = lireDouble(scanner, "Montant de la transaction : ");
        Transaction transaction = new Transaction(nextTransactionId++, annonce, acheteur, agent, montant);
        transactions.add(transaction);
        System.out.println("Transaction créée : " + transaction);
    }

    /**
     * Affiche toutes les transactions enregistrées.
     */
    private void suivreTransaction(Scanner scanner) {
        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction enregistrée.");
            return;
        }
        transactions.forEach(System.out::println);
    }

    /**
     * Modifie l'état d'une transaction et marque la voiture comme vendue si la transaction est finalisée.
     */
    private void modifierEtatTransaction(Scanner scanner) {
        int id = lireEntier(scanner, "Id de la transaction : ");
        Optional<Transaction> optionalTransaction = transactions.stream().filter(t -> t.getId() == id).findFirst();
        if (optionalTransaction.isEmpty()) {
            System.out.println("Transaction introuvable.");
            return;
        }
        Transaction transaction = optionalTransaction.get();
        System.out.print("Nouvel état (EN_COURS/FINALISEE/ANNULEE) : ");
        String etatString = scanner.nextLine().trim().toUpperCase();
        try {
            EtatTransaction etat = EtatTransaction.valueOf(etatString);
            transaction.setEtat(etat);
            if (etat == EtatTransaction.FINALISEE) {
                transaction.getAnnonce().setEtat(EtatAnnonce.VENDUE);
                transaction.getAnnonce().getVoiture().setDisponible(false);
            }
            System.out.println("Etat de la transaction mis à jour : " + transaction);
        } catch (IllegalArgumentException e) {
            System.out.println("État invalide.");
        }
    }

    /**
     * Recherche un vendeur par identifiant.
     */
    private Vendeur trouverVendeur(int id) {
        return vendeurs.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    /**
     * Recherche un acheteur par identifiant.
     */
    private Acheteur trouverAcheteur(int id) {
        return acheteurs.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    /**
     * Recherche un agent par identifiant.
     */
    private Agent trouverAgent(int id) {
        return agents.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    /**
     * Lit un entier depuis la console avec validation.
     */
    private int lireEntier(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String ligne = scanner.nextLine().trim();
            try {
                return Integer.parseInt(ligne);
            } catch (NumberFormatException e) {
                System.out.println("Entier invalide, réessayez.");
            }
        }
    }

    /**
     * Lit un nombre à virgule depuis la console avec validation.
     */
    private double lireDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String ligne = scanner.nextLine().trim();
            try {
                return Double.parseDouble(ligne);
            } catch (NumberFormatException e) {
                System.out.println("Nombre invalide, réessayez.");
            }
        }
    }
}
