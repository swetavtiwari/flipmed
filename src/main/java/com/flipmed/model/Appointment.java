package com.flipmed.model;

import java.time.LocalDate;
import java.util.UUID;

public class Appointment {

    UUID id;
    Doctor doctor;
    Patient patient;
    AppointmentStatus status;
    String prescriptionNotes;
    LocalDate date;
    Slot timeSlot;

    public Appointment(Doctor doctor, Patient patient, Slot timeSlot) {
        this.id = UUID.randomUUID();
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.status = AppointmentStatus.CREATED;
    }

    public Doctor getDoctor(){
        return this.doctor;
    }
    public Patient getPatient(){
        return this.patient;
    }

    public Slot getTimeSlot(){
        return this.timeSlot;
    }

    public void book() {
        this.status = AppointmentStatus.SCHEDULED;
    }

    public boolean has(Doctor doctor, Patient patient, Slot slot) {
        return getDoctor().equals(doctor)
            && getPatient().equals(patient)
            && getTimeSlot().equals(slot);
    }
}
