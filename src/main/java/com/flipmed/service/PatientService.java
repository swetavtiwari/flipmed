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

    public Patient register(String name) {
        Patient patient = new Patient(name);
        System.out.printf("Welcome %s . Your id is %s \n", patient.getName(), patient.getId());
        allPatients.add(patient);
        return patient;
    }

    public boolean bookAppointment(Patient patient, Appointment appointment){
        return patient.bookAppointment(appointment);
    }
}
