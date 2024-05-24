package com.example.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    // Requête personnalisée pour trouver l'hôpital le plus proche avec la spécialité donnée
    @Query(value = "SELECT h.* FROM hospital_entity h "
            + "JOIN hospital_speciality hs ON h.id = hs.hospital_id "
            + "JOIN speciality_entity s ON s.id = hs.speciality_id "
            + "WHERE s.id = :speciality "
            + "AND h.available_beds > 0 " // Seuls les hôpitaux avec des lits disponibles
            + "ORDER BY (POWER(h.latitude - :latitude, 2) + POWER(h.longitude - :longitude, 2)) ASC "
            + "LIMIT 1", // Prend le plus proche
            nativeQuery = true)  // Indique que la requête est en SQL natif
    HospitalEntity findNearestHospitalWithSpeciality(@Param("speciality") int speciality,
            @Param("latitude") float latitude,
            @Param("longitude") float longitude);
}
