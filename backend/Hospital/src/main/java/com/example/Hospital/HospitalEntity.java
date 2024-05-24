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

@Entity  // Indique que cette classe est une entité JPA
public class HospitalEntity {

    @Id  // Indique le champ identifiant de l'entité
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-génère l'ID
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // Relation Many-to-Many avec les spécialités
    @JoinTable(name = "hospital_speciality",
            joinColumns = @JoinColumn(name = "hospital_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "id"))
    @JsonManagedReference  // Pour éviter la récursion infinie lors de la sérialisation JSON
    private List<SpecialityEntity> specialities = new ArrayList<>();

    // Getter et Setter pour l'ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour le nom
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter et Setter pour l'adresse1
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String addr) {
        this.address1 = addr;
    }

    // Getter et Setter pour l'adresse2
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String addr) {
        this.address2 = addr;
    }

    // Getter et Setter pour l'adresse3
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String addr) {
        this.address3 = addr;
    }

    // Getter et Setter pour la ville
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter et Setter pour le code postal
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    // Getter et Setter pour la longitude
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    // Getter et Setter pour la latitude
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    // Getter et Setter pour les lits disponibles
    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    // Getter pour les spécialités, retourne une copie défensive
    public List<SpecialityEntity> getSpecialities() {
        return new ArrayList<>(specialities); // Defensive copy
    }

    // Setter pour les spécialités
    public void setSpecialities(List<SpecialityEntity> specialities) {
        this.specialities = specialities;
    }

    // Méthode pour ajouter une spécialité
    public void addSpeciality(SpecialityEntity speciality) {
        Objects.requireNonNull(speciality, "Speciality cannot be null");
        if (!this.specialities.contains(speciality)) {
            this.specialities.add(speciality);
        }
    }
}
