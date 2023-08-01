package com.flipmed.service;

import com.flipmed.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    List<Patient> allPatients;

    public PatientService() {
        this.allPatients = new ArrayList<>();
    }

    public boolean register(String name) {
        Patient patient = new Patient(name);
        return allPatients.add(patient);
    }
}
