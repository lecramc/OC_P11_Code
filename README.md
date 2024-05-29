# OC_P11_Code

## Description

Ce dépôt contient le code source du Projet 11 du cursus OpenClassrooms. Le projet inclut des composants backend et frontend et est configuré pour utiliser Docker et CircleCI pour l'intégration continue.

## Table des matières

- [Installation](#installation)
- [Utilisation](#utilisation)
- [Exécution des tests](#exécution-des-tests)
- [Intégration continue avec CircleCI](#intégration-continue-avec-circleci)
- [GitFlow pour le projet](#gitflow-pour-le-projet)
- [Tests de performance avec JMeter](#tests-de-performance-avec-jmeter)
- [Tests end-to-end avec Cypress](#tests-end-to-end-avec-cypress)

## Installation

Pour configurer le projet localement, suivez ces étapes :

1. Clonez le dépôt :

   ```sh
   git clone https://github.com/lecramc/OC_P11_Code.git
   cd OC_P11_Code
   ```
2. S'assurer que docker est installer sur voter machine.
3.  Créer un fichier .env.local à la racine du dossier /frontend et compléter les variables ci-dessous :

```sh
VITE_API_URL="http://localhost:8080/api/" 
VITE_MAPBOX_KEY="XXX" 
VITE_API_KEY="XXX" 
```

4. Construisez et démarrez les conteneurs avec Docker Compose :
   ```sh
   docker-compose up --build
   ```

## Utilisation

Une fois les conteneurs démarrés, vous pouvez accéder aux services frontend et backend via les URL suivantes :

- Frontend : `http://localhost:3000`
- Backend : `http://localhost:8080`

## Exécution des tests

Pour exécuter les tests du frontend et du backend, utilisez les commandes suivantes :

1. Pour le backend :

   ```
    cd backend/Hospital
    mvn test
   ```

2. Pour le frontend :
   ```
   cd frontend
   npm run test
   ```

## Intégration continue avec CircleCI

Ce projet utilise CircleCI pour l'intégration continue. Le fichier de configuration `.circleci/config.yml` inclut les tâches suivantes :

- **Orbs** : Utilisation de `circleci/docker` et `circleci/maven` pour simplifier les configurations Docker et Maven.
- **Jobs** :
  - `build_hospital_service` : Lance les tests et génère un rapport de coverage, puis "build" le service backend `Hospital`.
  - `build_frontend` : Installe les dépendances, exécute les tests unitaires, et "build" le projet frontend.
- **Workflows** : Définit l'ordre d'exécution des jobs. Le job `build_frontend` dépend de la réussite de `build_hospital_service`.

Pour plus de détails, référez-vous au fichier [config.yml](.circleci/config.yml).

## GitFlow pour le projet

Le projet utilise GitFlow comme modèle de branchement. Voici un aperçu des branches principales et des étapes pour travailler avec GitFlow

#### Branches principales

**main** : Contient le code de production.
**develop** : Contient le code pour la prochaine version et sert de branche d'intégration.

#### Branches de support

**Feature branches** : Utilisées pour développer de nouvelles fonctionnalités. Dérivées de develop et fusionnées dans develop après achèvement.

```
git checkout develop
git checkout -b feature/nom-de-la-feature
```

**Hotfix branches** : Utilisées pour corriger des bugs critiques en production. Dérivées de main et fusionnées dans main puis develop.

```
git checkout main
git checkout -b hotfix/nom-du-hotfix
```

## Tests de performance avec JMeter

Pour exécuter les tests de performance avec JMeter, utilisez le fichier `test_plan.jmx` situé dans le répertoire `backend/Hospital`. Suivez les étapes ci-dessous :

1. Assurez-vous que JMeter est installé sur votre machine. Vous pouvez le télécharger depuis [Apache JMeter](https://jmeter.apache.org/).

2. Lancez l'application java :

   ```sh
   mvn spring-boot:run
   ```

3. Lancez JMeter en mode GUI :

   ```sh
   jmeter
   ```

4. Ouvrez le fichier `test_plan.jmx` :

   ```
   File -> Open -> backend/Hospital/test_plan.jmx
   ```

5. Exécutez le test en cliquant sur le bouton **Start** (icône verte).

## Tests end-to-end avec Cypress

Pour exécuter les tests end-to-end avec Cypress, suivez les étapes ci-dessous :

1. Naviguez dans le répertoire `frontend` :

   ```sh
   cd frontend
   ```

2. Assurez-vous que les dépendances sont installées :

   ```sh
   npm install
   ```

3. Lancez Cypress en mode interactif :

   ```sh
   npx cypress open
   ```

4. Sélectionnez le test que vous souhaitez exécuter dans l'interface graphique de Cypress.

Alternativement, vous pouvez exécuter les tests en mode headless :

```sh
npx cypress run
```
