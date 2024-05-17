package com.example.Hospital;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Hospital.Speciality.SpecialityEntity;

@DataJpaTest
public class HospitalRepositoryIntegrationTests {

    @MockBean
    private CsvImportService csvImportService;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HospitalRepository hospitalRepository;

    @BeforeEach
    public void setup() {
        // Create and persist specialties
        SpecialityEntity speciality1 = new SpecialityEntity();
        speciality1.setName("Ophtalmologie médicale");
        entityManager.persist(speciality1);
        SpecialityEntity speciality2 = new SpecialityEntity();
        speciality2.setName("Cardiologie");
        entityManager.persist(speciality2);
        entityManager.flush();

        // Setup data here
        HospitalEntity hospital1 = new HospitalEntity();
        hospital1.setName("Hospital A");
        hospital1.setLatitude((float) 51.501364);
        hospital1.setLongitude((float) -0.14189);
        hospital1.setAvailableBeds(10);
        hospital1.addSpeciality(speciality1);
        entityManager.persist(hospital1);

        HospitalEntity hospital2 = new HospitalEntity();
        hospital2.setName("Hospital B");
        hospital2.setLatitude((float) 48.8566);
        hospital2.setLongitude((float) 2.3522);
        hospital2.setAvailableBeds(5);
        hospital2.addSpeciality(speciality1);
        hospital2.addSpeciality(speciality2);
        entityManager.persist(hospital2);

        entityManager.flush();
    }

    @Test
    void testFindNearestHospitalWithSpeciality() {
        // Testing the query method
        HospitalEntity nearestHospital = hospitalRepository.findNearestHospitalWithSpeciality(9, 51.509865f, -0.118092f);
        assertThat(nearestHospital).isNotNull();
        assertThat(nearestHospital.getName()).isEqualTo("Hospital A");
    }
}
