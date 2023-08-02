package com.flipmed.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientServiceTest {

    @Test
    void shouldRegisterPatient() {
        PatientService patientService = new PatientService();
        assertEquals(0, patientService.getAllPatients().size());

        boolean registered = patientService.register("Curious");

        assertTrue(registered);
        assertEquals(1, patientService.getAllPatients().size());
        assertEquals("Curious", patientService.getAllPatients().get(0).getName());
    }
}
