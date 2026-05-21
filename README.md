# Mini Projet Java - Agence de Médiation Automobile

**Filière** : 1ère année cycle d'ingénieur EMSI-Casablanca  
**Professeur** : Ahmed RABHI  
**Date limite** : 17 Mai 2026

---

## 📋 Description

Application console Java pour gérer les activités d'une agence de médiation automobile assurant l'intermédiation entre vendeurs et acheteurs de véhicules.

### Fonctionnalités Implémentées ✅

- **Gestion des personnes** : Ajouter/consulter/modifier vendeurs, acheteurs, agents
- **Gestion des voitures** : Ajouter, rechercher, modifier, supprimer voitures
- **Gestion des annonces** : Créer, afficher, modifier, désactiver annonces
- **Gestion des demandes** : Enregistrer, associer à des voitures, suivre l'état
- **Transactions** : Créer, suivre, finaliser ou annuler transactions
- **Frais de médiation** : Calcul automatique (5% du montant de vente)

---

## 📁 Structure du Projet

```
MINI PROJET-JAVA/
├── src/main/java/com/agence/automobile/
│   ├── Main.java                      # Point d'entrée
│   ├── model/                         # Entités métier
│   │   ├── Personne.java              # Classe abstraite
│   │   ├── Vendeur.java               # Hérite de Personne
│   │   ├── Acheteur.java              # Hérite de Personne
│   │   ├── Agent.java                 # Hérite de Personne
│   │   ├── Voiture.java
│   │   ├── Annonce.java
│   │   ├── Demande.java
│   │   └── Transaction.java
│   ├── enums/                         # Énumérations
│   │   ├── EtatAnnonce.java
│   │   ├── EtatDemande.java
│   │   └── EtatTransaction.java
│   └── service/                       # Services métier
│       ├── ConsoleApp.java            # Interface utilisateur
│       └── AgenceService.java         # Logique métier
├── out/                               # Fichiers compilés (généré)
├── README.md                          # Ce fichier
├── RAPPORT.md                         # **Rapport de conception détaillé**
├── build.bat                          # Script de compilation (Windows)
└── build.sh                           # Script de compilation (Linux/Mac)
```

---

## 🚀 Compilation et Exécution

### Option 1 : Script automatisé (Recommandé)

#### Sous Windows :
```bash
build.bat
```

#### Sous Linux/Mac :
```bash
chmod +x build.sh
./build.sh
```

### Option 2 : Compilation manuelle

```bash
# Créer le répertoire de sortie
mkdir out

# Compiler tous les fichiers Java
javac -d out ^
    src\main\java\com\agence\automobile\Main.java ^
    src\main\java\com\agence\automobile\service\*.java ^
    src\main\java\com\agence\automobile\model\*.java ^
    src\main\java\com\agence\automobile\enums\*.java

# Exécuter l'application
java -cp out com.agence.automobile.Main
```

**Prérequis** : Java 17 ou supérieur installé et accessible dans le PATH

---

## 📚 Documentation

Consultez **`RAPPORT.md`** pour :
- ✅ Cahier des charges détaillé
- 📊 Diagramme de classes et architecture
- 🎯 Analyse des choix de conception
- 🔄 Justification de l'héritage
- 📋 Classe abstraite vs classe ordinaire vs interface
- 📌 Intérêt des énumérations
- 🏗️ Principes OOP respectés
- 🔧 Modularité et extensibilité

---

## 🎮 Utilisation

Lancez l'application et naviguez via le menu principal :

```
=== AGENCE DE MÉDIATION AUTOMOBILE ===
1. Gestion des personnes
2. Gestion des voitures
3. Gestion des annonces
4. Gestion des demandes
5. Gestion des transactions
0. Quitter
```

Chaque menu offre des sous-options intuitives pour gérer les entités.

---

## 🏛️ Principes OOP Appliqués

✅ **Encapsulation** : Attributs private/protected avec accesseurs publics  
✅ **Héritage** : Hiérarchie Personne → Vendeur/Acheteur/Agent  
✅ **Polymorphisme** : Traitement unifié des sous-types  
✅ **Abstraction** : Classe Personne abstraite  
✅ **Composition** : Transaction compose ses dépendances  
✅ **Responsabilité unique** : Chaque classe a un rôle clair  
✅ **Énumérations** : États fortement typés (ACTIVE, VENDUE, etc.)

---

## 📝 Auteur

Projet réalisé selon les spécifications EMSI pour la gestion d'une agence de médiation automobile.
