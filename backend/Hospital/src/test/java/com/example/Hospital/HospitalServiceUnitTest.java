package com.example.Hospital;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.Hospital.Speciality.SpecialityEntity;
import com.example.Hospital.Speciality.SpecialityRepository;

public class HospitalServiceUnitTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @Mock
    private SpecialityRepository specialityRepository;

    @Mock
    private HospitalEventPublisher hospitalEventPublisher;

    @InjectMocks
    private HospitalService hospitalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindNearestHospitalWithSpeciality_NoAvailableBeds() {
        // Setup mock response
        SpecialityEntity speciality = new SpecialityEntity();
        speciality.setName("Cardiologie");
        when(hospitalRepository.findNearestHospitalWithSpeciality(anyInt(), anyFloat(), anyFloat())).thenReturn(null);

        // Execute the service call
        HospitalEntity result = hospitalService.findNearestHospitalWithSpeciality(2, 51.509865f, -0.118092f);

        // Assert the response
        assertThat(result).isNull();
        verify(hospitalEventPublisher, times(0)).publishHospitalFoundEvent(any(HospitalEntity.class));
    }

    @Test
    void testFindNearestHospitalWithSpeciality_NoHospitalFound() {
        // Setup mock response
        when(hospitalRepository.findNearestHospitalWithSpeciality(anyInt(), anyFloat(), anyFloat())).thenReturn(null);

        // Execute the service call
        HospitalEntity result = hospitalService.findNearestHospitalWithSpeciality(1, 51.509865f, -0.118092f);

        // Assert the response
        assertThat(result).isNull();
        verify(hospitalEventPublisher, times(0)).publishHospitalFoundEvent(any(HospitalEntity.class));
    }

    @Test
    void testFindNearestHospitalWithSpeciality_HospitalFound() {
        // Setup mock response
        SpecialityEntity speciality = new SpecialityEntity();
        speciality.setName("Cardiologie");

        HospitalEntity hospital = new HospitalEntity();
        hospital.setName("Hospital A");
        hospital.setLatitude(51.501364f);
        hospital.setLongitude(-0.14189f);
        hospital.setAvailableBeds(10);
        hospital.addSpeciality(speciality);

        when(hospitalRepository.findNearestHospitalWithSpeciality(anyInt(), anyFloat(), anyFloat())).thenReturn(hospital);

        // Execute the service call
        HospitalEntity result = hospitalService.findNearestHospitalWithSpeciality(1, 51.509865f, -0.118092f);

        // Assert the response
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Hospital A");
        assertThat(result.getAvailableBeds()).isEqualTo(9); // Bed should be reserved

        // Verify the event was published
        verify(hospitalEventPublisher, times(1)).publishHospitalFoundEvent(hospital);
    }
}
