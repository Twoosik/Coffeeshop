package com.example.coffeeshop.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.coffeeshop.R;

public class CartAnimationUtils {
    
    public static void animateAddToCart(View rootView, String itemName, Runnable callback) {
        try {
            Toast toast = new Toast(rootView.getContext());
            
            LayoutInflater inflater = LayoutInflater.from(rootView.getContext());
            View layout = inflater.inflate(R.layout.custom_toast, null);
            
            TextView text = layout.findViewById(R.id.toast_text);
            text.setText("✓ " + itemName + " добавлен в корзину");
            
            // Анимация появления
            layout.startAnimation(
                android.view.animation.AnimationUtils.loadAnimation(
                    rootView.getContext(), R.anim.toast_slide_up)
            );
            
            toast.setView(layout);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
            toast.show();
            
        } catch (Exception e) {
            Toast.makeText(rootView.getContext(), 
                "✓ " + itemName + " добавлен в корзину", 
                Toast.LENGTH_SHORT).show();
        }
        
        if (callback != null) {
            callback.run();
        }
    }
    
    public static void animateAddToCart(View view, Runnable callback) {
        if (callback != null) {
            callback.run();
        }
    }
}