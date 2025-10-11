package com.example.coffeeshop.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.io.Serializable;

/**
 * Модель кофе для меню кофейни
 */
@Entity(tableName = "coffee_items")
public class CoffeeItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private String description;
    private double price;
    private String imagePath;
    private String category; // "hot", "dessert"
    private boolean isAvailable;
    
    public CoffeeItem() {}
    
    @Ignore
    public CoffeeItem(String name, String description, double price, String imagePath, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
        this.isAvailable = true;
    }
    
    // Геттеры и сеттеры
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
}
