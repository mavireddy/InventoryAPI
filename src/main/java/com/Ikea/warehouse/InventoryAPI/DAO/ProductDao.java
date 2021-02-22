package com.Ikea.warehouse.InventoryAPI.DAO;

import com.Ikea.warehouse.InventoryAPI.model.Availability;
import com.Ikea.warehouse.InventoryAPI.model.Item;
import com.Ikea.warehouse.InventoryAPI.model.Product;
import com.Ikea.warehouse.InventoryAPI.model.RequiredInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String sellProducts(String productCode) {

        String sqlQuery = "select pi.p_name, a.art_id, pi.art_required, a.stock  from product_inventory pi, article a where pi.art_id = a.art_id and pi.p_name = ?";
        HashMap<String, Integer> availabilityMap = new HashMap<>();
        final List<Map<String, Object>> productMap = jdbcTemplate.queryForList(sqlQuery, productCode);
        boolean exceed_flag = false;

        for (Map<String, Object> map : productMap) {
            final int art_id = (int) map.get("art_id");
            final int art_required = (int) map.get("art_required");
            final int in_stock = (int) map.get("stock");

            if (in_stock - art_required >= 0) {
                availabilityMap.put(Integer.toString(art_id), (in_stock - art_required));
            } else {
                exceed_flag = true;
            }
        }
        if (!exceed_flag) {
            for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
                String updateSqlQuery = "update Article set stock = ? where art_id = ?";
                jdbcTemplate.update(updateSqlQuery, entry.getValue(), entry.getKey());
            }
            return "Item sold/removed Successfully";
        } else {
            return "Inventory is not sufficient";
        }
    }

    public void saveProducts(Product products) {

        final List<Item> productList = products.getProducts();
        for (Item product : productList) {
            final String name = product.getName();
            final List<RequiredInventory> contain_articles = product.getContain_articles();
            String insertProductQuery = "insert into products values (?)";
            jdbcTemplate.update(insertProductQuery, name);
            for (RequiredInventory inv : contain_articles) {
                String insertQuery = "insert into product_inventory values (?,?,?) ";
                jdbcTemplate.update(insertQuery, name, inv.getArt_id(), inv.getAmount_of());
            }
        }
    }

    public List<Availability> getAvailableProducts() {

        final ArrayList<String> products = getProducts();
        List<Availability> availableList = new ArrayList<>();
        for (String productcode : products) {
            availableList.add(getAvailability(productcode));
        }
        return availableList;
    }

    public ArrayList<String> getProducts() {

        String sql = "select * from products";
        final List<Map<String, Object>> products = jdbcTemplate.queryForList(sql);
        ArrayList<String> list = new ArrayList<>();
        for (Map<String, Object> product : products) {
            list.add((String) product.get("name"));
        }
        return list;
    }

    public Availability getAvailability(String productCode) {

        String sqlQuery = "select pi.p_name, a.art_id, pi.art_required, a.stock  from product_inventory pi, article a where pi.art_id = a.art_id and pi.p_name = ?";
        final List<Map<String, Object>> productMap = jdbcTemplate.queryForList(sqlQuery, productCode);

        Availability availability = new Availability();
        int maxAvailable = -1;
        for (Map<String, Object> map : productMap) {
            availability.setName((String) map.get("p_name"));
            int required = (int) map.get("art_required");
            int in_stock = (int) map.get("stock");

            int available = in_stock / required;
            if (maxAvailable == -1) maxAvailable = available;
            if (maxAvailable > available) maxAvailable = available;
        }
        availability.setAvailable(maxAvailable);
        return availability;
    }
}

