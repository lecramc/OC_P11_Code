# Utilisez une image OpenJDK officielle
FROM openjdk:22-jdk-slim as build

# Installer les dépendances nécessaires pour Maven
RUN apt-get update && apt-get install -y maven

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

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
