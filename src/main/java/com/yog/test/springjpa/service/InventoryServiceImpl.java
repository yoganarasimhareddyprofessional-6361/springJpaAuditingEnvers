package com.yog.test.springjpa.service;

import com.yog.test.springjpa.entity.Inventory;
import com.yog.test.springjpa.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//Am not any validations in any of the methods cz am only using this example for JPA/JPA queries
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository repo;


    @Override
    public Inventory addInventory(Inventory inventory) {
        return repo.save(inventory);
    }


    @Override
    public Inventory updateInventory(int id, Inventory inventory) {
        //        for update
        Optional<Inventory> byId = repo.findById(id);
        //        1st :get repo by id
        //2nd : if repo has data then update by using save operation
        if (byId.isPresent()) {
            Inventory inventory1 = byId.get();
            inventory1.setName(inventory.getName());
            inventory1.setDescription(inventory.getDescription());
            inventory1.setPrice(inventory.getPrice());
            return repo.save(inventory1);
        }
        return null;
    }

    @Override
    public String deleteInventoryById(int id) {
        repo.deleteById(id);
        return "Inventory deleted";
    }

    @Override
    public Inventory getInventoryById(int id) {
        Optional<Inventory> byId = repo.findById(id);
        return byId.orElse(null);
    }

    @Override
    public List<Inventory> getInventoryByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Inventory> getInventoryByDescription(String description) {
        return repo.findByDescription(description);
    }

    @Override
    public List<Inventory> getInventoryByNameAndDescription(String name, String description) {
        return repo.findByNameAndDescription(name, description);
    }

    @Override
    public List<Inventory> getInventoryByNameOrDescription(String name, String description) {
        return repo.findByNameOrDescription(name, description);
    }

    @Override
    public List<Inventory> getInventoryProductByPriceCustomQuery(Double price) {
        return repo.findByInventoryPrice(price);
    }


    @Override
    public List<Inventory> getAllInventory() {
        return repo.findAll();
    }

    @Override
    //IN Clause (Arrays of prices / List of prices)
    public List<Inventory> getInventoryProductsByPriceUsingIn(List<Double> prices) {
        return repo.findByPriceIn(prices);
    }

    @Override
    public List<Inventory> getInventoryProductsByPriceUsingLessThan(Double price) {
        return repo.findByPriceLessThan(price);
    }

    @Override
    public List<Inventory> getInventoryProductsByPriceUsingMoreThan(Double price) {
        return repo.findByPriceGreaterThan(price);
    }

    @Override
    public List<Inventory> getInventoryProductsByPriceUsingBetween(Double price1, Double price2) {
        return repo.findByPriceBetween(price1, price2);
    }

    @Override
    public List<Inventory> getInventoryProductsByNameUsingLike(String name) {
        //return repo.findByNameIgnoreCaseContaining(name); // if u want to ignore case
        return repo.findByNameContaining(name);
    }

    @Override
    public List<Inventory> getInventoryProductsByFieldNameUsingSort(String fieldName) {
        return repo.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }

    @Override
    public Page<Inventory> getInventoryProductsByPage(int offset, int limit) {
        //special case for pagination where u also pass the sort field name along with offset and limit values.
        //repo.findAll(PageRequest.of(offset, limit).withSort(Sort.by(Sort.Direction.ASC, fieldName)));

        // use .getContent() to get the content of the page as List<object> without pagination details in response
        //List<Inventory> content = repo.findAll(PageRequest.of(offset, limit)).getContent();
        return repo.findAll(PageRequest.of(offset, limit));
    }


}
