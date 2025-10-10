package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
        loadCoffeeItems();
    }

    private void setupRecyclerView() {
        // Настройка RecyclerView
        binding.recyclerCoffee.setLayoutManager(new LinearLayoutManager(getContext()));
        coffeeAdapter = new CoffeeAdapter(null);
        binding.recyclerCoffee.setAdapter(coffeeAdapter);

        // Обработчик клика по элементу (без уведомлений)
        coffeeAdapter.setOnCoffeeItemClickListener(coffeeItem -> {
            // Здесь можно добавить логику для детального просмотра или заказа
        });
    }

    private void loadCoffeeItems() {
        showLoadingState();
        
        // Получаем репозиторий из Application
        CoffeeShopApplication app = (CoffeeShopApplication) getActivity().getApplication();
        
        // Загружаем данные в фоновом потоке
        new Thread(() -> {
            List<CoffeeItem> coffeeItems = app.getRepository().getAvailableCoffeeItems();
            
            // Обновляем UI в главном потоке
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
