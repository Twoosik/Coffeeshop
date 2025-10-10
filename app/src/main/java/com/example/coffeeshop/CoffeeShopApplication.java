package com.example.coffeeshop;

import android.app.Application;
import com.example.coffeeshop.repository.CoffeeShopRepository;

/**
 * Application класс для инициализации репозитория
 */
public class CoffeeShopApplication extends Application {
    
    private CoffeeShopRepository repository;
    
    @Override
    public void onCreate() {
        super.onCreate();
        repository = new CoffeeShopRepository(this);
    }
    
    public CoffeeShopRepository getRepository() {
        return repository;
    }
}
