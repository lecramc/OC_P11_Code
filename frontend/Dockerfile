# Image de base utilisant Node.js
FROM node:18-alpine

# Création et définition du répertoire de travail
WORKDIR /app

# Copie des fichiers de définition de dépendances
COPY package.json ./

# Installation des dépendances, y compris les dépendances de développement pour le build
RUN npm install

# Copie du reste du code source de l'application
COPY . .

RUN npm run test:e2e

# Compilation du projet
RUN npm run build

EXPOSE 3000

