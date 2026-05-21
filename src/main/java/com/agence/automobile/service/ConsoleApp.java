package com.agence.automobile.service;

import java.util.Scanner;

/**
 * Interface utilisateur de l'application.
 * Affiche le menu principal et redirige les choix vers le service métier.
 */
public class ConsoleApp {
    private final AgenceService agenceService;
    private final Scanner scanner;

    public ConsoleApp() {
        this.agenceService = new AgenceService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Démarre la boucle principale de l'application.
     * Le menu est réaffiché tant que l'utilisateur n'a pas choisi de quitter.
     */
    public void run() {
        boolean running = true;
        while (running) {
            showMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> agenceService.handlePersonManagement(scanner);
                case "2" -> agenceService.handleVoitureManagement(scanner);
                case "3" -> agenceService.handleAnnonceManagement(scanner);
                case "4" -> agenceService.handleDemandeManagement(scanner);
                case "5" -> agenceService.handleTransactionManagement(scanner);
                case "0" -> running = false;
                default -> System.out.println("Choix invalide, veuillez réessayer.");
            }
        }
        System.out.println("Merci d'avoir utilisé l'application d'agence de médiation automobile.");
    }

    /**
     * Affiche le menu principal de l'agence.
     */
    private void showMainMenu() {
        System.out.println("\n=== AGENCE DE MÉDIATION AUTOMOBILE ===");
        System.out.println("1. Gestion des personnes");
        System.out.println("2. Gestion des voitures");
        System.out.println("3. Gestion des annonces");
        System.out.println("4. Gestion des demandes");
        System.out.println("5. Gestion des transactions");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }
}
