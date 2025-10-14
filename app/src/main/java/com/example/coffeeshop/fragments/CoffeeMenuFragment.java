package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.coffeeshop.CoffeeShopApplication;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapters.CoffeeAdapter;
import com.example.coffeeshop.databinding.FragmentCoffeeMenuBinding;
import com.example.coffeeshop.model.CoffeeItem;
import java.util.List;

/**
 * Фрагмент для отображения меню кофе
 */
public class CoffeeMenuFragment extends Fragment {

    private FragmentCoffeeMenuBinding binding;
    private CoffeeAdapter coffeeAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCoffeeMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        setupClickListeners();
        loadCoffeeItems();
    }

    private void setupClickListeners() {
        binding.fabCart.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_coffee_menu_to_cart);
        });
    }

    private void setupRecyclerView() {
        binding.recyclerCoffee.setLayoutManager(new LinearLayoutManager(getContext()));
        coffeeAdapter = new CoffeeAdapter(null);
        binding.recyclerCoffee.setAdapter(coffeeAdapter);
    }

    private void loadCoffeeItems() {
        showLoadingState();
        
        CoffeeShopApplication app = (CoffeeShopApplication) getActivity().getApplication();
        
        new Thread(() -> {
            List<CoffeeItem> coffeeItems = app.getRepository().getAvailableCoffeeItems();
            
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    if (coffeeItems != null && !coffeeItems.isEmpty()) {
                        coffeeAdapter.updateCoffeeItems(coffeeItems);
                        showCoffeeList();
                    } else {
                        showEmptyState();
                    }
                });
            }
        }).start();
    }

    private void showLoadingState() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.recyclerCoffee.setVisibility(View.GONE);
        binding.emptyState.setVisibility(View.GONE);
    }

    private void showEmptyState() {
        binding.progressBar.setVisibility(View.GONE);
        binding.recyclerCoffee.setVisibility(View.GONE);
        binding.emptyState.setVisibility(View.VISIBLE);
    }

    private void showCoffeeList() {
        binding.progressBar.setVisibility(View.GONE);
        binding.recyclerCoffee.setVisibility(View.VISIBLE);
        binding.emptyState.setVisibility(View.GONE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
