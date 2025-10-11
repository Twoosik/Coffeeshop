package com.example.coffeeshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.coffeeshop.CoffeeShopApplication;
import com.example.coffeeshop.databinding.ActivityLoginBinding;
import com.example.coffeeshop.model.User;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            String username = binding.etUsername.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (validateInput(username, password)) {
                performLogin(username, password);
            }
        });
    }

    private boolean validateInput(String username, String password) {
        if (username.isEmpty()) {
            binding.usernameLayout.setError("Введите имя пользователя");
            return false;
        }

        if (password.isEmpty()) {
            binding.passwordLayout.setError("Введите пароль");
            return false;
        }

        binding.usernameLayout.setError(null);
        binding.passwordLayout.setError(null);
        binding.tvError.setVisibility(View.GONE);

        return true;
    }

    private void performLogin(String username, String password) {
        CoffeeShopApplication app = (CoffeeShopApplication) getApplication();
        
        new Thread(() -> {
            User user = app.getRepository().login(username, password);
            
            runOnUiThread(() -> {
                if (user != null) {
                    Toast.makeText(this, "Добро пожаловать, " + user.getUsername() + "!", Toast.LENGTH_SHORT).show();
                    
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    showError("Неверный логин или пароль");
                }
            });
        }).start();
    }

    private void showError(String message) {
        binding.tvError.setText(message);
        binding.tvError.setVisibility(View.VISIBLE);
    }
}
