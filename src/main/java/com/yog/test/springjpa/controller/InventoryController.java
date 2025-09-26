package com.yog.test.springjpa.controller;

import com.yog.test.springjpa.entity.Inventory;
import com.yog.test.springjpa.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable int id) {
        return service.getInventoryById(id);
    }

    @GetMapping("/all")
    public List<Inventory> getAllInventory() {
        return service.getAllInventory();
    }

    @GetMapping("/name/{name}")
    public List<Inventory> getInventoryByName(@PathVariable String name) {
        return service.getInventoryByName(name);
    }

    @GetMapping("/description/{description}")
    public List<Inventory> getInventoryByDescription(@PathVariable String description) {
        return service.getInventoryByDescription(description);
    }

    @GetMapping("/name/{name}/and/description/{description}")
    public List<Inventory> getInventoryByNameAndDescription(@PathVariable String name, @PathVariable String description) {
        return service.getInventoryByNameAndDescription(name, description);
    }

    @GetMapping("/name/{name}/or/description/{description}")
    public List<Inventory> getInventoryByNameOrDescription(@PathVariable String name, @PathVariable String description) {
        return service.getInventoryByNameOrDescription(name, description);
    }

    @GetMapping("/price/{price}")
    public List<Inventory> getInventoryProductByPriceCustomQuery(@PathVariable Double price) {
        return service.getInventoryProductByPriceCustomQuery(price);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteInventoryById(@PathVariable int id) {
        return service.deleteInventoryById(id);
    }

    @PostMapping("/add")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return service.addInventory(inventory);
    }

    @PutMapping("/update/{id}")
    public Inventory updateInventory(@PathVariable int id, @RequestBody Inventory inventory) {
        return service.updateInventory(id, inventory);
    }

    @GetMapping("/search-in/price")
    public List<Inventory> getInventoryProductsByPriceUsingIn(@RequestBody List<Double> prices) {
        return service.getInventoryProductsByPriceUsingIn(prices);
    }

    @GetMapping("/search-less-than/price/{price}")
    public List<Inventory> getInventoryProductsByPriceUsingLessThan(@PathVariable Double price) {
        return service.getInventoryProductsByPriceUsingLessThan(price);
    }

    @GetMapping("/search-more-than/price/{price}")
    public List<Inventory> getInventoryProductsByPriceUsingMoreThan(@PathVariable Double price) {
        return service.getInventoryProductsByPriceUsingMoreThan(price);
    }

    @GetMapping("/search-between/price/{price1}/{price2}")
    public List<Inventory> getInventoryProductsByPriceUsingBetween(@PathVariable Double price1, @PathVariable Double price2) {
        return service.getInventoryProductsByPriceUsingBetween(price1, price2);
    }

    @GetMapping("/search-like/name/{name}")
    public List<Inventory> getInventoryProductsByNameUsingLike(@PathVariable String name) {
        return service.getInventoryProductsByNameUsingLike(name);
    }

    @GetMapping("/search-sort/field/{fieldName}")
    public List<Inventory> getInventoryProductsByFieldNameUsingSort(@PathVariable String fieldName) {
        return service.getInventoryProductsByFieldNameUsingSort(fieldName);
    }

    @GetMapping("/search-page/{offset}/{limit}")
    public Page<Inventory> getInventoryProductsByPage(@PathVariable int offset, @PathVariable int limit) {
        return service.getInventoryProductsByPage(offset, limit);
    }

}
