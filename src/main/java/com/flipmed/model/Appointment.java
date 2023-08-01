import java.time.LocalDate;

public class Appointment {

    int id;
    Doctor doctor;
    Patient patient;
    AppointmentStatus status;
    String prescriptionNotes;
    LocalDate date;
    Slot timeSlot;
}
