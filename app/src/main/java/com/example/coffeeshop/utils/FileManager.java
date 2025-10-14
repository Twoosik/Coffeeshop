package com.example.coffeeshop.utils;

import android.content.Context;
import com.example.coffeeshop.model.CoffeeItem;
import com.example.coffeeshop.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    
    private static final String COFFEE_FILE = "coffee_data.txt";
    private static final String USER_FILE = "user_data.txt";
    private static final String CART_FILE = "cart_data.txt";
    
    private Context context;
    
    public FileManager(Context context) {
        this.context = context;
    }
    
    public void saveCoffeeItems(List<CoffeeItem> coffeeItems) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (CoffeeItem item : coffeeItems) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("name", item.getName());
                jsonObject.put("description", item.getDescription());
                jsonObject.put("price", item.getPrice());
                jsonObject.put("imagePath", item.getImagePath());
                jsonObject.put("category", item.getCategory());
                jsonObject.put("isAvailable", item.isAvailable());
                jsonArray.put(jsonObject);
            }
            
            FileOutputStream fos = context.openFileOutput(COFFEE_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(jsonArray.toString());
            writer.close();
            fos.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    
    public List<CoffeeItem> loadCoffeeItems() {
        List<CoffeeItem> coffeeItems = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(COFFEE_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            fis.close();
            
            String jsonString = stringBuilder.toString();
            if (!jsonString.isEmpty()) {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    CoffeeItem item = new CoffeeItem();
                    item.setId(jsonObject.getInt("id"));
                    item.setName(jsonObject.getString("name"));
                    item.setDescription(jsonObject.getString("description"));
                    item.setPrice(jsonObject.getDouble("price"));
                    item.setImagePath(jsonObject.getString("imagePath"));
                    item.setCategory(jsonObject.getString("category"));
                    item.setAvailable(jsonObject.getBoolean("isAvailable"));
                    coffeeItems.add(item);
                }
            }
        } catch (IOException | JSONException e) {
        }
        return coffeeItems;
    }
    
    public void saveUsers(List<User> users) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (User user : users) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", user.getId());
                jsonObject.put("username", user.getUsername());
                jsonObject.put("password", user.getPassword());
                jsonObject.put("role", user.getRole());
                jsonObject.put("email", user.getEmail());
                jsonArray.put(jsonObject);
            }
            
            FileOutputStream fos = context.openFileOutput(USER_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(jsonArray.toString());
            writer.close();
            fos.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(USER_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            fis.close();
            
            String jsonString = stringBuilder.toString();
            if (!jsonString.isEmpty()) {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    User user = new User();
                    user.setId(jsonObject.getInt("id"));
                    user.setUsername(jsonObject.getString("username"));
                    user.setPassword(jsonObject.getString("password"));
                    user.setRole(jsonObject.getString("role"));
                    user.setEmail(jsonObject.getString("email"));
                    users.add(user);
                }
            }
        } catch (IOException | JSONException e) {
        }
        return users;
    }
    
    public boolean fileExists(String filename) {
        File file = new File(context.getFilesDir(), filename);
        return file.exists();
    }
    
    public void saveCartItems(List<CoffeeItem> cartItems) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (CoffeeItem item : cartItems) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", item.getId());
                jsonObject.put("name", item.getName());
                jsonObject.put("description", item.getDescription());
                jsonObject.put("price", item.getPrice());
                jsonObject.put("imagePath", item.getImagePath());
                jsonObject.put("category", item.getCategory());
                jsonObject.put("isAvailable", item.isAvailable());
                jsonArray.put(jsonObject);
            }
            
            FileOutputStream fos = context.openFileOutput(CART_FILE, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(jsonArray.toString());
            writer.close();
            fos.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    
    public List<CoffeeItem> loadCartItems() {
        List<CoffeeItem> cartItems = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(CART_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            fis.close();
            
            String jsonString = stringBuilder.toString();
            if (!jsonString.isEmpty()) {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    CoffeeItem item = new CoffeeItem();
                    item.setId(jsonObject.getInt("id"));
                    item.setName(jsonObject.getString("name"));
                    item.setDescription(jsonObject.getString("description"));
                    item.setPrice(jsonObject.getDouble("price"));
                    item.setImagePath(jsonObject.getString("imagePath"));
                    item.setCategory(jsonObject.getString("category"));
                    item.setAvailable(jsonObject.getBoolean("isAvailable"));
                    cartItems.add(item);
                }
            }
        } catch (IOException | JSONException e) {
            // Файл корзины не найден или пустой
        }
        return cartItems;
    }
    
    public void deleteFile(String filename) {
        File file = new File(context.getFilesDir(), filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
