package com.flipmed.model;

import java.util.List;
import java.util.UUID;

public class Doctor {
    UUID id;
    String name;
    Specialization specialization;
    List<Slot> availableSlots;
    int rating;

    public UUID getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Specialization getSpecialization(){
        return this.specialization;
    }

    public List<Slot> getAvailableSlots(){
        return this.availableSlots;
    }

    public Doctor(String name, Specialization specialization) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Doctor)) {
            return false;
        }
        return this.id == ((Doctor) other).getId();
    }
}
