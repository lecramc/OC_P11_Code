package com.example.Hospital;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Hospital.Speciality.SpecialityEntity;
import com.example.Hospital.Speciality.SpecialityRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service  // Indique que cette classe est un service Spring
public class CsvImportService {

    @Autowired  // Injection de dépendance pour HospitalRepository
    private HospitalRepository hospitalRepository;

    @Autowired  // Injection de dépendance pour SpecialityRepository
    private SpecialityRepository specialityRepository;

    @Transactional  // Assure que la méthode est exécutée dans une transaction
    public void importSpecialitiesFromCSV(String specialityFilePath) throws IOException, CsvValidationException {
        // Charge le fichier CSV à partir du chemin donné
        Resource resource = new ClassPathResource("static/" + specialityFilePath);
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(resource.getInputStream()))
                .withSkipLines(1) // Saute la ligne d'en-tête
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build()) // Utilise ';' comme délimiteur
                .build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String specialityName = line[0];  // Suppose que le nom est dans la première colonne
                SpecialityEntity speciality = specialityRepository.findByName(specialityName)
                        .orElseGet(() -> {
                            // Crée une nouvelle spécialité si elle n'existe pas
                            SpecialityEntity newSpeciality = new SpecialityEntity();
                            newSpeciality.setName(specialityName);
                            specialityRepository.save(newSpeciality);
                            return newSpeciality;
                        });
                specialityRepository.save(speciality);  // Sauvegarde la spécialité
            }
        }
    }

    @Transactional  // Assure que la méthode est exécutée dans une transaction
    public void importHospitalCsv(String specialityFilePath) throws IOException {
        List<SpecialityEntity> allSpecialities = specialityRepository.findAll();  // Récupère toutes les spécialités
        Resource resource = new ClassPathResource("static/" + specialityFilePath);  // Charge le fichier CSV à partir du chemin donné

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(resource.getInputStream()))
                .withSkipLines(1) // Saute la ligne d'en-tête
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build()) // Utilise ';' comme délimiteur
                .build()) {
            String[] line;
            Random random = new Random();  // Pour générer des valeurs aléatoires

            while ((line = reader.readNext()) != null) {
                HospitalEntity hospital = new HospitalEntity();
                hospital.setName(line[7]);
                hospital.setAddress1(line[8]);
                hospital.setAddress2(line[9]);
                hospital.setAddress3(line[10]);
                hospital.setCity(line[12]);
                hospital.setPostCode(line[13]);
                hospital.setLongitude(Float.parseFloat(line[15].replace(",", ".")));  // Convertit la longitude
                hospital.setLatitude(Float.parseFloat(line[14].replace(",", ".")));  // Convertit la latitude
                hospital.setAvailableBeds(random.nextInt(25));  // Génère un nombre aléatoire de lits disponibles
                int numberOfSpecialities = 1 + random.nextInt(allSpecialities.size());
                List<SpecialityEntity> assignedSpecialities = getRandomSpecialities(allSpecialities, numberOfSpecialities, random);
                assignedSpecialities.forEach(hospital::addSpeciality);  // Assigne des spécialités aléatoires à l'hôpital

                hospitalRepository.save(hospital);  // Sauvegarde l'hôpital
            }
        } catch (CsvValidationException e) {
            System.err.println("CSV validation error: " + e.getMessage());
        }
    }

    // Retourne une liste de spécialités aléatoires
    private List<SpecialityEntity> getRandomSpecialities(List<SpecialityEntity> specialities, int number, Random random) {
        Collections.shuffle(specialities, random);  // Mélange la liste de spécialités
        return specialities.subList(0, number);  // Retourne une sous-liste avec le nombre spécifié de spécialités
    }
}
