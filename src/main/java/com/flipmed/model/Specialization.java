package com.flipmed.model;

public enum Specialization {
    CARDIOLOGIST("Cardiologist"),
    DERMATOLOGIST("Dermatologist"),
    ORTHOPEDIC("OrthoPedic"),
    GENERAL_PHYSICIAN("General Physician");

    final String name;

     Specialization(String name){
        this.name = name;
     }
}
