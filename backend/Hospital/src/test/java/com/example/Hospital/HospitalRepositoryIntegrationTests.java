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

@SpringBootTest
class HospitalRepositoryIntegrationTests {

    @MockBean
    private CsvImportService csvImportService;

    @MockBean
    private HospitalEventPublisher hospitalEventPublisher;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private HospitalService hospitalService;

    private SpecialityEntity speciality1;
    private SpecialityEntity speciality2;

    @BeforeEach
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
    @Transactional
    void testFindNearestHospitalWithSpeciality() {
        // Utiliser l'identifiant correct de la spécialité
        int specialityId = speciality1.getId().intValue();
        HospitalEntity nearestHospital = hospitalService.findNearestHospitalWithSpeciality(specialityId, 51.509865f, -0.118092f);

        assertThat(nearestHospital).isNotNull();
        assertThat(nearestHospital.getName()).isEqualTo("Hospital A");

        // Vérifier que l'événement a été publié
        verify(hospitalEventPublisher, times(1)).publishHospitalFoundEvent(nearestHospital);

        // Vérifier que le nombre de lits disponibles a été décrémenté
        assertThat(nearestHospital.getAvailableBeds()).isEqualTo(9);
    }
}
