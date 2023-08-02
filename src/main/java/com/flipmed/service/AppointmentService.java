package com.flipmed.service;

import com.flipmed.model.*;

import java.util.*;
import java.util.function.Predicate;

public class AppointmentService {

    List<Appointment> appointments;
    DoctorService doctorService;

    public AppointmentService(DoctorService doctorService){
        this.appointments = new ArrayList<>();
        this.doctorService = doctorService;
    }

    public Appointment createAppointment(Doctor doctor, Patient patient, Slot slot) {
        Appointment appointment = new Appointment(doctor, patient, slot);
        appointments.add(appointment);
        return appointment;
    }

    public Appointment bookAppointment(Doctor doctor, Patient patient, Slot slot) {

        if (scheduledAppointmentExistsForPatient(patient, slot)) {
            System.out.printf("\n %s already has an appointment at %s. " +
                "You cancel it to book a new one if wish to book with any other doctor", patient.getName(), slot);
            return null;
        }
        if (!doctorHasSlotAvailable(doctor, slot) && !appointmentAlreadyScheduledForDoctor(doctor, slot)) {
            System.out.println("Doctor is not available at " + slot.getStartTime());
            return null;
        }
        Appointment appointment = createAppointment(doctor, patient, slot);
        if (appointmentAlreadyScheduledForDoctor(doctor, slot)) {
            System.out.printf("\nAdding %s to waitlist for %s  %s", patient.getName(), doctor.getName(), slot);
            appointment.markOnHold();
            return appointment;
        }
        doctorService.removeSlot(doctor, slot);
        appointment.book();
        return appointment;
    }


    public void cancelAppointment(UUID appointmentId) {
        Optional<Appointment> optionalAppointment = appointments.stream()
            .filter(appointment1 -> appointment1.getId() == appointmentId)
            .findFirst();
        optionalAppointment.ifPresentOrElse(appointment -> {
                doctorService.addSlot(appointment.getDoctor(), appointment.getTimeSlot());
                appointment.cancel();
                System.out.println("Appointment Cancelled");
                bookAppointmentFromWaitList(appointment.getDoctor(), appointment.getTimeSlot());
            }, () -> System.out.println("Appointment does not exist")
        );
    }

    private void bookAppointmentFromWaitList(Doctor doctor, Slot slot) {

        Optional<Appointment> waitlistedAppointment = appointments.stream()
            .filter(Appointment::isOnHold)
            .filter(appointment -> appointment.getDoctor().equals(doctor))
            .filter(appointment -> appointment.getTimeSlot().equals(slot))
            .min(Comparator.comparing(Appointment::getCreatedAt));

        waitlistedAppointment.ifPresent(appointment -> {
            appointment.markScheduled();
            System.out.printf("\nAppointment Confirmed for id %s", appointment.getId());
            doctorService.removeSlot(doctor, slot);
        });
    }

    public void showAllBookedAppointmentsForPatient(Patient patient) {
         appointments.stream()
            .filter(appointment -> appointment.getPatient().equals(patient))
            .filter(Appointment::isScheduled)
             .forEach(appointment -> System.out.printf("\n Appointment id: %s  %s , %s:%s",
                 appointment.getId(), appointment.getDoctor().getName(),
                 appointment.getTimeSlot().getStartTime().getHour(), appointment.getTimeSlot().getStartTime().getMinute()));
    }

    private static boolean doctorHasSlotAvailable(Doctor doctor, Slot slot) {
        return doctor.getAvailableSlots().stream().anyMatch(slot::equals);
    }

    private boolean appointmentAlreadyScheduledForDoctor(Doctor doctor, Slot slot) {
        return scheduledAppointmentExists(appointment -> appointment.getDoctor().equals(doctor), slot);
    }

    private boolean scheduledAppointmentExistsForPatient(Patient patient, Slot slot) {
        return scheduledAppointmentExists(appointment -> appointment.getPatient().equals(patient), slot);
    }

    private boolean scheduledAppointmentExists(Predicate<Appointment> filterPredicate, Slot slot){
        return appointments.stream()
            .filter(filterPredicate)
            .filter(Appointment::isScheduled)
            .map(Appointment::getTimeSlot)
            .anyMatch(slot::equals);
    }
}
