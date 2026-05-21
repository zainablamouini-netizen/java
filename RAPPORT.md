# RAPPORT DE CONCEPTION - Agence de Médiation Automobile

## 1. Cahier des Charges

### 1.1 Contexte et Objectif
L'application Java doit gérer l'activité d'une agence de médiation automobile assurant l'intermédiation entre vendeurs et acheteurs de véhicules. L'agence n'est pas propriétaire des véhicules ; son rôle consiste à :

- Enregistrer les voitures proposées par les vendeurs
- Publier et gérer les annonces
- Mettre en relation vendeurs et acheteurs
- Suivre les demandes des clients
- Gérer les opérations de médiation jusqu'à finalisation ou annulation

### 1.2 Besoins Fonctionnels Implémentés

#### 2.1 Gestion des personnes
- ✅ Ajouter, consulter, modifier vendeurs, acheteurs, agents

#### 2.2 Gestion des voitures
- ✅ Ajouter, consulter, rechercher, modifier voitures
- ✅ Supprimer voiture non disponible

#### 2.3 Gestion des annonces
- ✅ Créer annonce pour une voiture
- ✅ Afficher annonces, modifier description et état
- ✅ Désactiver annonce, gérer l'état

#### 2.4 Gestion des demandes
- ✅ Enregistrer demande d'achat
- ✅ Associer demande à des voitures
- ✅ Consulter demandes, modifier état

#### 2.5 Transactions
- ✅ Créer transaction
- ✅ Suivre l'état d'une transaction
- ✅ Finaliser ou annuler transaction

#### 2.6 Frais de médiation
- ✅ Calculer frais de médiation (5% du montant)

---

## 2. Conception OOP

### 2.1 Architecture Générale

```
com.agence.automobile/
├── Main.java                          [Point d'entrée]
├── model/
│   ├── Personne (abstraite)           [Classe mère]
│   ├── Vendeur (concrète)             [Hérite de Personne]
│   ├── Acheteur (concrète)            [Hérite de Personne]
│   ├── Agent (concrète)               [Hérite de Personne]
│   ├── Voiture
│   ├── Annonce
│   ├── Demande
│   └── Transaction
├── enums/
│   ├── EtatAnnonce                    [ACTIVE, DESACTIVE, VENDUE]
│   ├── EtatDemande                    [NOUVELLE, EN_COURS, TRAITE, ANNULEE]
│   └── EtatTransaction                [EN_COURS, FINALISEE, ANNULEE]
└── service/
    ├── ConsoleApp                     [Interface utilisateur]
    └── AgenceService                  [Logique métier]
```

### 2.2 Diagramme de Classes

```
                    Personne (abstraite)
                    /        |         \
                   /         |          \
              Vendeur    Acheteur      Agent
                   \         |           /
                    \        |          /
                         (entités)
                         
Voiture ──────── Vendeur
  |
  └─────► Annonce ──────► Transaction ◄─────── Acheteur
                              |
                             Agent
                         
Demande ◄────── Acheteur
  |
  └─────► [Liste de Voitures]
  
EtatAnnonce, EtatDemande, EtatTransaction (Enums)
```

### 2.3 Classes Clés

#### Classe Abstraite : Personne
**Fichier** : `model/Personne.java`

```java
public abstract class Personne {
    private final int id;           // Identifiant unique
    private String nom;              // Nom de la personne
    private String telephone;        // Contact

    // Constructeur protégé - oblige les sous-classes à initialiser
    protected Personne(int id, String nom, String telephone) {...}
    
    // Accesseurs et mutateurs
}
```

**Justification** :
- Classe abstraite car jamais instanciée directement
- Regroupe les attributs communs (id, nom, téléphone)
- Force les sous-classes à respecter une interface commune

#### Classes Concrètes : Vendeur, Acheteur, Agent

