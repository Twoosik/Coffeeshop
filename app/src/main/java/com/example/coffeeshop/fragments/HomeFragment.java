package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.FragmentHomeBinding;

/**
 * Главный фрагмент с приветствием и быстрыми действиями
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Настройка обработчиков кликов
        setupClickListeners();
        
        // Анимация появления фрагмента
        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        view.startAnimation(fadeIn);
    }

    private void setupClickListeners() {
        // Переход к меню кофе
        binding.btnCoffeeMenu.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_home_to_coffee_menu);
        });

        // Переход к корзине
        binding.btnCart.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_home_to_cart);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
