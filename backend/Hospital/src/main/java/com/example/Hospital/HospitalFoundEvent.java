package com.example.Hospital;

import org.springframework.context.ApplicationEvent;

public class HospitalFoundEvent extends ApplicationEvent {

    private final HospitalEntity hospital;

    public HospitalFoundEvent(Object source, HospitalEntity hospital) {
        super(source);
        this.hospital = hospital;
    }

    public HospitalEntity getHospital() {
        return hospital;
    }
}
