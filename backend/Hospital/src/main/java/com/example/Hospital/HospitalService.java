package com.example.Hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  // Indique que cette classe est un service Spring
public class HospitalService {

    @Autowired  // Injection de dépendance de Spring pour HospitalRepository
    private HospitalRepository hospitalRepository;

    @Autowired  // Injection de dépendance de Spring pour HospitalEventPublisher
    private HospitalEventPublisher hospitalEventPublisher;

    @Transactional  // Assure que la méthode est exécutée dans une transaction
    public HospitalEntity findNearestHospitalWithSpeciality(int speciality, float latitude, float longitude) {
        // Trouve l'hôpital le plus proche ayant la spécialité et les coordonnées données
        HospitalEntity nearestHospital = hospitalRepository.findNearestHospitalWithSpeciality(speciality, latitude, longitude);

        // Si un hôpital est trouvé et qu'il a des lits disponibles
        if (nearestHospital != null && nearestHospital.getAvailableBeds() > 0) {
            // Publie un événement indiquant qu'un hôpital a été trouvé
            hospitalEventPublisher.publishHospitalFoundEvent(nearestHospital);
            // Réserve un lit en diminuant le nombre de lits disponibles
            nearestHospital.setAvailableBeds(nearestHospital.getAvailableBeds() - 1);
            // Sauvegarde les modifications dans le dépôt
            hospitalRepository.save(nearestHospital);
        }

        // Retourne l'hôpital trouvé
        return nearestHospital;
    }

    // Retourne la liste de tous les hôpitaux
    public List<HospitalEntity> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}
