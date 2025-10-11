package com.example.coffeeshop.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.coffeeshop.CoffeeShopApplication;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.ActivityMainBinding;
import com.example.coffeeshop.utils.CartManager;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        CoffeeShopApplication app = (CoffeeShopApplication) getApplication();
        app.getRepository().clearAndReinitializeData();
        
        CartManager.getInstance().setContext(this);
        
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            
            int currentDestination = navController.getCurrentDestination().getId();
            if (currentDestination == R.id.CoffeeMenuFragment) {
                navController.navigate(R.id.action_coffee_menu_to_about);
            } else if (currentDestination == R.id.CartFragment) {
                navController.navigate(R.id.action_cart_to_about);
            } else if (currentDestination == R.id.CoffeeDetailFragment) {
                navController.navigate(R.id.action_coffee_detail_to_about);
            } else if (currentDestination == R.id.InstructionFragment) {
                navController.navigate(R.id.action_instruction_to_about);
            } else if (currentDestination == R.id.AuthorFragment) {
                navController.navigate(R.id.action_author_to_about);
            } else {
                navController.navigate(R.id.action_home_to_about);
            }
            return true;
        }
        
        if (id == R.id.action_instruction) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            
            int currentDestination = navController.getCurrentDestination().getId();
            if (currentDestination == R.id.CoffeeMenuFragment) {
                navController.navigate(R.id.action_coffee_menu_to_instruction);
            } else if (currentDestination == R.id.CartFragment) {
                navController.navigate(R.id.action_cart_to_instruction);
            } else if (currentDestination == R.id.CoffeeDetailFragment) {
                navController.navigate(R.id.action_coffee_detail_to_instruction);
            } else if (currentDestination == R.id.AboutFragment) {
                navController.navigate(R.id.action_about_to_instruction);
            } else if (currentDestination == R.id.AuthorFragment) {
                navController.navigate(R.id.action_author_to_instruction);
            } else {
                navController.navigate(R.id.action_home_to_instruction);
            }
            return true;
        }
        
        if (id == R.id.action_author) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            
            int currentDestination = navController.getCurrentDestination().getId();
            if (currentDestination == R.id.CoffeeMenuFragment) {
                navController.navigate(R.id.action_coffee_menu_to_author);
            } else if (currentDestination == R.id.CartFragment) {
                navController.navigate(R.id.action_cart_to_author);
            } else if (currentDestination == R.id.CoffeeDetailFragment) {
                navController.navigate(R.id.action_coffee_detail_to_author);
            } else if (currentDestination == R.id.AboutFragment) {
                navController.navigate(R.id.action_about_to_author);
            } else if (currentDestination == R.id.InstructionFragment) {
                navController.navigate(R.id.action_instruction_to_author);
            } else {
                navController.navigate(R.id.action_home_to_author);
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