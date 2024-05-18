# OC_P11_Code

## Description

Ce dépôt contient le code source du Projet 11 du cursus OpenClassrooms. Le projet inclut des composants backend et frontend et est configuré pour utiliser Docker et CircleCI pour l'intégration continue.

## Table des matières

- [Installation](#installation)
- [Utilisation](#utilisation)
- [Exécution des tests](#exécution-des-tests)
- [Intégration continue avec CircleCI](#intégration-continue-avec-circleci)

## Installation

Pour configurer le projet localement, suivez ces étapes :

1. Clonez le dépôt :

   ```sh
   git clone https://github.com/lecramc/OC_P11_Code.git
   cd OC_P11_Code
   ```

2. Construisez et démarrez les conteneurs avec Docker Compose :
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
  - `build_hospital_service` : Installe Maven, puis construit et teste le service backend `Hospital`.
  - `build_frontend` : Installe les dépendances Node.js, exécute les tests unitaires, et construit le projet frontend.
- **Workflows** : Définit l'ordre d'exécution des jobs. Le job `build_frontend` dépend de la réussite de `build_hospital_service`.

Pour plus de détails, référez-vous au fichier [config.yml](.circleci/config.yml).

## Tests de performance avec JMeter

Pour exécuter les tests de performance avec JMeter, utilisez le fichier `test_plan.jmx` situé dans le répertoire `backend/Hospital`. Suivez les étapes ci-dessous :

1. Assurez-vous que JMeter est installé sur votre machine. Vous pouvez le télécharger depuis [Apache JMeter](https://jmeter.apache.org/).

2. Lancez JMeter en mode GUI :

   ```sh
   jmeter
   ```

3. Ouvrez le fichier `test_plan.jmx` :

   ```sh
   File -> Open -> backend/Hospital/test_plan.jmx
   ```

4. Exécutez le test en cliquant sur le bouton **Start** (icône verte).

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

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.
