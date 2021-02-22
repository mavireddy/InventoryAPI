package com.Ikea.warehouse.InventoryAPI.service;

import com.Ikea.warehouse.InventoryAPI.DAO.ProductDao;
import com.Ikea.warehouse.InventoryAPI.model.Availability;
import com.Ikea.warehouse.InventoryAPI.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void saveProducts(Product products) {
        productDao.saveProducts(products);
    }

    public List<Availability> getAllProducts() {
        final List<Availability> availableProducts = productDao.getAvailableProducts();
        return availableProducts;
    }

    public String sellProducts(String productCode) {
        return productDao.sellProducts(productCode);
    }
}
