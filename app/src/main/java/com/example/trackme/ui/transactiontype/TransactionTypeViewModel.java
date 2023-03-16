package com.example.trackme.ui.transactiontype;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trackme.data.model.Category;
import com.example.trackme.data.model.TransactionType;
import com.example.trackme.data.repository.TransactionRepository;

import java.util.List;

public class TransactionTypeViewModel extends ViewModel {
    private final TransactionRepository transactionTypeRepository;

    public TransactionTypeViewModel(TransactionRepository transactionTypeRepository){
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public MutableLiveData<List<TransactionType>> getTransactionType(){
        return transactionTypeRepository.getTransactionType();
    }
}
