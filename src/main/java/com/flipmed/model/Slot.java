package com.flipmed.model;

import java.time.LocalDateTime;

public class Slot {

    LocalDateTime startTime;
    LocalDateTime endTime;

    public Slot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public boolean isValid() {
        return startTime.getHour() >= 9
            && endTime.getHour() <= 21
            && startTime.plusMinutes(30).equals(endTime);
    }
}
