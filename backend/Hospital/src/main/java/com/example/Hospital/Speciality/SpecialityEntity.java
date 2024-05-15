package com.example.Hospital.Speciality;

import java.util.ArrayList;
import java.util.List;

import com.example.Hospital.HospitalEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class SpecialityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "specialities", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<HospitalEntity> hospitals;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HospitalEntity> getHospitals() {
        return new ArrayList<>(hospitals);
    }

    public void setHospitals(List<HospitalEntity> hospitals) {
        this.hospitals = hospitals;
    }

}
