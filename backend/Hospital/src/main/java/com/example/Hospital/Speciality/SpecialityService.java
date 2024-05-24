package com.example.Hospital.Speciality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // Indique que cette classe est un service Spring
public class SpecialityService {

    @Autowired  // Injection de dépendance pour SpecialityRepository
    private SpecialityRepository specialityRepository;

    // Méthode pour obtenir toutes les spécialités
    public List<SpecialityEntity> getAllSpecialities() {
        return specialityRepository.findAll();  // Retourne la liste de toutes les spécialités
    }
}
