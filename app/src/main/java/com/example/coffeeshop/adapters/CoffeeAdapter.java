package com.example.coffeeshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.ItemCoffeeBinding;
import com.example.coffeeshop.model.CoffeeItem;
import java.util.List;
import java.util.Locale;

/**
 * Адаптер для отображения списка кофе в RecyclerView
 */
public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private List<CoffeeItem> coffeeItems;
    private OnCoffeeItemClickListener listener;

    public interface OnCoffeeItemClickListener {
        void onCoffeeItemClick(CoffeeItem coffeeItem);
    }

    public CoffeeAdapter(List<CoffeeItem> coffeeItems) {
        this.coffeeItems = coffeeItems;
    }

    public void setOnCoffeeItemClickListener(OnCoffeeItemClickListener listener) {
        this.listener = listener;
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
            binding.coffeeDescription.setText(coffeeItem.getDescription());
            binding.coffeePrice.setText(String.format(Locale.getDefault(), "%.0f ₽", coffeeItem.getPrice()));
            binding.coffeeCategory.setText(getCategoryText(coffeeItem.getCategory()));

            // Обработчик клика
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCoffeeItemClick(coffeeItem);
                }
            });
        }

        private String getCategoryText(String category) {
            switch (category) {
                case "hot":
                    return "Горячий";
                case "cold":
                    return "Холодный";
                case "dessert":
                    return "Десерт";
                default:
                    return "Другое";
            }
        }
    }
}
