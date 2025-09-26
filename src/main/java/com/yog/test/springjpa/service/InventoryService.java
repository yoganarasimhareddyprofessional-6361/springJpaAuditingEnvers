package com.yog.test.springjpa.service;

import com.yog.test.springjpa.entity.Inventory;
import org.springframework.data.domain.Page;

import java.util.List;


public interface InventoryService {

    public Inventory addInventory(Inventory inventory);

    public Inventory updateInventory(int id, Inventory inventory);

    public String deleteInventoryById(int id);

    public Inventory getInventoryById(int id);

    public List<Inventory> getInventoryByName(String name);

    public List<Inventory> getInventoryByDescription(String description);

    public List<Inventory> getInventoryByNameAndDescription(String name, String description);

    public List<Inventory> getInventoryByNameOrDescription(String name, String description);

    public List<Inventory> getInventoryProductByPriceCustomQuery(Double price);

    public List<Inventory> getAllInventory();

    //IN Clause (Arrays of prices / List of prices)
    public List<Inventory> getInventoryProductsByPriceUsingIn(List<Double> prices);

    //Less Than
    public List<Inventory> getInventoryProductsByPriceUsingLessThan(Double price);

    //More Than
    public List<Inventory> getInventoryProductsByPriceUsingMoreThan(Double price);

    //BETWEEN (examples : dates / prices)
    public List<Inventory> getInventoryProductsByPriceUsingBetween(Double price1, Double price2);

    //LIKE --> special Case
    public List<Inventory> getInventoryProductsByNameUsingLike(String name);

    //Sorting
    public List<Inventory> getInventoryProductsByFieldNameUsingSort(String fieldName);

    //PAGINATION --> (offset,limit <--> start,end/size)
    public Page<Inventory> getInventoryProductsByPage(int offset, int limit);
}
