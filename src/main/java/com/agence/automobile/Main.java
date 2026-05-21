package com.agence.automobile;

import com.agence.automobile.service.ConsoleApp;

/**
 * Point d'entrée de l'application de l'agence automobile.
 * Ce programme démarre l'application console et affiche le menu principal.
 */
public class Main {
    public static void main(String[] args) {
        // Crée et lance l'interface de la console
        new ConsoleApp().run();
    }
}
