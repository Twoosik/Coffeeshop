package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.FragmentCoffeeMenuBinding;

/**
 * Фрагмент меню кофе с фильтрами и списком
 */
public class CoffeeMenuFragment extends Fragment {

    private FragmentCoffeeMenuBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCoffeeMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Настройка RecyclerView (будет реализовано в следующем этапе)
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        // Настройка RecyclerView будет реализована в следующем этапе
        // Пока что показываем пустое состояние
        showEmptyState();
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
