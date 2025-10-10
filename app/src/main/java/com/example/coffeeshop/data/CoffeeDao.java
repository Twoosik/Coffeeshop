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
    
    @Query("SELECT * FROM coffee_items WHERE category = :category ORDER BY name")
    List<CoffeeItem> getCoffeeByCategory(String category);
    
    @Query("SELECT * FROM coffee_items WHERE isAvailable = 1 ORDER BY category, name")
    List<CoffeeItem> getAvailableCoffeeItems();
    
    @Query("SELECT * FROM coffee_items WHERE id = :id")
    CoffeeItem getCoffeeById(int id);
    
    @Query("SELECT * FROM coffee_items WHERE name LIKE :searchQuery OR description LIKE :searchQuery")
    List<CoffeeItem> searchCoffee(String searchQuery);
    
    @Insert
    void insertCoffeeItem(CoffeeItem coffeeItem);
    
    @Update
    void updateCoffeeItem(CoffeeItem coffeeItem);
    
    @Delete
    void deleteCoffeeItem(CoffeeItem coffeeItem);
    
    @Query("DELETE FROM coffee_items WHERE id = :id")
    void deleteCoffeeById(int id);
    
    @Query("UPDATE coffee_items SET isAvailable = :isAvailable WHERE id = :id")
    void updateAvailability(int id, boolean isAvailable);
}
