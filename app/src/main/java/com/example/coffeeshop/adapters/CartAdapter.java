package com.example.coffeeshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.ItemCartBinding;
import com.example.coffeeshop.model.CartItem;
import java.util.List;
import java.util.Locale;

/**
 * Адаптер для отображения элементов корзины
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private OnCartItemChangeListener listener;

    public interface OnCartItemChangeListener {
        void onQuantityChanged();
        void onItemRemoved(CartItem cartItem);
    }

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setOnCartItemChangeListener(OnCartItemChangeListener listener) {
        this.listener = listener;
    }

    public void updateCartItems(List<CartItem> newCartItems) {
        this.cartItems = newCartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.getContext()), 
            parent, 
            false
        );
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItems != null ? cartItems.size() : 0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding binding;

        public CartViewHolder(@NonNull ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CartItem cartItem) {
            binding.itemName.setText(cartItem.getCoffeeItem().getName());
            binding.itemPrice.setText(String.format(Locale.getDefault(), "%.0f ₽", cartItem.getCoffeeItem().getPrice()));
            binding.quantity.setText(String.valueOf(cartItem.getQuantity()));

            // Увеличить количество
            binding.btnIncrease.setOnClickListener(v -> {
                cartItem.incrementQuantity();
                binding.quantity.setText(String.valueOf(cartItem.getQuantity()));
                if (listener != null) {
                    listener.onQuantityChanged();
                }
            });

            // Уменьшить количество
            binding.btnDecrease.setOnClickListener(v -> {
                if (cartItem.getQuantity() > 1) {
                    cartItem.decrementQuantity();
                    binding.quantity.setText(String.valueOf(cartItem.getQuantity()));
                    if (listener != null) {
                        listener.onQuantityChanged();
                    }
                } else {
                    // Удалить товар из корзины
                    if (listener != null) {
                        listener.onItemRemoved(cartItem);
                    }
                }
            });

            // Удалить товар
            binding.btnRemove.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemRemoved(cartItem);
                }
            });
        }
    }
}
