package com.example.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    @Query(value = "SELECT h.* FROM hospital_entity h JOIN hospital_speciality hs ON h.id = hs.hospital_id "
            + "JOIN speciality_entity s ON s.id = hs.speciality_id WHERE s.name = :speciality "
            + "AND h.available_beds > 0 "
            + "ORDER BY (POWER(h.latitude - :latitude, 2) + POWER(h.longitude - :longitude, 2)) ASC LIMIT 1",
            nativeQuery = true)
    HospitalEntity findNearestHospitalWithSpeciality(@Param("speciality") String speciality,
            @Param("latitude") float latitude,
            @Param("longitude") float longitude);
}
