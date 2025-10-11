package com.example.coffeeshop.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.coffeeshop.model.CoffeeItem;
import java.util.List;

/**
 * DAO для работы с кофе
 */
@Dao
public interface CoffeeDao {
    
    @Query("SELECT * FROM coffee_items ORDER BY category, name")
    List<CoffeeItem> getAllCoffeeItems();
    
    
    @Query("SELECT * FROM coffee_items WHERE isAvailable = 1 ORDER BY category, name")
    List<CoffeeItem> getAvailableCoffeeItems();
    
    
    
    @Insert
    void insertCoffeeItem(CoffeeItem coffeeItem);
    
    @Update
    void updateCoffeeItem(CoffeeItem coffeeItem);
    
    @Delete
    void deleteCoffeeItem(CoffeeItem coffeeItem);
    
    @Query("DELETE FROM coffee_items")
    void deleteAllCoffeeItems();
    
    @Query("SELECT COUNT(*) FROM coffee_items")
    int getCoffeeCount();
    
    
}
