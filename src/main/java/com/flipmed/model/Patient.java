package com.flipmed.model;

import java.util.List;
import java.util.UUID;

public class Patient {
    UUID id;
    String name;
    String phoneNumber;
    List<Appointment> appointments;
}
