package com.example.trackme.ui.categories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trackme.data.model.Category;
import com.example.trackme.data.repository.TransactionRepository;

import java.util.List;

public class CategoriesViewModel extends ViewModel {
    private final TransactionRepository categoryRepository;

    public CategoriesViewModel(TransactionRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public MutableLiveData<List<Category>> getCategories(){
        return categoryRepository.getCategories();
    }
}
