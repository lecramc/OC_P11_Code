package com.example.Hospital;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class HospitalEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public HospitalEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishHospitalFoundEvent(HospitalEntity hospital) {
        HospitalFoundEvent hospitalFoundEvent = new HospitalFoundEvent(this, hospital);
        applicationEventPublisher.publishEvent(hospitalFoundEvent);
    }
}
