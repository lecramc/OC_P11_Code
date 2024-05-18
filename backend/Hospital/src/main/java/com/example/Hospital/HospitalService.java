package com.example.Hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Hospital.Speciality.SpecialityEntity;
import com.example.Hospital.Speciality.SpecialityRepository;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private HospitalEventPublisher hospitalEventPublisher;

    @Transactional
    public HospitalEntity findNearestHospitalWithSpeciality(int speciality, float latitude, float longitude) {
        HospitalEntity nearestHospital = hospitalRepository.findNearestHospitalWithSpeciality(speciality, latitude, longitude);
        if (nearestHospital != null && nearestHospital.getAvailableBeds() > 0) {
            hospitalEventPublisher.publishHospitalFoundEvent(nearestHospital);
            nearestHospital.setAvailableBeds(nearestHospital.getAvailableBeds() - 1);  // Réserve un lit
            hospitalRepository.save(nearestHospital);  // Enregistre les modifications
        }
        return nearestHospital;
    }

    public List<HospitalEntity> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public void saveSpeciality(SpecialityEntity speciality) {
        specialityRepository.save(speciality);
    }

    public void saveHospital(HospitalEntity hospital) {
        hospitalRepository.save(hospital);
    }
}
