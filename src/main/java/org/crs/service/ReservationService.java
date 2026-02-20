package org.crs.service;

import org.crs.enums.CarType;
import org.crs.interfaces.Reservable;
import org.crs.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private final CarInventory inventory;
    private final List<Reservation> reservations = new ArrayList<>();

    public ReservationService(CarInventory inventory) {
        this.inventory = inventory;
    }

    public Reservation reserveCar(CarType type,
                                  LocalDateTime startDateTime,
                                  int numberOfDays) {

        if (numberOfDays <= 0) {
            throw new IllegalArgumentException("Number of days must be greater than 0");
        }

        if (!inventory.hasAvailableCar(type)) {
            throw new RuntimeException("Out of stock for car type: " + type);
        }

        Optional<Reservable> availableCar = inventory.getAvailableCar(type);

        if (availableCar.isEmpty()) {
            throw new RuntimeException("Out of stock for car type: " + type);
        }

        Reservable car = availableCar.get();
        car.reserve();

        Reservation reservation = new Reservation((Car) car, startDateTime, numberOfDays);
        reservations.add(reservation);

        System.out.println("Reserved " + type + " | Remaining: "
                + inventory.getAvailableCount(type));

        return reservation;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }
}