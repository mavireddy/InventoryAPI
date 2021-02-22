package com.Ikea.warehouse.InventoryAPI.model;

public class Availability {

    private String name;
    private int available;

    public Availability() {
    }

    public Availability(String name, int available) {
        this.name = name;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
