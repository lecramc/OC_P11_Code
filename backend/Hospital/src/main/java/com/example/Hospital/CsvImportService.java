package com.example.Hospital;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Hospital.Speciality.SpecialityEntity;
import com.example.Hospital.Speciality.SpecialityRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class CsvImportService {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

    @Transactional

    public void importSpecialitiesFromCSV(String specialityFilePath) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(specialityFilePath))
                .withSkipLines(1) // Skip header
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build()) // Assume ';' delimiter
                .build()) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String specialityName = line[0]; // Assuming the name is in the first column
                SpecialityEntity speciality = specialityRepository.findByName(specialityName)
                        .orElseGet(() -> {
                            SpecialityEntity newSpeciality = new SpecialityEntity();
                            newSpeciality.setName(specialityName);
                            specialityRepository.save(newSpeciality);
                            return newSpeciality;
                        });
                specialityRepository.save(speciality);
            }
        }
    }

    @Transactional
    public void importHospitalCsv(String filePath) throws IOException {
        List<SpecialityEntity> allSpecialities = specialityRepository.findAll();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withSkipLines(1).withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build()).build()) {
            String[] line;
            Random random = new Random();

            while ((line = reader.readNext()) != null) {
                HospitalEntity hospital = new HospitalEntity();
                hospital.setName(line[8]);
                hospital.setLongitude(Float.parseFloat(line[15].replace(",", ".")));
                hospital.setLatitude(Float.parseFloat(line[14].replace(",", ".")));
                hospital.setAvailableBeds(random.nextInt(100));
                int numberOfSpecialities = 1 + random.nextInt(allSpecialities.size());
                List<SpecialityEntity> assignedSpecialities = getRandomSpecialities(allSpecialities, numberOfSpecialities, random);
                assignedSpecialities.forEach(hospital::addSpeciality);

                hospitalRepository.save(hospital);
            }
        } catch (CsvValidationException e) {
            System.err.println("CSV validation error: " + e.getMessage());
            // Handle the error appropriately
        }
    }

    private List<SpecialityEntity> getRandomSpecialities(List<SpecialityEntity> specialities, int number, Random random) {
        Collections.shuffle(specialities, random);
        return specialities.subList(0, number);
    }
}
