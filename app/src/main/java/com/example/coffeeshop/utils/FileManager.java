package com.example.coffeeshop.utils;

import android.content.Context;
import android.util.Log;
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
    private static final String TAG = "FileManager";
    
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
            
            Log.d(TAG, "Coffee items saved to file: " + coffeeItems.size() + " items");
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error saving coffee items to file", e);
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
            
            Log.d(TAG, "Coffee items loaded from file: " + coffeeItems.size() + " items");
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error loading coffee items from file", e);
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
            
            Log.d(TAG, "Users saved to file: " + users.size() + " users");
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error saving users to file", e);
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
            
            Log.d(TAG, "Users loaded from file: " + users.size() + " users");
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error loading users from file", e);
        }
        return users;
    }
    
    public boolean fileExists(String filename) {
        File file = new File(context.getFilesDir(), filename);
        return file.exists();
    }
    
    public void deleteFile(String filename) {
        File file = new File(context.getFilesDir(), filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
