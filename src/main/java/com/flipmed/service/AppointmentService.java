package com.flipmed.service;

import com.flipmed.model.*;

import java.util.List;
import java.util.Optional;

public class AppointmentService {

    List<Appointment> appointments;
    DoctorService doctorService;

    public Appointment createAppointment(Doctor doctor, Patient patient, Slot slot) {
        Appointment appointment = new Appointment(doctor, patient, slot);
        appointments.add(appointment);
        return appointment;
    }

    public boolean bookAppointment(Doctor doctor, Patient patient, Slot slot) {

        if (appointmentExistsForPatient(patient, slot)) {
            System.out.println(" already has an appointment. You cancel it to book a new one if wish to book with any other doctor");
        }
        if (!doctorHasSlotAvailable(doctor, slot) && !appointmentExistsForDoctor(doctor, slot)) {
            System.out.println("Doctor is not available at " + slot.getStartTime());
            return false;
        }
        if (appointmentExistsForDoctor(doctor, slot)) {
            System.out.println("Adding Patient to waitlist");
        }

        Appointment appointment = createAppointment(doctor, patient, slot);
        doctorService.removeSlot(doctor, slot);
        appointment.book();
        return true;
    }

    public boolean cancelAppointment(Doctor doctor, Patient patient, Slot slot) {
        Optional<Appointment> appointment = appointments.stream()
            .filter(appointment1 -> appointment1.has(doctor, patient, slot))
            .findFirst();
        if (!appointment.isPresent()) {
            System.out.println("Appointment does not exist");
            return false;
        }
        doctorService.addSlot(doctor, slot);
        return appointments.remove(appointment.get());
    }

    private static boolean doctorHasSlotAvailable(Doctor doctor, Slot slot) {
        return doctor.getAvailableSlots().stream().noneMatch(slot::equals);
    }

    private boolean appointmentExistsForDoctor(Doctor doctor, Slot slot) {
        return appointments.stream()
            .filter(appointment -> appointment.getDoctor().equals(doctor))
            .map(Appointment::getTimeSlot).anyMatch(slot::equals);
    }

    private boolean appointmentExistsForPatient(Patient patient, Slot slot) {
        return appointments.stream()
            .filter(appointment -> appointment.getPatient().equals(patient))
            .map(Appointment::getTimeSlot).anyMatch(slot::equals);
    }

}
