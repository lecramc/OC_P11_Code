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

public class SpecialityServiceUnitTest {

    @Mock  // Mock du repository des spécialités
    private SpecialityRepository specialityRepository;

    @InjectMocks  // Injection du service avec les mocks
    private SpecialityService specialityService;

    @BeforeEach  // Méthode exécutée avant chaque test
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Initialisation des mocks
    }

    @Test
    public void testGetAllSpecialities() {
        // Création de deux entités de spécialité
        SpecialityEntity speciality1 = new SpecialityEntity();
        speciality1.setName("Cardiologie");

        SpecialityEntity speciality2 = new SpecialityEntity();
        speciality2.setName("Neurologie");

        // Configuration du mock pour retourner les entités créées
        when(specialityRepository.findAll()).thenReturn(Arrays.asList(speciality1, speciality2));

        // Appel du service
        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        // Vérifications
        assertThat(specialities).isNotEmpty();  // Vérifie que la liste n'est pas vide
        assertThat(specialities.size()).isEqualTo(2);  // Vérifie que la liste contient deux éléments
        assertThat(specialities.get(0).getName()).isEqualTo("Cardiologie");  // Vérifie le nom de la première spécialité
        assertThat(specialities.get(1).getName()).isEqualTo("Neurologie");  // Vérifie le nom de la deuxième spécialité
    }

    @Test
    public void testGetAllSpecialitiesEmpty() {
        // Configuration du mock pour retourner une liste vide
        when(specialityRepository.findAll()).thenReturn(Arrays.asList());

        // Appel du service
        List<SpecialityEntity> specialities = specialityService.getAllSpecialities();

        // Vérifications
        assertThat(specialities).isEmpty();  // Vérifie que la liste est vide
    }
}
