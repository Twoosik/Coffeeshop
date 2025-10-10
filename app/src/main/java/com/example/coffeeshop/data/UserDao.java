package com.example.coffeeshop.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.coffeeshop.model.User;
import java.util.List;

/**
 * DAO для работы с пользователями
 */
@Dao
public interface UserDao {
    
    @Query("SELECT * FROM users")
    List<User> getAllUsers();
    
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User login(String username, String password);
    
    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);
    
    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(int id);
    
    @Insert
    void insertUser(User user);
    
    @Update
    void updateUser(User user);
    
    @Delete
    void deleteUser(User user);
    
    @Query("DELETE FROM users WHERE id = :id")
    void deleteUserById(int id);
}
