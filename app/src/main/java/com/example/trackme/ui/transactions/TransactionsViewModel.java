package com.example.trackme.ui.transactions;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trackme.data.model.Transaction;
import com.example.trackme.data.repository.TransactionRepository;

import java.util.List;

public class TransactionsViewModel extends ViewModel {
    private final TransactionRepository transactionRepository;
    public TransactionsViewModel(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public MutableLiveData<List<Transaction>> getTransactions(){
        return transactionRepository.getTransactions();
    }

}
