package com.flipmed.service;

import com.flipmed.model.Doctor;
import com.flipmed.model.Slot;
import com.flipmed.model.Specialization;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    List<Doctor> allDoctors;

    public DoctorService() {
        allDoctors = new ArrayList<>();
    }


    public boolean register(String name, Specialization specialization) {
        Doctor doctor = new Doctor(name, specialization);
        return allDoctors.add(doctor);
    }

    public boolean addSlots(Doctor doctor, List<Slot> slots) {
        return slots.stream().allMatch(slot -> addSlot(doctor, slot));
    }

    public boolean addSlot(Doctor doctor, Slot slot) {
        if (!slot.isValid()) {
            return false;
        }
        return doctor.addAvailableSlots(slot);
    }
}
