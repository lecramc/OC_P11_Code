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

@SpringBootTest
class SpecialityServiceIntegrationTest {

    @MockBean
    private CsvImportService csvImportService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private SpecialityRepository specialityRepository;

    @BeforeEach
    void setup() {
        // Clean up repository before each test
        specialityRepository.deleteAll();
    }

    @Test
    @Transactional
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
        assertThat(specialities).hasSize(2);
        assertThat(specialities).extracting(SpecialityEntity::getName).containsExactlyInAnyOrder("Cardiologie", "Neurologie");
    }

    @Test
    @Transactional
    void testGetAllSpecialitiesEmpty() {
        // Act
        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        // Assert
        assertThat(specialities).isEmpty();
    }
}
