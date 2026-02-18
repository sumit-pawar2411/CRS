package org.crs.domain;

import java.time.LocalDate;

public class Reservation {

    private final Car car;
    private final LocalDate startDateTime;
    private final int numberOfDays;

    public Reservation(Car car, LocalDate startDateTime, int numberOfDays) {
        this.car = car;
        this.startDateTime = startDateTime;
        this.numberOfDays = numberOfDays;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public LocalDate getEndDateTime() {
        return startDateTime.plusDays(numberOfDays);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "car=" + car +
                ", start=" + startDateTime +
                ", end=" + getEndDateTime() +
                '}';
    }
}