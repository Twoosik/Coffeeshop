package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.coffeeshop.databinding.FragmentCartBinding;

/**
 * Фрагмент для отображения корзины покупок
 */
public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Здесь будет логика корзины
        setupCart();
    }

    private void setupCart() {
        // Пока что просто показываем пустую корзину
        showEmptyCart();
    }

    private void showEmptyCart() {
        binding.emptyCart.setVisibility(View.VISIBLE);
        binding.cartContent.setVisibility(View.GONE);
    }

    private void showCartContent() {
        binding.emptyCart.setVisibility(View.GONE);
        binding.cartContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
