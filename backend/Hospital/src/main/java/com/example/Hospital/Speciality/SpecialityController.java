package com.example.Hospital.Speciality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/specialities")
public class SpecialityController {

    @Autowired
    private SpecialityRepository specialityRepository;

    @GetMapping
    public List<SpecialityEntity> getAllSpecialities() {
        return specialityRepository.findAll();
    }
}
