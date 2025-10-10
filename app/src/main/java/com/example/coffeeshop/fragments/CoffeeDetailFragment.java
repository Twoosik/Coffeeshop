package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.FragmentCoffeeDetailBinding;
import com.example.coffeeshop.model.CoffeeItem;
import com.example.coffeeshop.utils.CartManager;
import com.example.coffeeshop.utils.ToastUtils;

/**
 * Фрагмент для детального просмотра информации о кофе
 */
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

        // Получаем данные о кофе из аргументов
        if (getArguments() != null) {
            coffeeItem = (CoffeeItem) getArguments().getSerializable("coffee_item");
        }

        if (coffeeItem != null) {
            displayCoffeeDetails();
            setupAddToCartButton();
        }
    }

    private void displayCoffeeDetails() {
        // Название
        binding.coffeeName.setText(coffeeItem.getName());

        // Категория
        binding.coffeeCategory.setText(getCategoryText(coffeeItem.getCategory()));

        // Цена
        binding.coffeePrice.setText(String.format("%.0f ₽", coffeeItem.getPrice()));

        // Описание
        binding.coffeeDescription.setText(coffeeItem.getDescription());

        // Состав
        setIngredients();
    }

    private void setIngredients() {
        String ingredients;

        switch (coffeeItem.getName()) {
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
            case "Мокко":
                ingredients = "• Эспрессо\n• Молоко\n• Шоколадный сироп\n• Взбитые сливки";
                break;
            case "Флэт Уайт":
                ingredients = "• Двойной эспрессо\n• Молоко";
                break;
            case "Кортадо":
                ingredients = "• Эспрессо\n• Молоко (1:1)";
                break;
            case "Фраппучино":
                ingredients = "• Эспрессо\n• Молоко\n• Лед\n• Сахарный сироп\n• Взбитые сливки\n• Ванильный сироп";
                break;
            case "Айс-кофе":
                ingredients = "• Кофе\n• Лед\n• Сахар (по желанию)";
                break;
            case "Колд-брю":
                ingredients = "• Кофейные зерна\n• Холодная вода\n• Лед";
                break;
            case "Айс-латте":
                ingredients = "• Эспрессо\n• Молоко\n• Лед";
                break;
            case "Аффогато":
                ingredients = "• Ванильное мороженое\n• Горячий эспрессо";
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
                ingredients = "• Ингредиенты не указаны";
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
            case "cold":
                return "Холодный напиток";
            case "dessert":
                return "Десерт";
            default:
                return "Другое";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
