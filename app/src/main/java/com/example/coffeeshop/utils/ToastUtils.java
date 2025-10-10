package com.example.coffeeshop.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.coffeeshop.R;

/**
 * Утилиты для создания кастомных уведомлений
 */
public class ToastUtils {

    /**
     * Показать уведомление о добавлении товара в корзину
     */
    public static void showAddedToCart(Context context, String itemName) {
        // Создаем кастомный Toast
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_added_to_cart, null);
        
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(itemName + " добавлен в корзину!");
        
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    /**
     * Показать уведомление об успешном оформлении заказа
     */
    public static void showOrderSuccess(Context context) {
        // Создаем кастомный Toast
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_added_to_cart, null);
        
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText("Заказ успешно оформлен!");
        
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
