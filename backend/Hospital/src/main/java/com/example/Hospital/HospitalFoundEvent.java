package com.example.Hospital;

import org.springframework.context.ApplicationEvent;

public class HospitalFoundEvent extends ApplicationEvent {

    // L'hôpital trouvé associé à cet événement
    private final HospitalEntity hospital;

    // Constructeur de l'événement
    public HospitalFoundEvent(Object source, HospitalEntity hospital) {
        super(source);  // Appelle le constructeur de la classe parente ApplicationEvent
        this.hospital = hospital;  // Initialise l'hôpital trouvé
    }

    // Getter pour accéder à l'hôpital trouvé
    public HospitalEntity getHospital() {
        return hospital;
    }
}
