package com.example.trackme.ui.transactiontype;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trackme.MainApplication;
import com.example.trackme.data.repository.TransactionRepository;
import com.example.trackme.ui.categories.CategoriesViewModel;

public class TransactionTypeViewModeLFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TransactionTypeViewModel.class)) {
            return (T) new TransactionTypeViewModel(TransactionRepository.getInstance(MainApplication.TrackMEApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
