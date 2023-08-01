package com.flipmed.model;

import java.util.*;

public class Doctor {
    UUID id;
    String name;
    Specialization specialization;
    Set<Slot> availableSlots;
    int rating;

    public Doctor(String name, Specialization specialization) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = new HashSet<>();
    }

    public UUID getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Specialization getSpecialization(){
        return this.specialization;
    }

    public Set<Slot> getAvailableSlots(){
        return this.availableSlots;
    }

    public boolean addAvailableSlots(Slot slot) {
       return availableSlots.add(slot);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Doctor)) {
            return false;
        }
        return this.id == ((Doctor) other).getId();
    }
}
