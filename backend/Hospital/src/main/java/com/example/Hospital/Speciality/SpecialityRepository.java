package com.example.Hospital.Speciality;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {

    Optional<SpecialityEntity> findByName(String name);

}
