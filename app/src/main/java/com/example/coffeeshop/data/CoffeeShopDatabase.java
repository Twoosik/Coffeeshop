package com.example.coffeeshop.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import com.example.coffeeshop.model.User;
import com.example.coffeeshop.model.CoffeeItem;

/**
 * База данных Room для приложения кофейни
 */
@Database(
    entities = {User.class, CoffeeItem.class},
    version = 1,
    exportSchema = false
)
public abstract class CoffeeShopDatabase extends RoomDatabase {
    
    public abstract UserDao userDao();
    public abstract CoffeeDao coffeeDao();
    
    private static volatile CoffeeShopDatabase INSTANCE;
    
    public static CoffeeShopDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CoffeeShopDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        CoffeeShopDatabase.class,
                        "coffeeshop_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
