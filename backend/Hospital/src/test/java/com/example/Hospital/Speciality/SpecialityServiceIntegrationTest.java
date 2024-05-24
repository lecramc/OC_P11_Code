package com.example.Hospital.Speciality;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.example.Hospital.CsvImportService;

@SpringBootTest  // Indique que c'est un test d'intégration Spring Boot
class SpecialityServiceIntegrationTest {

    @MockBean  // Mock du service d'import CSV
    private CsvImportService csvImportService;

    @Autowired  // Injection du service des spécialités
    private SpecialityService specialityService;

    @Autowired  // Injection du repository des spécialités
    private SpecialityRepository specialityRepository;

    @BeforeEach  // Méthode exécutée avant chaque test
    void setup() {
        // Nettoyer le repository avant chaque test
        specialityRepository.deleteAll();
    }

    @Test
    @Transactional  // Assure que le test est exécuté dans une transaction
    void testGetAllSpecialities() {
        // Arrange
        SpecialityEntity speciality1 = new SpecialityEntity();
        speciality1.setName("Cardiologie");
        specialityRepository.save(speciality1);

        SpecialityEntity speciality2 = new SpecialityEntity();
        speciality2.setName("Neurologie");
        specialityRepository.save(speciality2);

        // Act
        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        // Assert
        assertThat(specialities).hasSize(2);  // Vérifie que la taille de la liste est 2
        assertThat(specialities)
                .extracting(SpecialityEntity::getName)
                .containsExactlyInAnyOrder("Cardiologie", "Neurologie");  // Vérifie les noms des spécialités
    }

    @Test
    @Transactional  // Assure que le test est exécuté dans une transaction
    void testGetAllSpecialitiesEmpty() {
        // Act
        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        // Assert
        assertThat(specialities).isEmpty();  // Vérifie que la liste est vide
    }
}
