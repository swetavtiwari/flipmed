public class Appointment {

    int id;
    Doctor doctor;
    Patient patient;
    AppointmentStatus status;
    String prescriptionNotes;

    @Override
    public boolean equals(Object other){
        if( !(other instanceof Doctor)){
            return false;
        }
        return this.id == other.id;
    }
    
}
