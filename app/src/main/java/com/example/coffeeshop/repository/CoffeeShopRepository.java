package com.example.coffeeshop.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.coffeeshop.data.CoffeeDao;
import com.example.coffeeshop.data.CoffeeShopDatabase;
import com.example.coffeeshop.data.UserDao;
import com.example.coffeeshop.model.CoffeeItem;
import com.example.coffeeshop.model.User;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Репозиторий для управления данными приложения
 */
public class CoffeeShopRepository {
    
    private UserDao userDao;
    private CoffeeDao coffeeDao;
    private ExecutorService executor;
    
    public CoffeeShopRepository(Application application) {
        CoffeeShopDatabase database = CoffeeShopDatabase.getDatabase(application);
        userDao = database.userDao();
        coffeeDao = database.coffeeDao();
        executor = Executors.newFixedThreadPool(2);
        
        // Инициализация базовых данных
        initializeData();
    }
    
    // Методы для работы с пользователями
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public void insertUser(User user) {
        executor.execute(() -> userDao.insertUser(user));
    }
    
    public void updateUser(User user) {
        executor.execute(() -> userDao.updateUser(user));
    }
    
    public void deleteUser(User user) {
        executor.execute(() -> userDao.deleteUser(user));
    }
    
    public User login(String username, String password) {
        return userDao.login(username, password);
    }
    
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
    
    // Методы для работы с кофе
    public List<CoffeeItem> getAllCoffeeItems() {
        return coffeeDao.getAllCoffeeItems();
    }
    
    public List<CoffeeItem> getAvailableCoffeeItems() {
        return coffeeDao.getAvailableCoffeeItems();
    }
    
    public List<CoffeeItem> getCoffeeByCategory(String category) {
        return coffeeDao.getCoffeeByCategory(category);
    }
    
    public void insertCoffeeItem(CoffeeItem coffeeItem) {
        executor.execute(() -> coffeeDao.insertCoffeeItem(coffeeItem));
    }
    
    public void updateCoffeeItem(CoffeeItem coffeeItem) {
        executor.execute(() -> coffeeDao.updateCoffeeItem(coffeeItem));
    }
    
    public void deleteCoffeeItem(CoffeeItem coffeeItem) {
        executor.execute(() -> coffeeDao.deleteCoffeeItem(coffeeItem));
    }
    
    public void updateCoffeeAvailability(int id, boolean isAvailable) {
        executor.execute(() -> coffeeDao.updateAvailability(id, isAvailable));
    }
    
    /**
     * Инициализация базовых данных при первом запуске
     */
    private void initializeData() {
        executor.execute(() -> {
            // Проверяем, есть ли уже данные
            List<User> users = userDao.getAllUsers();
            if (users.size() == 0) {
                // Создаем администратора по умолчанию
                User admin = new User("admin", "admin123", "admin", "admin@coffeeshop.com");
                userDao.insertUser(admin);
                
                // Создаем обычного пользователя
                User user = new User("user", "user123", "user", "user@coffeeshop.com");
                userDao.insertUser(user);
            }
            
            List<CoffeeItem> coffeeItems = coffeeDao.getAllCoffeeItems();
            if (coffeeItems.size() == 0) {
                // Добавляем базовые позиции кофе
                CoffeeItem espresso = new CoffeeItem(
                    "Эспрессо", 
                    "Классический крепкий кофе", 
                    120.0, 
                    "", 
                    "hot"
                );
                coffeeDao.insertCoffeeItem(espresso);
                
                CoffeeItem americano = new CoffeeItem(
                    "Американо", 
                    "Эспрессо с добавлением горячей воды", 
                    150.0, 
                    "", 
                    "hot"
                );
                coffeeDao.insertCoffeeItem(americano);
                
                CoffeeItem cappuccino = new CoffeeItem(
                    "Капучино", 
                    "Эспрессо с молочной пеной", 
                    180.0, 
                    "", 
                    "hot"
                );
                coffeeDao.insertCoffeeItem(cappuccino);
                
                CoffeeItem latte = new CoffeeItem(
                    "Латте", 
                    "Эспрессо с большим количеством молока", 
                    200.0, 
                    "", 
                    "hot"
                );
                coffeeDao.insertCoffeeItem(latte);
                
                CoffeeItem frappe = new CoffeeItem(
                    "Фраппе", 
                    "Холодный кофе со льдом и молоком", 
                    220.0, 
                    "", 
                    "cold"
                );
                coffeeDao.insertCoffeeItem(frappe);
                
                CoffeeItem tiramisu = new CoffeeItem(
                    "Тирамису", 
                    "Классический итальянский десерт", 
                    250.0, 
                    "", 
                    "dessert"
                );
                coffeeDao.insertCoffeeItem(tiramisu);
            }
        });
    }
}
