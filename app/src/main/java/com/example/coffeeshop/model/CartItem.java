package com.example.coffeeshop.model;

import java.io.Serializable;

/**
 * Модель элемента корзины
 */
public class CartItem implements Serializable {
    private CoffeeItem coffeeItem;
    private int quantity;
    
    public CartItem(CoffeeItem coffeeItem, int quantity) {
        this.coffeeItem = coffeeItem;
        this.quantity = quantity;
    }
    
    public CoffeeItem getCoffeeItem() {
        return coffeeItem;
    }
    
    public void setCoffeeItem(CoffeeItem coffeeItem) {
        this.coffeeItem = coffeeItem;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return coffeeItem.getPrice() * quantity;
    }
    
    public void incrementQuantity() {
        this.quantity++;
    }
    
    public void decrementQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }
}
