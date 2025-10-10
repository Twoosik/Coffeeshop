package com.example.coffeeshop.utils;

import com.example.coffeeshop.model.CartItem;
import com.example.coffeeshop.model.CoffeeItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер корзины для управления товарами
 */
public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems;
    
    private CartManager() {
        cartItems = new ArrayList<>();
    }
    
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }
    
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    
    public void addItem(CoffeeItem coffeeItem) {
        // Проверяем, есть ли уже такой товар в корзине
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCoffeeItem().getId() == coffeeItem.getId()) {
                cartItem.incrementQuantity();
                return;
            }
        }
        
        // Если товара нет в корзине, добавляем новый
        cartItems.add(new CartItem(coffeeItem, 1));
    }
    
    public void removeItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }
    
    public void updateQuantity(CartItem cartItem, int quantity) {
        if (quantity <= 0) {
            removeItem(cartItem);
        } else {
            cartItem.setQuantity(quantity);
        }
    }
    
    public double getTotalPrice() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getTotalPrice();
        }
        return total;
    }
    
    public int getTotalItems() {
        int total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getQuantity();
        }
        return total;
    }
    
    public void clearCart() {
        cartItems.clear();
    }
    
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
