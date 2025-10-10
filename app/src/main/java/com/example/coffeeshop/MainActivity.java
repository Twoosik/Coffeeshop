package com.example.coffeeshop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.coffeeshop.databinding.ActivityMainBinding;

/**
 * Главная Activity приложения кофейни
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Настройка навигации
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            // Переход к информации о программе
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            
            // Определяем текущий фрагмент и выбираем правильное действие
            int currentDestination = navController.getCurrentDestination().getId();
            if (currentDestination == R.id.CoffeeMenuFragment) {
                navController.navigate(R.id.action_coffee_menu_to_about);
            } else if (currentDestination == R.id.CartFragment) {
                navController.navigate(R.id.action_cart_to_about);
            } else if (currentDestination == R.id.CoffeeDetailFragment) {
                navController.navigate(R.id.action_coffee_detail_to_about);
            } else {
                navController.navigate(R.id.action_home_to_about);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}