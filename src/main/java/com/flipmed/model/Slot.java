package com.flipmed.model;

import java.time.LocalDateTime;

public class Slot {

    LocalDateTime startTime;
    LocalDateTime endTime;

    public Slot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public boolean isValid() {
        isWithinRange();
        LocalDateTime expectedEndTime = startTime.plusMinutes(30);
        return isWithinRange() && expectedEndTime.getHour() == (endTime.getHour())
            && expectedEndTime.getMinute() == endTime.getMinute();
    }

    private boolean isWithinRange() {
        return startTime.getHour() >= 9
            && endTime.getHour() <= 21;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Slot)) {
            return false;
        }
        Slot otherSlot = (Slot) other;
        return startTime.getHour() == otherSlot.getStartTime().getHour()
            && startTime.getMinute() == otherSlot.getStartTime().getMinute();
    }

    public String toString(){
        return String.format("(%s:%s  %s:%s)", startTime.getHour(), startTime.getMinute(), endTime.getHour(), endTime.getMinute());
    }
}
