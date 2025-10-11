package com.example.coffeeshop.repository;

import android.app.Application;
import com.example.coffeeshop.data.CoffeeDao;
import com.example.coffeeshop.data.CoffeeShopDatabase;
import com.example.coffeeshop.data.UserDao;
import com.example.coffeeshop.model.CoffeeItem;
import com.example.coffeeshop.model.User;
import com.example.coffeeshop.utils.FileManager;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeShopRepository {
    
    private UserDao userDao;
    private CoffeeDao coffeeDao;
    private ExecutorService executor;
    private FileManager fileManager;
    
    public CoffeeShopRepository(Application application) {
        CoffeeShopDatabase database = CoffeeShopDatabase.getDatabase(application);
        userDao = database.userDao();
        coffeeDao = database.coffeeDao();
        executor = Executors.newFixedThreadPool(2);
        fileManager = new FileManager(application);
        
        initializeData();
    }
    
    
    public User login(String username, String password) {
        User dbUser = userDao.login(username, password);
        List<User> fileUsers = fileManager.loadUsers();
        
        if (!fileUsers.isEmpty()) {
            for (User user : fileUsers) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        
        if (dbUser != null && fileUsers.isEmpty()) {
            fileManager.saveUsers(userDao.getAllUsers());
        }
        
        return dbUser;
    }
    
    
    public List<CoffeeItem> getAvailableCoffeeItems() {
        List<CoffeeItem> dbItems = coffeeDao.getAvailableCoffeeItems();
        List<CoffeeItem> fileItems = fileManager.loadCoffeeItems();
        
        if (!fileItems.isEmpty()) {
            return fileItems;
        }
        
        if (!dbItems.isEmpty()) {
            fileManager.saveCoffeeItems(dbItems);
        }
        
        return dbItems;
    }
    
    
    
    
    public void clearAndReinitializeData() {
        executor.execute(() -> {
            coffeeDao.deleteAllCoffeeItems();
            userDao.deleteAllUsers();
            
            fileManager.deleteFile("coffee_data.txt");
            fileManager.deleteFile("user_data.txt");
            
            initializeDataInternal();
        });
    }
    
    private void initializeData() {
        executor.execute(() -> {
            initializeDataInternal();
        });
    }
    
    private void initializeDataInternal() {
        User admin = new User("admin", "admin123", "admin", "admin@coffeeshop.com");
        userDao.insertUser(admin);
        
        User user = new User("user", "user123", "user", "user@coffeeshop.com");
        userDao.insertUser(user);
        
        
        CoffeeItem espresso = new CoffeeItem(
            "Эспрессо", 
            "Классический крепкий кофе", 
            120.0, 
            "https://neibors.ru/image/cache/catalog/napitki/2presso-1200x800.jpg",
            "hot"
        );
                coffeeDao.insertCoffeeItem(espresso);
                
        CoffeeItem americano = new CoffeeItem(
            "Американо", 
            "Эспрессо с добавлением горячей воды", 
            150.0, 
            "https://avatars.mds.yandex.net/i?id=4469ee4e9f8d53e7649078f4acc2778f546841d0-5163221-images-thumbs&n=13",
            "hot"
        );
                coffeeDao.insertCoffeeItem(americano);
                
        CoffeeItem cappuccino = new CoffeeItem(
            "Капучино", 
            "Эспрессо с молочной пеной", 
            180.0, 
            "https://gastrobarmoscow.ru/content/uploads/2020/06/latte-scaled.jpg",
            "hot"
        );
                coffeeDao.insertCoffeeItem(cappuccino);
                
        CoffeeItem latte = new CoffeeItem(
            "Латте", 
            "Эспрессо с большим количеством молока", 
            200.0, 
            "https://i.pinimg.com/originals/78/ca/7c/78ca7c237ca3eef04f493728b59c2a39.jpg",
            "hot"
        );
                coffeeDao.insertCoffeeItem(latte);
                
        CoffeeItem macchiato = new CoffeeItem(
            "Макиато", 
            "Эспрессо с каплей молока", 
            160.0, 
            "https://img.freepik.com/free-photo/view-3d-coffee-cup_23-2151083695.jpg?semt=ais_hybrid&w=740",
            "hot"
        );
                coffeeDao.insertCoffeeItem(macchiato);
                
                
        CoffeeItem flatWhite = new CoffeeItem(
            "Флэт Уайт",
            "Двойной эспрессо с молоком", 
            190.0, 
            "https://avatars.mds.yandex.net/i?id=5b709b32fb1eda4ec263eb310c8081ff_l-5283245-images-thumbs&n=13",
            "hot"
        );
                coffeeDao.insertCoffeeItem(flatWhite);
                
                
                
        CoffeeItem tiramisu = new CoffeeItem(
            "Тирамису", 
            "Классический итальянский десерт", 
            280.0, 
            "https://citymagazine.danas.rs/wp-content/uploads/2024/07/shutterstock_2464069613.jpg",
            "dessert"
        );
                coffeeDao.insertCoffeeItem(tiramisu);
                
        CoffeeItem cheesecake = new CoffeeItem(
            "Чизкейк", 
            "Классический десерт", 
            320.0, 
            "https://img.vkusvill.ru/pim/images/site_LargeWebP/7c1853be-251d-4dbf-8528-579eb32b631d.webp?1682429535.4052",
            "dessert"
        );
                coffeeDao.insertCoffeeItem(cheesecake);
                
        CoffeeItem brownie = new CoffeeItem(
            "Брауни", 
            "Шоколадный десерт", 
            250.0, 
            "https://avatars.mds.yandex.net/i?id=c6da4fe46c4a9b63cb8945730756cec0_l-5309459-images-thumbs&n=13",
            "dessert"
        );
                coffeeDao.insertCoffeeItem(brownie);
                
        CoffeeItem croissant = new CoffeeItem(
            "Круассан", 
            "Французская выпечка", 
            180.0, 
            "https://api.cafe.store/file/get?id=3CA60872-7903-4180-94C3-15198A808919",
            "dessert"
        );
                coffeeDao.insertCoffeeItem(croissant);
                
        CoffeeItem muffin = new CoffeeItem(
            "Маффин", 
            "Американская выпечка", 
            150.0, 
            "https://marr.ru/upload/iblock/7da/fz7cm3d3u7w20xfkevleqvr7aatd2y03.jpg",
            "dessert"
        );
                coffeeDao.insertCoffeeItem(muffin);
        
        List<User> users = userDao.getAllUsers();
        List<CoffeeItem> coffeeItems = coffeeDao.getAvailableCoffeeItems();
        
        fileManager.saveUsers(users);
        fileManager.saveCoffeeItems(coffeeItems);
    }
    
    public void saveCartToFile(List<CoffeeItem> cartItems) {
        fileManager.saveCoffeeItems(cartItems);
    }
    
    public List<CoffeeItem> loadCartFromFile() {
        return fileManager.loadCoffeeItems();
    }
}
