package com.Ikea.warehouse.InventoryAPI.model;

import java.util.List;

public class Product {

    private List<Item> products;

    public Product() {
    }

    public Product(List<Item> products) {
        this.products = products;
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }
}