```java
public class Vendeur extends Personne {
    public Vendeur(int id, String nom, String telephone) {
        super(id, nom, telephone);
    }
}

public class Acheteur extends Personne {
    public Acheteur(int id, String nom, String telephone) {
        super(id, nom, telephone);
    }
}

public class Agent extends Personne {
    public Agent(int id, String nom, String telephone) {
        super(id, nom, telephone);
    }
}
```

**Justification** :
- Spécialisations concrètes de Personne
- Chacune peut ajouter des comportements spécifiques ultérieurement
- Polymorphisme : traiter vendeur/acheteur/agent de manière uniforme

#### Classe Voiture
```java
public class Voiture {
    private final int id;               // Immuable
    private String marque;              // Mutable
    private String modele;
    private int annee;
    private double prix;
    private boolean disponible;         // État du véhicule
    private final Vendeur vendeur;      // Lien de propriété
}
```

**Justification** :
- Aggregation : Voiture contient une référence à Vendeur
- Disponibilité gérée pour suivi des transactions
- Attributs id et vendeur immuables (final)

#### Classe Transaction
```java
public class Transaction {
    private final Annonce annonce;      // Lien à annonce
    private final Acheteur acheteur;    // Acheteur impliqué
    private final Agent agent;          // Agent médiateur
    private final double montant;       // Prix de vente
    private EtatTransaction etat;       // État actuel
    
    public double calculerFraisMediation() {
        return montant * 0.05;  // 5% de commission
    }
}
```

**Justification** :
- Composition : Transaction agrège Annonce, Acheteur, Agent
- État géré via enum
- Calcul des frais : logique métier intégrée

---

## 3. Synthèse : Analyse des Choix de Conception

### 3.1 L'Intérêt de l'Héritage

#### Problème sans héritage
Si on avait créé 3 classes indépendantes (Vendeur, Acheteur, Agent) sans héritage :

```java
// ❌ SANS HÉRITAGE - CODE DUPLIQUÉ
public class Vendeur {
    private int id;
    private String nom;
    private String telephone;
    // ... getters/setters répétés
}

public class Acheteur {
    private int id;
    private String nom;
    private String telephone;
    // ... mêmes getters/setters
}

public class Agent {
    private int id;
    private String nom;
    private String telephone;
    // ... encore les mêmes getters/setters
}
```

**Problèmes** :
- Code dupliqué (violation DRY)
- Modifications difficiles (si on change la structure, 3 endroits à modifier)
- Pas de polymorphisme
- Gestion hétérogène des personnes

#### Solution avec héritage
```java
// ✅ AVEC HÉRITAGE - CODE RÉUTILISÉ
public abstract class Personne {
    protected int id;
    protected String nom;
    protected String telephone;
    // Attributs et méthodes communes une seule fois
}

public class Vendeur extends Personne { }
public class Acheteur extends Personne { }
public class Agent extends Personne { }
```

**Avantages** :
- **Réutilisation** : Attributs/méthodes communes définies une fois
- **Maintenabilité** : Modification centralisée dans Personne
- **Polymorphisme** : Traitement unifié des 3 types
  ```java
  Personne p;
  if (type == "vendeur") p = new Vendeur(...);
  else if (type == "acheteur") p = new Acheteur(...);
  // Traiter p uniformément
  ```
- **Extensibilité** : Facile d'ajouter un nouveau type (Gestionnaire, etc.)

#### Bénéfice dans le code service
```java
// Dans AgenceService
private Vendeur trouverVendeur(int id) { ... }
private Acheteur trouverAcheteur(int id) { ... }
private Agent trouverAgent(int id) { ... }

// Avec héritage, on pourrait fusionner :
private Personne trouverPersonne(int id, Class<? extends Personne> type) { ... }
```

---

### 3.2 Classe Abstraite vs Classe Ordinaire vs Interface

