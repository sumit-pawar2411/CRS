package org.crs.model;

import org.crs.enums.CarType;
import org.crs.interfaces.Reservable;

import java.util.UUID;

public abstract class Car implements Reservable {

    private final String id;
    private final CarType type;
    private boolean available;

    protected Car(CarType type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public CarType getType() {
        return type;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void reserve() {
        if (!available) {
            throw new RuntimeException("Car already reserved: " + id);
        }
        available = false;
    }

    @Override
    public void release() {
        available = true;
    }
}