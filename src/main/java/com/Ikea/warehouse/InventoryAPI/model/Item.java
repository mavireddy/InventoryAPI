package com.Ikea.warehouse.InventoryAPI.model;

import java.util.List;

public class Item {

    private String name;
    private List<RequiredInventory> contain_articles;

    public Item(String name, List<RequiredInventory> contain_articles) {
        this.name = name;
        this.contain_articles = contain_articles;
    }

    public Item(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RequiredInventory> getContain_articles() {
        return contain_articles;
    }

    public void setContain_articles(List<RequiredInventory> contain_articles) {
        this.contain_articles = contain_articles;
    }
}
