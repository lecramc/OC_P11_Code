package com.example.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
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

        String csvSpecialityFilePath = "Speciality.csv";
        csvImportService.importSpecialitiesFromCSV(csvSpecialityFilePath);
        String csvHospitalFilePath = "Hospital.csv";
        csvImportService.importHospitalCsv(csvHospitalFilePath);
    }
}
