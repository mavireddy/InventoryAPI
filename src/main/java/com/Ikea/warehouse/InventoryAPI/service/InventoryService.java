package com.Ikea.warehouse.InventoryAPI.service;

import com.Ikea.warehouse.InventoryAPI.DAO.InventoryRepo;
import com.Ikea.warehouse.InventoryAPI.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    public List<Article> getInventory() {
        List<Article> inventory = new ArrayList<>();
        inventoryRepo.findAll().forEach(inv -> inventory.add(inv));
        return inventory;
    }

    public Article getArticleById(int id) {
        return inventoryRepo.findById(id).get();
    }

    public void saveOrUpdate(Article article) {
        inventoryRepo.save(article);
    }

    public void saveOrUpdateList(List<Article> articles) {
        for (Article article : articles) {
            inventoryRepo.save(article);
        }
    }

    public void deleteArticle(int id) {
        inventoryRepo.deleteById(id);
    }

    public void update(Article article, int id) {
        inventoryRepo.save(article);
    }

}
