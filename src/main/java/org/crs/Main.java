package org.crs;

import org.crs.domain.CarType;
import org.crs.domain.Reservation;
import org.crs.service.CarInventory;
import org.crs.service.ReservationService;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Map<CarType, Integer> initialStock = new EnumMap<>(CarType.class);
        initialStock.put(CarType.SEDAN, 5);
        initialStock.put(CarType.SUV, 3);
        initialStock.put(CarType.VAN, 1);

        CarInventory inventory = new CarInventory(initialStock);
        ReservationService service = new ReservationService(inventory);

        Optional<Reservation> reservation1 = Optional.ofNullable(service.createReservation(CarType.SEDAN, LocalDate.of(2026, 2, 18), 3));
        service.createReservation(CarType.VAN, LocalDate.of(2026, 2, 18), 2);
        /*service.createReservation(CarType.VAN, LocalDate.of(2026, 2, 19), 1);*/

        System.out.println("\nAvailable cars after reservations:");
        System.out.println("Sedans: " + inventory.availableCount(CarType.SEDAN));
        System.out.println("SUVs: " + inventory.availableCount(CarType.SUV));
        System.out.println("Vans: " + inventory.availableCount(CarType.VAN));

        System.out.println("\nActive reservations are :" + service.getReservations());

        if (reservation1.isPresent()) {
            boolean cancelled = service.cancelReservation(reservation1);
            if (cancelled) {
                System.out.println("Reservation Cancelled Successfully");
            } else {
                System.out.println("Cancellation Failed");
            }
        } else {
            System.out.println("No car available");
        }
        System.out.println("\nActive reservations are :" + service.getReservations());
    }
}