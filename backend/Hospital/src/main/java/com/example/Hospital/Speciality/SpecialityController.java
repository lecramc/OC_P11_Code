package com.example.Hospital.Speciality;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")  // Permet les requêtes cross-origin depuis le frontend sur localhost:3000
@RestController  // Indique que cette classe est un contrôleur REST
@RequestMapping("/api/specialities")  // Définit le chemin de base pour toutes les requêtes de ce contrôleur
public class SpecialityController {

    @Autowired  // Injection de dépendance pour SpecialityRepository
    private SpecialityRepository specialityRepository;

    @GetMapping  // Mappage pour une requête GET à l'URL "/api/specialities"
    public List<SpecialityEntity> getAllSpecialities() {
        return specialityRepository.findAll();  // Retourne la liste de toutes les spécialités
    }
}
