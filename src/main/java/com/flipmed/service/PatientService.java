package com.flipmed.service;

import com.flipmed.model.Appointment;
import com.flipmed.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    List<Patient> allPatients;

    public PatientService() {
        this.allPatients = new ArrayList<>();
    }

    public List<Patient> getAllPatients() {
        return allPatients;
    }

    public boolean register(String name) {
        Patient patient = new Patient(name);
        return allPatients.add(patient);
    }

    public boolean bookAppointment(Patient patient, Appointment appointment){
        return patient.bookAppointment(appointment);
    }
}
