package com.example.coffeeshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.coffeeshop.R;
import com.example.coffeeshop.databinding.FragmentInstructionsBinding;

/**
 * Фрагмент "Инструкция пользователю"
 */
public class InstructionsFragment extends Fragment {

    private FragmentInstructionsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInstructionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Фрагмент готов к использованию
        // Вся информация уже настроена в макете через строковые ресурсы
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
