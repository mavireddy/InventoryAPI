package com.Ikea.warehouse.InventoryAPI.controller;

import com.Ikea.warehouse.InventoryAPI.model.Article;
import com.Ikea.warehouse.InventoryAPI.model.Inventory;
import com.Ikea.warehouse.InventoryAPI.service.InventoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/ikea")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/home")
    public ModelAndView getHome() {
        ModelAndView model = new ModelAndView("inventory");
        return model;
    }

    @GetMapping("/inventory")
    private List<Article> getInventory() {
        return inventoryService.getInventory();
    }

    @GetMapping("/inventory/{articleid}")
    private Article getArticle(@PathVariable("articleid") int articleid) {
        return inventoryService.getArticleById(articleid);
    }

    @PostMapping("/inventory/articles")
    private String saveArticle(@RequestBody List<Article> articles) throws IOException {
        inventoryService.saveOrUpdateList(articles);
        return "Inventory updated successfully";
    }

    @RequestMapping(value = "/uploadInventoryFile", method = RequestMethod.POST)
    @ResponseBody
    private String saveArticleThroughFile(@RequestParam("file") MultipartFile file) throws IOException {
        final List<Article> articles = readInventoryJsonFile(file);
        inventoryService.saveOrUpdateList(articles);
        return "Inventory updated successfully";
    }

    @DeleteMapping("/inventory/{articleid}")
    private void deleteArticle(@PathVariable("articleid") int articleid) {
        inventoryService.deleteArticle(articleid);
    }

    @PutMapping("/inventory/articles")
    private Article update(@RequestBody Article article) {
        inventoryService.saveOrUpdate(article);
        return article;
    }

    public List<Article> readInventoryJsonFile(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File tempFile = new File("src/main/resources/tempInventoryFile.tmp");
        file.transferTo(tempFile);
        InputStream inputStream = new FileInputStream(tempFile);
        TypeReference<Inventory> typeReference = new TypeReference<Inventory>() {};
        Inventory inventories = objectMapper.readValue(inputStream, typeReference);

        return inventories.getInventory();
    }
}
