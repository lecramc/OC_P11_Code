package com.example.Hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")  // Permet les requêtes cross-origin depuis le frontend sur localhost:3000
@RestController  // Indique que cette classe est un contrôleur REST
@RequestMapping("/api/hospitals")  // Mappage de l'URL de base pour tous les endpoints de ce contrôleur
public class HospitalController {

    @Autowired  // Injection de dépendance pour HospitalService
    private HospitalService hospitalService;

    @GetMapping  // Mappage pour une requête GET à l'URL "/api/hospitals"
    public List<HospitalEntity> getAllHospitals() {
        return hospitalService.getAllHospitals();  // Retourne la liste de tous les hôpitaux
    }

    @GetMapping("/search")  // Mappage pour une requête GET à l'URL "/api/hospitals/search"
    public ResponseEntity<HospitalEntity> findNearestHospitalWithSpeciality(
            @RequestParam int speciality, // Paramètre de requête pour la spécialité
            @RequestParam float latitude, // Paramètre de requête pour la latitude
            @RequestParam float longitude) {  // Paramètre de requête pour la longitude

        // Cherche l'hôpital le plus proche avec la spécialité et les coordonnées données
        HospitalEntity hospital = hospitalService.findNearestHospitalWithSpeciality(speciality, latitude, longitude);
        if (hospital != null) {
            return ResponseEntity.ok(hospital);  // Retourne 200 OK avec l'hôpital trouvé
        } else {
            return ResponseEntity.notFound().build();  // Retourne 404 Not Found si aucun hôpital n'est trouvé
        }
    }
}
