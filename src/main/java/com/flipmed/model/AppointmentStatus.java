package com.flipmed.model;

public enum AppointmentStatus {

    CREATED("Created"),
   SCHEDULED("Scheduled"),
   IN_PROGRESS("In Progres"),
   COMPLETED("Completed"),
   CANCELED("Canceled"),
   ON_HOLD("On Hold");

    final String name;

    AppointmentStatus(String name){
        this.name = name;
     }
}
