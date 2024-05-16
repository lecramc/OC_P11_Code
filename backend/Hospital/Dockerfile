# Étape de construction avec Maven
FROM openjdk:22-jdk-slim AS build

# Correction des permissions et nettoyage du cache APT
RUN chmod 1777 /tmp \
    && chmod -R 777 /var/lib/apt /var/cache/apt \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* 

# Mise à jour et installation de Maven avec gestion des erreurs
RUN apt-get update --ignore-missing && apt-get install -y maven

# Définition du répertoire de travail
WORKDIR /app

# Copie du fichier pom.xml et des sources
COPY pom.xml .
COPY src src

# Compilation et empaquetage du projet avec Maven
RUN mvn clean package -DskipTests

# Étape de déploiement utilisant la même image Java
FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose le port sur lequel l'application s'exécute
EXPOSE 8080

