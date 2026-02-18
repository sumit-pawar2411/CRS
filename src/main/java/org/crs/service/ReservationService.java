package org.crs.service;

import org.crs.domain.Car;
import org.crs.domain.CarType;
import org.crs.domain.Reservation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    private final CarInventory carInventory;
    private final List<Reservation> reservations = new ArrayList<>();

    public ReservationService(CarInventory carInventory) {
        this.carInventory = carInventory;
    }

    public Reservation createReservation(CarType type, LocalDate startDate, int days) {
        Car car = carInventory.rentCar(type);
        if (car == null) {
            System.out.println("No " + type + " available for reservation.");
            return null;
        }
        Reservation reservation = new Reservation(car, startDate, days);
        reservations.add(reservation);
        System.out.println("Reservation successful: " + reservation);
        return reservation;
    }

    public boolean cancelReservation(Optional<Reservation> reservation) {
        if (reservations.remove(reservation.get())) {
            carInventory.returnCar(reservation.get().getCar());
            System.out.println("Reservation cancelled: " + reservation);
            return true;
        }
        return false;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
