package com.flipmed.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient {
    UUID id;
    String name;
    List<Appointment> appointments;

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean bookAppointment(Appointment appointment) {
        return this.appointments.add(appointment);
    }

    public Patient(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    @Override
    public boolean equals(Object other){
        if(! (other instanceof  Patient)){
            return false;
        }
        return id.equals(((Patient) other).getId());
    }
}
