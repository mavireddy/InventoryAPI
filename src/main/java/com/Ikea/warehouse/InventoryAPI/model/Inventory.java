package com.Ikea.warehouse.InventoryAPI.model;

import java.util.List;

public class Inventory {

    private List<Article> inventory;

    public Inventory(List<Article> inventory) {
        this.inventory = inventory;
    }

    public Inventory() {
    }

    public List<Article> getInventory() {
        return inventory;
    }

    public void setInventory(List<Article> inventory) {
        this.inventory = inventory;
    }
}