| Aspect | Classe Abstraite | Classe Ordinaire | Interface |
|--------|------------------|------------------|-----------|
| **Instanciation** | ❌ Impossible | ✅ Oui | ❌ Impossible |
| **Constructeurs** | ✅ Oui | ✅ Oui | ❌ Non |
| **Attributs** | ✅ Oui (tout type) | ✅ Oui | ⚠️ Oui (public static final) |
| **Méthodes concrètes** | ✅ Oui | ✅ Oui | ⚠️ Oui (depuis Java 8) |
| **Méthodes abstraites** | ✅ Oui | ❌ Non | ✅ Oui |
| **Accès** | `protected`, `public` | Tous | Public uniquement |
| **Héritage multiple** | ❌ Non (une seule classe) | ❌ Non | ✅ Oui (plusieurs interfaces) |

#### Exemple : Pourquoi Personne est une classe abstraite

```java
// ✅ BON : Personne est une classe abstraite
public abstract class Personne {
    private int id;              // Attribut d'instance (impossible en interface)
    private String nom;
    private String telephone;
    
    protected Personne(int id, String nom, String telephone) { // Constructeur
        this.id = id;
    }
    
    public abstract void traiter(); // Méthode abstraite optionnelle
}
```

**Justification** :
1. **Attributs avec état** : Chaque personne a un id, nom, téléphone → besoin d'attributs d'instance
2. **Constructeur** : Initialisation requise → constructeur nécessaire
3. **Spécialisation hiérarchique** : Vendeur, Acheteur, Agent sont des "types de Personne"
4. **Pas de contractualisation multiple** : Une personne n'est qu'un type unique

#### Si c'était une interface (❌ mauvaise approche)

```java
// ❌ PAS BON : Personne comme interface
public interface Personne {
    int getId();  // Pas d'attribut d'instance
    String getNom();
}

public class Vendeur implements Personne {
    private int id;
    private String nom;
    // Redéfinition des attributs dans chaque classe → duplication !
}
```

#### Si c'était une classe ordinaire (⚠️ problématique)

```java
// ⚠️ POSSIBLE mais problématique
public class Personne {
    private int id;
    private String nom;
}

Personne p = new Personne(1, "Jean"); // ❌ Autorisé mais illogique
                                       // Une "Personne" générique seule n'a pas de sens
```

---

### 3.3 L'Intérêt des Énumérations

#### Problème sans enum

```java
// ❌ SANS ENUM - Strings ou ints libres
public class Annonce {
    private String etat;  // Quelle valeur valide ? "active", "ACTIVE", "active_" ?
    
    public void setEtat(String etat) {
        if ("ACTIVE".equals(etat) || "active".equals(etat) 
            || "Active".equals(etat)) {  // Comparaisons répétées
            this.etat = etat;
        }
    }
}

public class Transaction {
    private int etat;  // 1 = en cours, 2 = finalisée ? Pas clair !
    
    if (transaction.getEtat() == 1) { ... }  // Nombre magique
}
```

**Problèmes** :
- Valeurs invalides possibles ("ACTIF", "desactiv", typos)
- Pas de typage fort
- Comparaisons fastidieuses
- Nombres "magiques" incompréhensibles
- Autocomplétion IDE limitée

#### Solution avec enum

```java
// ✅ AVEC ENUM - Sûr et explicite
public enum EtatAnnonce {
    ACTIVE,
    DESACTIVE,
    VENDUE
}

public class Annonce {
    private EtatAnnonce etat;  // Type sûr
    
    public void setEtat(EtatAnnonce etat) {
        this.etat = etat;  // Validation automatique
    }
}

// Utilisation
Annonce annonce = new Annonce(...);
annonce.setEtat(EtatAnnonce.ACTIVE);  // ✅ Autocomplétion IDE
// annonce.setEtat("invalid");  // ❌ Erreur à la compilation !
```

