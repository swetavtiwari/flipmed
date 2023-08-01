public enum AppointmentStatus {
    
   SCHEDULED("Scheduled"),
   IN_PROGRESS("In Progres"),
   COMPLETED("Completed");

    String name;

     Specialization(String name){
        this.name = name;
     }
}
