package com.example.Hospital;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component  // Indique que cette classe est un composant Spring
public class HospitalEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    // Constructeur avec injection de dépendance
    public HospitalEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // Méthode pour publier un événement HospitalFoundEvent
    public void publishHospitalFoundEvent(HospitalEntity hospital) {
        // Crée un nouvel événement HospitalFoundEvent
        HospitalFoundEvent hospitalFoundEvent = new HospitalFoundEvent(this, hospital);
        // Publie l'événement
        applicationEventPublisher.publishEvent(hospitalFoundEvent);
    }
}
