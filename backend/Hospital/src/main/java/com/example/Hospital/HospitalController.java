package com.example.Hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public List<HospitalEntity> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/search")
    public ResponseEntity<HospitalEntity> findNearestHospitalWithSpeciality(
            @RequestParam int speciality,
            @RequestParam float latitude,
            @RequestParam float longitude) {

        HospitalEntity hospital = hospitalService.findNearestHospitalWithSpeciality(speciality, latitude, longitude);
        if (hospital != null) {
            return ResponseEntity.ok(hospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
