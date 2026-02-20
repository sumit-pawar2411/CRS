package org.crs.model;

import java.time.LocalDateTime;

public class Reservation {

    private final Car car;
    private final LocalDateTime startDateTime;
    private final int numberOfDays;

    public Reservation(Car car, LocalDateTime startDateTime, int numberOfDays) {
        this.car = car;
        this.startDateTime = startDateTime;
        this.numberOfDays = numberOfDays;
    }

    public Car getCar() {
        return car;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public LocalDateTime getEndDateTime() {
        return startDateTime.plusDays(numberOfDays);
    }
}