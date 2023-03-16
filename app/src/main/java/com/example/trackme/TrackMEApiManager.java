package com.example.trackme;

import com.example.trackme.data.model.Category;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.data.model.TransactionType;
import com.example.trackme.data.remote.RetrofitService;
import com.example.trackme.data.remote.iTrackMEAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class TrackMEApiManager {
    private static iTrackMEAPI service;
    private static TrackMEApiManager apiManager;

    private TrackMEApiManager() {
        service = RetrofitService.Create();
    }

    public static TrackMEApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new TrackMEApiManager();

        }
        return apiManager;

    }

    public void getCategories(Callback<List<Category>> callback){
        Call<List<Category>> categoriesCall = service.getCategories();
        categoriesCall.enqueue(callback);
    }

    public void getTransactionsByCategory(int id, Callback<Category> callback){
        Call<Category> transactionsByCategoryCall = service.getTransactionsByCategory(id);
        transactionsByCategoryCall.enqueue(callback);
    }

    public void getTransactions(Callback<List<Transaction>> callback){
        Call<List<Transaction>> transactionsCall = service.getTransactions();
        transactionsCall.enqueue(callback);
    }

    public void getTransactionsByTransaction(int id, Callback<Transaction> callback){
        Call<Transaction> getTransactionsByTransactionCall = service.getTransactionsByTransaction(id);
        getTransactionsByTransactionCall.enqueue(callback);
    }

    public void getTransactionType(Callback<List<TransactionType>> callback){
        Call<List<TransactionType>> transactionTypeCall = service.getTransactionType();
        transactionTypeCall.enqueue(callback);
    }

    public void getTransactionsByTransactionType(int id, Callback<TransactionType> callback){
        Call<TransactionType> getTransactionsByTransactionTypeCall = service.getTransactionsByTransactionType(id);
        getTransactionsByTransactionTypeCall.enqueue(callback);
    }
}
