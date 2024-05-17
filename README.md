# OC_P11_Code

## Description

Ce dépôt contient le code source du Projet 11 du cursus OpenClassrooms. Le projet inclut des composants backend et frontend et est configuré pour utiliser Docker et CircleCI pour l'intégration continue.

## Table des matières

- [Installation](#installation)
- [Utilisation](#utilisation)
- [Exécution des tests](#exécution-des-tests)
- [Intégration continue avec CircleCI](#intégration-continue-avec-circleci)
- [Contribution](#contribution)

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

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.
