package com.example.Hospital;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.example.Hospital.Speciality.SpecialityEntity;
import com.example.Hospital.Speciality.SpecialityRepository;

@SpringBootTest  // Indique que c'est un test d'intégration Spring Boot
class HospitalRepositoryIntegrationTests {

    @MockBean  // Mock du service d'import CSV
    private CsvImportService csvImportService;

    @MockBean  // Mock du publisher d'événements
    private HospitalEventPublisher hospitalEventPublisher;

    @Autowired  // Injection du repository des hôpitaux
    private HospitalRepository hospitalRepository;

    @Autowired  // Injection du repository des spécialités
    private SpecialityRepository specialityRepository;

    @Autowired  // Injection du service des hôpitaux
    private HospitalService hospitalService;

    private SpecialityEntity speciality1;
    private SpecialityEntity speciality2;

    @BeforeEach  // Méthode exécutée avant chaque test
    public void setup() {
        // Créer et persister les spécialités
        speciality1 = new SpecialityEntity();
        speciality1.setName("Ophtalmologie médicale");
        specialityRepository.save(speciality1);

        speciality2 = new SpecialityEntity();
        speciality2.setName("Cardiologie");
        specialityRepository.save(speciality2);

        // Configurer les hôpitaux
        HospitalEntity hospital1 = new HospitalEntity();
        hospital1.setName("Hospital A");
        hospital1.setLatitude(51.501364f);
        hospital1.setLongitude(-0.14189f);
        hospital1.setAvailableBeds(10);
        hospital1.addSpeciality(speciality1);
        hospitalRepository.save(hospital1);

        HospitalEntity hospital2 = new HospitalEntity();
        hospital2.setName("Hospital B");
        hospital2.setLatitude(48.8566f);
        hospital2.setLongitude(2.3522f);
        hospital2.setAvailableBeds(5);
        hospital2.addSpeciality(speciality1);
        hospital2.addSpeciality(speciality2);
        hospitalRepository.save(hospital2);
    }

    @Test
    @Transactional  // Assure que le test est exécuté dans une transaction
    void testFindNearestHospitalWithSpeciality() {
        // Utiliser l'identifiant correct de la spécialité
        int specialityId = speciality1.getId().intValue();
        HospitalEntity nearestHospital = hospitalService.findNearestHospitalWithSpeciality(specialityId, 51.509865f, -0.118092f);

        // Vérifie que l'hôpital le plus proche est trouvé
        assertThat(nearestHospital).isNotNull();
        assertThat(nearestHospital.getName()).isEqualTo("Hospital A");

        // Vérifie que l'événement a été publié
        verify(hospitalEventPublisher, times(1)).publishHospitalFoundEvent(nearestHospital);

        // Vérifie que le nombre de lits disponibles a été décrémenté
        assertThat(nearestHospital.getAvailableBeds()).isEqualTo(9);
    }
}
