package com.example.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

    private final CsvImportService csvImportService;

    @Autowired
    public HospitalApplication(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String csvSpecialityFilePath = "/Users/clementmarcel/Library/Mobile Documents/com~apple~CloudDocs/Studies/Archi logiciel/Projet 11/OC_P11_Code/backend/Hospital/src/main/resources/static/Speciality.csv";
        csvImportService.importSpecialitiesFromCSV(csvSpecialityFilePath);
        String csvHospitalFilePath = "/Users/clementmarcel/Library/Mobile Documents/com~apple~CloudDocs/Studies/Archi logiciel/Projet 11/OC_P11_Code/backend/Hospital/src/main/resources/static/Hospital.csv";
        csvImportService.importHospitalCsv(csvHospitalFilePath);
    }
}
