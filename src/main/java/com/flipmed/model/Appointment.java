package com.flipmed.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    UUID id;
    Doctor doctor;
    Patient patient;
    AppointmentStatus status;
    String prescriptionNotes;
    LocalDate date;
    Slot timeSlot;

    LocalDateTime createdAt;

    public Appointment(Doctor doctor, Patient patient, Slot timeSlot) {
        this.id = UUID.randomUUID();
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
        this.status = AppointmentStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId(){
        return this.id;
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

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void book() {
        this.status = AppointmentStatus.SCHEDULED;
    }

    public boolean has(Doctor doctor, Patient patient, Slot slot) {
        return getDoctor().equals(doctor)
            && getPatient().equals(patient)
            && getTimeSlot().equals(slot);
    }

    public boolean isOnHold(){
        return status.equals(AppointmentStatus.ON_HOLD);
    }

    public boolean isScheduled(){
        return status.equals(AppointmentStatus.SCHEDULED);
    }

    public void markScheduled() {
        status = AppointmentStatus.SCHEDULED;
    }
    public void markOnHold() {
        status = AppointmentStatus.ON_HOLD;
    }

    public void cancel() {
        status = AppointmentStatus.CANCELED;
    }
}