**Avantages** :
- **Typage fort** : Impossible de passer une valeur invalide
- **Exhaustivité** : Switch sur enum → IDE avertit si valeur oubliée
  ```java
  switch(annonce.getEtat()) {
      case ACTIVE: ...
      case DESACTIVE: ...
      case VENDUE: ...
      // case AUTRE: ... ← IDE signale si manquant
  }
  ```
- **Performance** : Comparaison par identité (==) au lieu de .equals()
- **Sérialisation** : Énums sérializées naturellement
- **Itération** : `EtatAnnonce.values()` pour obtenir tous les états
- **Clarté métier** : Intention claire : "c'est l'un de ces états"

#### Bénéfice dans le code service

```java
// ✅ Code sûr et lisible
private void modifierEtatDemande(Scanner scanner) {
    System.out.print("Nouvel état : ");
    String etatString = scanner.nextLine().trim().toUpperCase();
    try {
        EtatDemande etat = EtatDemande.valueOf(etatString);
        demande.setEtat(etat);  // ✅ Validation automatique
    } catch (IllegalArgumentException e) {
        System.out.println("État invalide.");
    }
}
```

#### Comparaison des 3 approches

| Approche | Sécurité | Performance | Clarté | Maintenabilité |
|----------|----------|-------------|--------|----------------|
| Strings | ❌ Faible | ⚠️ Moyenne | ❌ Faible | ⚠️ Moyenne |
| Ints | ⚠️ Moyenne | ✅ Bonne | ❌ Très faible | ⚠️ Moyenne |
| **Enums** | **✅ Forte** | **✅ Bonne** | **✅ Forte** | **✅ Excellente** |

---

## 4. Principes OOP Respectés

### 4.1 Encapsulation
- Attributs **private** ou **protected**
- Accès contrôlé via getters/setters
- Exemple : `Voiture.disponible` privé, modifiable via `setDisponible()`

### 4.2 Héritage
- Hiérarchie `Personne` → `Vendeur/Acheteur/Agent`
- Réutilisation du code commun
- Spécialisation typée

### 4.3 Polymorphisme
- Traitement unifié des sous-classes
- Overriding potentiel de `toString()` dans sous-classes
- Collections hétérogènes possibles

### 4.4 Abstraction
- Classe `Personne` abstraite
- Interfaces implicites respectées
- Détails cachés, interfaces publiques claires

### 4.5 Composition/Agrégation
- Transaction **compose** Annonce, Acheteur, Agent
- Voiture **agrège** Vendeur
- Relations clairement établies

### 4.6 Responsabilité Unique
- **Personne** : données des acteurs
- **Voiture** : données du véhicule
- **Transaction** : gestion vente + calcul frais
- **AgenceService** : logique métier et orchestration

---

## 5. Modularité et Extensibilité

### 5.1 Structure modulaire
- Séparation model / enums / service
- Facile d'ajouter nouveaux modèles
- Service centralisé facilite maintenance

### 5.2 Extensibilité possible

#### Ajouter un nouveau type de personne
```java
public class Gestionnaire extends Personne {
    private double salaire;
    // Nouvelle spécialisation sans toucher au reste
}
```

#### Ajouter un nouveau type d'état
```java
public enum EtatAnnonce {
    ACTIVE, DESACTIVE, VENDUE, EN_ATTENTE  // Nouveau
}
```

#### Ajouter une interface pour contrats
```java
public interface Mediable {
    double calculerCommission();
}

public class Transaction implements Mediable {
    @Override
    public double calculerCommission() {
        return montant * 0.05;
    }
}
```

---

## 6. Conclusion

Ce projet démontre comment une bonne conception OOP facilite :
- **Réutilisation** : Héritage pour factoriser le code
- **Typage fort** : Enums pour validité garantie
- **Maintenabilité** : Séparation des préoccupations
- **Extensibilité** : Ajout facile de nouvelles entités
- **Robustesse** : Moins d'erreurs dues aux types

L'application est prête pour évolution vers une architecture 3-tiers (avec base de données, service REST, etc.).
