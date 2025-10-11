package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.coffeeshop.databinding.FragmentCoffeeDetailBinding;
import com.example.coffeeshop.model.CoffeeItem;
import com.example.coffeeshop.utils.CartManager;
import com.example.coffeeshop.utils.ToastUtils;

public class CoffeeDetailFragment extends Fragment {

    private FragmentCoffeeDetailBinding binding;
    private CoffeeItem coffeeItem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCoffeeDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            coffeeItem = (CoffeeItem) getArguments().getSerializable("coffee_item");
        }

        if (coffeeItem != null) {
            displayCoffeeDetails();
            setupAddToCartButton();
        }
    }

    private void displayCoffeeDetails() {
        binding.coffeeName.setText(coffeeItem.getName());

        binding.coffeeCategory.setText(getCategoryText(coffeeItem.getCategory()));

        binding.coffeePrice.setText(String.format("%.0f ₽", coffeeItem.getPrice()));

        binding.coffeeDescription.setText(coffeeItem.getDescription());

        setIngredients();
        
        loadCoffeeImage(coffeeItem.getImagePath());
    }

    private void setIngredients() {
        String ingredients;
        String coffeeName = coffeeItem.getName();

        switch (coffeeName) {
            case "Эспрессо":
                ingredients = "• Кофейные зерна\n• Вода";
                break;
            case "Американо":
                ingredients = "• Эспрессо\n• Горячая вода";
                break;
            case "Капучино":
                ingredients = "• Эспрессо\n• Молоко\n• Молочная пена";
                break;
            case "Латте":
                ingredients = "• Эспрессо\n• Молоко\n• Молочная пена";
                break;
            case "Макиато":
                ingredients = "• Эспрессо\n• Молоко";
                break;
            case "Флэт Уайт":
                ingredients = "• Двойной эспрессо\n• Молоко";
                break;
            case "Тирамису":
                ingredients = "• Маскарпоне\n• Кофе\n• Песочное печенье\n• Какао";
                break;
            case "Чизкейк":
                ingredients = "• Творожный сыр\n• Сметана\n• Яйца\n• Сахар\n• Печенье";
                break;
            case "Брауни":
                ingredients = "• Темный шоколад\n• Масло\n• Яйца\n• Сахар\n• Мука";
                break;
            case "Круассан":
                ingredients = "• Мука\n• Масло\n• Дрожжи\n• Молоко\n• Яйца";
                break;
            case "Маффин":
                ingredients = "• Мука\n• Яйца\n• Молоко\n• Сахар\n• Растительное масло";
                break;
            default:
                if (coffeeName.toLowerCase().contains("фрап")) {
                    ingredients = "• Эспрессо\n• Молоко\n• Лед\n• Сахарный сироп\n• Взбитые сливки\n• Ванильный сироп";
                } else {
                    ingredients = "• Ингредиенты не указаны";
                }
                break;
        }

        binding.coffeeIngredients.setText(ingredients);
    }

    private void setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener(v -> {
            CartManager.getInstance().addItem(coffeeItem);
            ToastUtils.showAddedToCart(getContext(), coffeeItem.getName());
        });
    }

    private String getCategoryText(String category) {
        switch (category) {
            case "hot":
                return "Горячий напиток";
            case "dessert":
                return "Десерт";
            default:
                return "Другое";
        }
    }
    
    private void loadCoffeeImage(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions()
                    .transform(new RoundedCorners(16)))
                .into(binding.coffeeImage);
        } else {
            binding.coffeeImage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
