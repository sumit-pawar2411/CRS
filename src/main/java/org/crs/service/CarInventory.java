package org.crs.service;

import org.crs.enums.CarType;
import org.crs.interfaces.Reservable;
import org.crs.model.Car;

import java.util.*;

public class CarInventory {

    private final Map<CarType, List<Reservable>> carsByType = new HashMap<>();

    public CarInventory() {
        for (CarType type : CarType.values()) {
            carsByType.put(type, new ArrayList<>());
        }
    }

    public void addCar(Reservable car) {
        carsByType.get(((Car) car).getType()).add(car);
    }

    public Optional<Reservable> getAvailableCar(CarType type) {
        return carsByType.get(type)
                .stream()
                .filter(Reservable::isAvailable)
                .findFirst();
    }

    public boolean hasAvailableCar(CarType type) {
        return getAvailableCount(type) > 0;
    }

    public int getAvailableCount(CarType type) {
        return (int) carsByType.get(type)
                .stream()
                .filter(Reservable::isAvailable)
                .count();
    }

}