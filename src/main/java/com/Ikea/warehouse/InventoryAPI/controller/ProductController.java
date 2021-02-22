package com.Ikea.warehouse.InventoryAPI.controller;

import com.Ikea.warehouse.InventoryAPI.model.Availability;
import com.Ikea.warehouse.InventoryAPI.model.Product;
import com.Ikea.warehouse.InventoryAPI.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/ikea")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/uploadProductFile", method = RequestMethod.POST)
    @ResponseBody
    private String saveProductThroughFile(@RequestParam("file") MultipartFile file) throws IOException {
        final Product products = readProductJsonFile(file);
        productService.saveProducts(products);
        System.out.println(products);
        return "Products updated successfully";
    }

    @GetMapping("/getAllProducts")
    public List<Availability> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/sellProduct/{productCode}")
    public String sellProducts(@PathVariable("productCode") String productCode) {
        return productService.sellProducts(productCode);
    }

    public Product readProductJsonFile(MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File tempFile = new File("src/main/resources/tempProductFile.tmp");
        file.transferTo(tempFile);
        InputStream inputStream = new FileInputStream(tempFile);
        TypeReference<Product> typeReference = new TypeReference<Product>() {};
        Product products = objectMapper.readValue(inputStream, typeReference);

        return products;
    }
}
