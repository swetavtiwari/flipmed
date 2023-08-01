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
}
