package com.example.trackme.ui.categories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trackme.MainApplication;
import com.example.trackme.data.repository.TransactionRepository;

public class CategoriesViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategoriesViewModel.class)) {
            return (T) new CategoriesViewModel(TransactionRepository.getInstance(MainApplication.TrackMEApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
