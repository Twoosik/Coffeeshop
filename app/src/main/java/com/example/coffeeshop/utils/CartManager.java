package com.example.coffeeshop.utils;

import android.content.Context;
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
    private FileManager fileManager;
    private Context context;
    
    private CartManager() {
        cartItems = new ArrayList<>();
    }
    
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }
    
    public void setContext(Context context) {
        this.context = context;
        this.fileManager = new FileManager(context);
        loadCartFromFile();
    }
    
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    
    public void addItem(CoffeeItem coffeeItem) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCoffeeItem().getId() == coffeeItem.getId()) {
                cartItem.incrementQuantity();
                saveCartToFile();
                return;
            }
        }
        
        cartItems.add(new CartItem(coffeeItem, 1));
        saveCartToFile();
    }
    
    public void removeItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        saveCartToFile();
    }
    
    public void updateQuantity(CartItem cartItem, int quantity) {
        if (quantity <= 0) {
            removeItem(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            saveCartToFile();
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
        if (fileManager != null) {
            fileManager.deleteFile("cart_data.txt");
        }
    }
    
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
    
    public void saveCartToFile() {
        if (fileManager != null) {
            List<CoffeeItem> coffeeItems = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                for (int i = 0; i < cartItem.getQuantity(); i++) {
                    coffeeItems.add(cartItem.getCoffeeItem());
                }
            }
            fileManager.saveCartItems(coffeeItems);
        }
    }
    
    private void loadCartFromFile() {
        if (fileManager != null) {
            List<CoffeeItem> coffeeItems = fileManager.loadCartItems();
            cartItems.clear();
            
            for (CoffeeItem coffeeItem : coffeeItems) {
                boolean found = false;
                for (CartItem cartItem : cartItems) {
                    if (cartItem.getCoffeeItem().getId() == coffeeItem.getId()) {
                        cartItem.incrementQuantity();
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    cartItems.add(new CartItem(coffeeItem, 1));
                }
            }
        }
    }
}
