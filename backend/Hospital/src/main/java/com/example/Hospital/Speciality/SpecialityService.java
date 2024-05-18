package com.example.Hospital.Speciality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public List<SpecialityEntity> getAllSpecialities() {
        return specialityRepository.findAll();
    }
}
