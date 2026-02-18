package org.crs.service;

import org.crs.domain.Car;
import org.crs.domain.CarType;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CarInventory {

    private final Map<CarType, Queue<Car>> availableCars = new EnumMap<>(CarType.class);
    private final AtomicInteger carIdGenerator = new AtomicInteger(1);

    public CarInventory(Map<CarType, Integer> initialStock) {
        for (CarType type : initialStock.keySet()) {
            Queue<Car> queue = new LinkedList<>();
            int count = initialStock.get(type);
            for (int i = 0; i < count; i++) {
                queue.add(new Car(carIdGenerator.getAndIncrement(), type));
            }
            availableCars.put(type, queue);
        }
    }

    public Car rentCar(CarType type) {
        Queue<Car> cars = availableCars.get(type);
        if (cars != null && !cars.isEmpty()) {
            return cars.poll();
        }
        return null;
    }

    public void returnCar(Car car) {
        availableCars.get(car.getType()).offer(car);
    }

    public int availableCount(CarType type) {
        return availableCars.get(type).size();
    }
}
