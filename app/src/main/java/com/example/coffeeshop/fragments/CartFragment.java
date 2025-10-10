package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapters.CartAdapter;
import com.example.coffeeshop.databinding.FragmentCartBinding;
import com.example.coffeeshop.model.CartItem;
import com.example.coffeeshop.utils.CartManager;
import com.example.coffeeshop.utils.ToastUtils;
import java.util.Locale;

/**
 * Фрагмент корзины покупок
 */
public class CartFragment extends Fragment implements CartAdapter.OnCartItemChangeListener {

    private FragmentCartBinding binding;
    private CartAdapter cartAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        updateCartDisplay();
        setupCheckoutButton();
    }

    private void setupRecyclerView() {
        binding.recyclerCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems());
        cartAdapter.setOnCartItemChangeListener(this);
        binding.recyclerCart.setAdapter(cartAdapter);
    }

    private void updateCartDisplay() {
        CartManager cartManager = CartManager.getInstance();
        
        if (cartManager.isEmpty()) {
            showEmptyState();
        } else {
            showCartContent();
            updateTotalPrice();
        }
    }

    private void showEmptyState() {
        binding.emptyCart.setVisibility(View.VISIBLE);
        binding.cartContent.setVisibility(View.GONE);
    }

    private void showCartContent() {
        binding.emptyCart.setVisibility(View.GONE);
        binding.cartContent.setVisibility(View.VISIBLE);
    }

    private void updateTotalPrice() {
        double total = CartManager.getInstance().getTotalPrice();
        binding.totalPrice.setText(String.format(Locale.getDefault(), "%.0f ₽", total));
    }

    private void setupCheckoutButton() {
        binding.btnCheckout.setOnClickListener(v -> {
            // Проверяем, что корзина не пустая
            if (!CartManager.getInstance().isEmpty()) {
                // Показываем уведомление об успешном заказе
                ToastUtils.showOrderSuccess(getContext());
                
                // Очищаем корзину
                CartManager.getInstance().clearCart();
                
                // Обновляем отображение корзины
                cartAdapter.updateCartItems(CartManager.getInstance().getCartItems());
                updateCartDisplay();
            }
        });
    }

    @Override
    public void onQuantityChanged() {
        updateTotalPrice();
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRemoved(CartItem cartItem) {
        CartManager.getInstance().removeItem(cartItem);
        cartAdapter.updateCartItems(CartManager.getInstance().getCartItems());
        updateCartDisplay();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Обновляем корзину при возвращении на экран
        cartAdapter.updateCartItems(CartManager.getInstance().getCartItems());
        updateCartDisplay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
