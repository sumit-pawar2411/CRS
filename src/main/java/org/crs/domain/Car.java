package org.crs.domain;

public class Car {

    private final int id;
    private final CarType type;

    public Car(int id, CarType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public CarType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Car{id=" + id + ", type=" + type + '}';
    }
}
