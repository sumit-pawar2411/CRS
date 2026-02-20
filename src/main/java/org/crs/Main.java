package org.crs;

import org.crs.enums.CarType;
import org.crs.model.*;
import org.crs.service.CarInventory;
import org.crs.service.ReservationService;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        CarInventory inventory = new CarInventory();

        /*Limited inventory*/
        inventory.addCar(new Sedan());
        inventory.addCar(new Sedan());
        inventory.addCar(new Suv());
        inventory.addCar(new Van());

        ReservationService service = new ReservationService(inventory);

        /*Attempt to overbook Sedans (2 available, try 3)*/
        for (int i = 1; i <= 3; i++) {
            try {
                Reservation res = service.reserveCar(
                        CarType.SEDAN,
                        LocalDateTime.now(),
                        2
                );
                System.out.println("Reservation " + i + " successful: Car ID = " + res.getCar().getId());
            } catch (Exception e) {
                System.out.println("Reservation " + i + " failed: " + e.getMessage());
            }
        }
    }
}