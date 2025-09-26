package com.yog.test.springjpa.repository;

import com.yog.test.springjpa.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//JpaRepository<Inventory,Integer> --> JpaRepository<Entity Class , Data type of primary key>
//@Repository -- is optional As Spring recognizes the repositories by the fact that they extend one of the predefined Repository interfaces.
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    //JPA queries ( findBy + attribute name )
    List<Inventory> findByName(String name);

    //JPA queries ( findBy + attribute name )
    List<Inventory> findByDescription(String description);

    //JPA queries ( findBy + attribute name 1 + operator + attribute name 2 )
    List<Inventory> findByNameAndDescription(String name, String description);

    //JPA queries ( findBy + attribute name 1 + operator + attribute name 2 )
    List<Inventory> findByNameOrDescription(String name, String description);

    //@Query(value = "SELECT * FROM inventory_tbl WHERE price = ?1",nativeQuery = true)
    @Query("from Inventory i where i.price= ?1 ")
    // use always entity class name when ur using position based parameter
    //position based parameter
    //@Query("from inventory_tbl p where p.price= :price") //named parameter base index
    List<Inventory> findByInventoryPrice(Double price);

    //JPA queries ( findBy + attribute name  + operator )
    List<Inventory> findByPriceIn(List<Double> prices);

    //JPA queries ( findBy + attribute name  + operator )
    List<Inventory> findByPriceLessThan(Double price);

    //JPA queries ( findBy + attribute name  + operator )
    List<Inventory> findByPriceGreaterThan(Double price);

    //JPA queries ( findBy + attribute name  + operator )
    List<Inventory> findByPriceBetween(Double price1, Double price2);

    //JPA queries ( findBy + attribute name  + operator )  // here like is special case where u use containing operator for like in JPA
    List<Inventory> findByNameContaining(String name);
}
