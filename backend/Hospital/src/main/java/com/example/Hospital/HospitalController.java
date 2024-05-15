package com.example.Hospital;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private static final Logger log = LoggerFactory.getLogger(HospitalController.class);

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping
    public List<HospitalEntity> getAllHospitals() {
        List<HospitalEntity> hospitals = hospitalRepository.findAll();
        log.info("Retrieving hospitals: {}", hospitals);
        return hospitals;
    }

    @GetMapping("/search")
    public ResponseEntity<HospitalEntity> findNearestHospitalWithSpeciality(
            @RequestParam String speciality,
            @RequestParam float latitude,
            @RequestParam float longitude) {

        HospitalEntity hospital = hospitalRepository.findNearestHospitalWithSpeciality(speciality, latitude, longitude);
        if (hospital != null) {
            return ResponseEntity.ok(hospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
