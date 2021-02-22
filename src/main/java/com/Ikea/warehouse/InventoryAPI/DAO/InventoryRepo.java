package com.Ikea.warehouse.InventoryAPI.DAO;

import com.Ikea.warehouse.InventoryAPI.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepo extends CrudRepository<Article, Integer> {
}
