package com.flipmed.service;

import com.flipmed.model.Doctor;
import com.flipmed.model.Slot;
import com.flipmed.model.Specialization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DoctorService {

    List<Doctor> allDoctors;

    public DoctorService() {
        allDoctors = new ArrayList<>();
    }


    public Doctor register(String name, Specialization specialization) {
        Doctor doctor = new Doctor(name, specialization);
        System.out.printf("Welcome %s . Your id is %s \n", doctor.getName(), doctor.getId());
        allDoctors.add(doctor);
        return doctor;
    }

    public boolean addSlots(Doctor doctor, List<Slot> slots) {
        return slots.stream().allMatch(slot -> addSlot(doctor, slot));
    }

    public boolean addSlot(Doctor doctor, Slot slot) {
        if (!slot.isValid()) {
            System.out.printf("Slot not valid %s %s \n", slot.getStartTime(), slot.getEndTime());
            return false;
        }
        return doctor.addAvailableSlots(slot);
    }

    public void removeSlot(Doctor doctor, Slot slot) {
        Set<Slot> slots = doctor.getAvailableSlots().stream().filter(s -> !s.equals(slot)).collect(Collectors.toSet());
        doctor.setAvailableSlots(slots);
    }

    public Set<Slot> getAllSlotsForDoctor(Doctor doctor) {
        return doctor.getAvailableSlots();
    }

    public void getAllSlotsBySpeciality(Specialization specialization) {
        allDoctors.stream().filter(doctor -> doctor.getSpecialization().equals(specialization))
            .forEach(this::printAvailableSlots);
    }

    private void printAvailableSlots(Doctor doctor) {
        doctor.getAvailableSlots().stream()
            .sorted(Comparator.comparing(Slot::getStartTime))
            .forEach(slot ->
                System.out.printf("%s  %s \n", doctor.getName(), slot));


    }
}
