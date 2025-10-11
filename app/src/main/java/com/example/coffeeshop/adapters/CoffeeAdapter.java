package com.example.coffeeshop.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.ItemCoffeeBinding;
import com.example.coffeeshop.model.CoffeeItem;
import com.example.coffeeshop.utils.CartManager;
import com.example.coffeeshop.utils.ToastUtils;
import java.util.List;
import java.util.Locale;

/**
 * Адаптер для отображения списка кофе в RecyclerView
 */
public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private List<CoffeeItem> coffeeItems;

    public CoffeeAdapter(List<CoffeeItem> coffeeItems) {
        this.coffeeItems = coffeeItems;
    }

    public void updateCoffeeItems(List<CoffeeItem> newCoffeeItems) {
        this.coffeeItems = newCoffeeItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCoffeeBinding binding = ItemCoffeeBinding.inflate(
            LayoutInflater.from(parent.getContext()), 
            parent, 
            false
        );
        return new CoffeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        CoffeeItem coffeeItem = coffeeItems.get(position);
        holder.bind(coffeeItem);
        
        // Анимация появления элемента
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slide_in_right);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return coffeeItems != null ? coffeeItems.size() : 0;
    }

    class CoffeeViewHolder extends RecyclerView.ViewHolder {
        private ItemCoffeeBinding binding;

        public CoffeeViewHolder(@NonNull ItemCoffeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CoffeeItem coffeeItem) {
            binding.coffeeName.setText(coffeeItem.getName());
            binding.coffeePrice.setText(String.format(Locale.getDefault(), "%.0f ₽", coffeeItem.getPrice()));
            binding.coffeeCategory.setText(getCategoryText(coffeeItem.getCategory()));

            // Обработчик клика по карточке - переход к детальному просмотру
            binding.getRoot().setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("coffee_item", coffeeItem);
                Navigation.findNavController(v).navigate(R.id.action_coffee_menu_to_coffee_detail, bundle);
            });

            // Обработчик клика по цене - добавление в корзину
            binding.coffeePrice.setOnClickListener(v -> {
                Context context = v.getContext();
                CartManager.getInstance().addItem(coffeeItem);
                ToastUtils.showAddedToCart(context, coffeeItem.getName());
            });
        }

        private String getCategoryText(String category) {
            switch (category) {
                case "hot":
                    return "Горячий";
                case "dessert":
                    return "Десерт";
                default:
                    return "Другое";
            }
        }
        
    }
}
