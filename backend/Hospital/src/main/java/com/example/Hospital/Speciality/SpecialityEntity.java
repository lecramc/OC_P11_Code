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

@Entity  // Indique que cette classe est une entité JPA
public class SpecialityEntity {

    @Id  // Indique le champ identifiant de l'entité
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-génère l'ID avec la stratégie d'identité
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "specialities", fetch = FetchType.LAZY)  // Relation Many-to-Many avec les hôpitaux, côté inverse
    @JsonBackReference  // Empêche la sérialisation récursive lors de la conversion en JSON
    private List<HospitalEntity> hospitals = new ArrayList<>();

    // Getter pour l'ID
    public Long getId() {
        return id;
    }

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // Setter pour le nom
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour la liste des hôpitaux, retourne une copie défensive
    public List<HospitalEntity> getHospitals() {
        return new ArrayList<>(hospitals);  // Copie défensive pour éviter les modifications externes
    }

    // Setter pour la liste des hôpitaux
    public void setHospitals(List<HospitalEntity> hospitals) {
        this.hospitals = hospitals;
    }

}
