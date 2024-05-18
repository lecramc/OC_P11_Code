package com.example.Hospital.Speciality;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SpecialityServicUnitTest {

    @Mock
    private SpecialityRepository specialityRepository;

    @InjectMocks
    private SpecialityService specialityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSpecialities() {
        SpecialityEntity speciality1 = new SpecialityEntity();
        speciality1.setName("Cardiologie");

        SpecialityEntity speciality2 = new SpecialityEntity();
        speciality2.setName("Neurologie");

        when(specialityRepository.findAll()).thenReturn(Arrays.asList(speciality1, speciality2));

        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        assertThat(specialities).isNotEmpty();
        assertThat(specialities.size()).isEqualTo(2);
        assertThat(specialities.get(0).getName()).isEqualTo("Cardiologie");
        assertThat(specialities.get(1).getName()).isEqualTo("Neurologie");
    }

    @Test
    public void testGetAllSpecialitiesEmpty() {
        when(specialityRepository.findAll()).thenReturn(Arrays.asList());

        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        assertThat(specialities).isEmpty();
    }
}
