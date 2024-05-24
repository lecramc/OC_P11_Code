package com.example.Hospital.Speciality;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {

    // Méthode pour trouver une spécialité par son nom
    Optional<SpecialityEntity> findByName(String name);

}
