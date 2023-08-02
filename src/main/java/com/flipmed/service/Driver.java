package com.flipmed.service;

import com.flipmed.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class Driver {

    private DoctorService doctorService;
    private PatientService patientService;
    private AppointmentService appointmentService;

    public Driver() {
        doctorService = new DoctorService();
        patientService = new PatientService();
        appointmentService = new AppointmentService(doctorService);
    }

    public void start() {
        Doctor curious = doctorService.register("Curious", Specialization.CARDIOLOGIST);
        doctorService.addSlot(curious, new Slot(getTime(9, 30), getTime(10, 30)));
        List<Slot> slots = List.of(new Slot(getTime(9, 30), getTime(10, 0)),
            new Slot(getTime(12, 30), getTime(13, 0)),
            new Slot(getTime(16, 0), getTime(16, 30)));
        doctorService.addSlots(curious, slots);

        Doctor dreadful = doctorService.register("Dreadful", Specialization.DERMATOLOGIST);
        doctorService.addSlots(dreadful, slots);
        doctorService.getAllSlotsBySpeciality(Specialization.CARDIOLOGIST);

        Patient patientA = patientService.register("PatientA");
        Appointment appointmentA = appointmentService.bookAppointment(curious, patientA, new Slot(getTime(12, 30), getTime(13, 0)));
        doctorService.getAllSlotsBySpeciality(Specialization.CARDIOLOGIST);
        appointmentService.cancelAppointment(appointmentA.getId());
        doctorService.getAllSlotsBySpeciality(Specialization.CARDIOLOGIST);

        Patient patientB = patientService.register("PatientB");
        appointmentService.bookAppointment(curious, patientB, new Slot(getTime(12, 30), getTime(13, 0)));

        Patient patientC = patientService.register("PatientC");
        Patient patientD = patientService.register("PatientD");
        Patient patientF = patientService.register("PatientF");

        Doctor daring = doctorService.register("Daring", Specialization.DERMATOLOGIST);
        List<Slot> slots2 = List.of(new Slot(getTime(11, 30), getTime(12, 0)),
            new Slot(getTime(14, 0), getTime(14, 30)));
        doctorService.addSlots(daring, slots2);
        doctorService.getAllSlotsBySpeciality(Specialization.DERMATOLOGIST);

        appointmentService.bookAppointment(daring, patientF, new Slot(getTime(11, 30), getTime(12, 0)));
        appointmentService.bookAppointment(curious, patientA, new Slot(getTime(12, 30), getTime(13, 0)));
        appointmentService.bookAppointment(curious, patientF, new Slot(getTime(9, 30), getTime(10, 0)));
        Appointment appointmentC = appointmentService.bookAppointment(curious, patientC, new Slot(getTime(16, 0), getTime(16, 30)));

        doctorService.getAllSlotsBySpeciality(Specialization.CARDIOLOGIST);
        appointmentService.bookAppointment(curious, patientD, new Slot(getTime(16, 0), getTime(16, 30)));

        appointmentService.cancelAppointment(appointmentC.getId());

        appointmentService.showAllBookedAppointmentsForPatient(patientF);


    }

    private LocalDateTime getTime(int hour, int min) {
        return LocalDateTime.now().withHour(hour).withMinute(min);
    }
}
