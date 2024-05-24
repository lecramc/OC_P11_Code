package com.example.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})  // Exclut la configuration de sécurité par défaut
public class HospitalApplication implements CommandLineRunner {

    private final CsvImportService csvImportService;

    @Autowired  // Injection de dépendance pour CsvImportService
    public HospitalApplication(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);  // Démarre l'application Spring Boot
    }

    @Override
    public void run(String... args) throws Exception {
        // Chemin du fichier CSV pour les spécialités
        String csvSpecialityFilePath = "Speciality.csv";
        // Importe les spécialités à partir du fichier CSV
        csvImportService.importSpecialitiesFromCSV(csvSpecialityFilePath);

        // Chemin du fichier CSV pour les hôpitaux
        String csvHospitalFilePath = "Hospital.csv";
        // Importe les hôpitaux à partir du fichier CSV
        csvImportService.importHospitalCsv(csvHospitalFilePath);
    }
}
