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

    @Mock  // Mock des dépendances
    private HospitalRepository hospitalRepository;

    @Mock
    private SpecialityRepository specialityRepository;

    @Mock
    private HospitalEventPublisher hospitalEventPublisher;

    @InjectMocks  // Injection du service avec les mocks
    private HospitalService hospitalService;

    @BeforeEach  // Configuration avant chaque test
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindNearestHospitalWithSpeciality_NoAvailableBeds() {
        // Prépare la réponse du mock pour un hôpital sans lits disponibles
        when(hospitalRepository.findNearestHospitalWithSpeciality(anyInt(), anyFloat(), anyFloat())).thenReturn(null);

        // Exécute l'appel du service
        HospitalEntity result = hospitalService.findNearestHospitalWithSpeciality(2, 51.509865f, -0.118092f);

        // Vérifie que le résultat est nul (aucun hôpital trouvé)
        assertThat(result).isNull();
        // Vérifie que l'événement n'a pas été publié
        verify(hospitalEventPublisher, times(0)).publishHospitalFoundEvent(any(HospitalEntity.class));
    }

    @Test
    void testFindNearestHospitalWithSpeciality_NoHospitalFound() {
        // Prépare la réponse du mock pour aucun hôpital trouvé
        when(hospitalRepository.findNearestHospitalWithSpeciality(anyInt(), anyFloat(), anyFloat())).thenReturn(null);

        // Exécute l'appel du service
        HospitalEntity result = hospitalService.findNearestHospitalWithSpeciality(1, 51.509865f, -0.118092f);

        // Vérifie que le résultat est nul (aucun hôpital trouvé)
        assertThat(result).isNull();
        // Vérifie que l'événement n'a pas été publié
        verify(hospitalEventPublisher, times(0)).publishHospitalFoundEvent(any(HospitalEntity.class));
    }

    @Test
    void testFindNearestHospitalWithSpeciality_HospitalFound() {
        // Prépare la réponse du mock pour un hôpital trouvé
        SpecialityEntity speciality = new SpecialityEntity();
        speciality.setName("Cardiologie");

        HospitalEntity hospital = new HospitalEntity();
        hospital.setName("Hospital A");
        hospital.setLatitude(51.501364f);
        hospital.setLongitude(-0.14189f);
        hospital.setAvailableBeds(10);
        hospital.addSpeciality(speciality);

        when(hospitalRepository.findNearestHospitalWithSpeciality(anyInt(), anyFloat(), anyFloat())).thenReturn(hospital);

        // Exécute l'appel du service
        HospitalEntity result = hospitalService.findNearestHospitalWithSpeciality(1, 51.509865f, -0.118092f);

        // Vérifie que le résultat n'est pas nul
        assertThat(result).isNotNull();
        // Vérifie les propriétés de l'hôpital trouvé
        assertThat(result.getName()).isEqualTo("Hospital A");
        assertThat(result.getAvailableBeds()).isEqualTo(9); // Un lit doit être réservé

        // Vérifie que l'événement a été publié
        verify(hospitalEventPublisher, times(1)).publishHospitalFoundEvent(hospital);
    }
}
