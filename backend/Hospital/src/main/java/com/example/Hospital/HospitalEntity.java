package com.example.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.Hospital.Speciality.SpecialityEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class HospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String postCode;

    private float longitude;
    private float latitude;
    private int availableBeds;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "hospital_speciality",
            joinColumns = @JoinColumn(name = "hospital_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<SpecialityEntity> specialities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String addr) {
        this.address1 = addr;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String addr) {
        this.address2 = addr;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String addr) {
        this.address3 = addr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public List<SpecialityEntity> getSpecialities() {
        return new ArrayList<>(specialities); // Defensive copy
    }

    public void setSpecialities(List<SpecialityEntity> specialities) {
        this.specialities = specialities;
    }

    public void addSpeciality(SpecialityEntity speciality) {
        Objects.requireNonNull(speciality, "Speciality cannot be null");
        if (!this.specialities.contains(speciality)) {
            this.specialities.add(speciality);
        }
    }

}
