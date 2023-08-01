package com.flipmed.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient {
    UUID id;
    String name;
    List<Appointment> appointments;

    public Patient(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.appointments = new ArrayList<>();
    }
}
