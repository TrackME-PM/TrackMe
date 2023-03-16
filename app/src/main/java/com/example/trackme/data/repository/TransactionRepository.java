package com.example.trackme.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.trackme.TrackMEApiManager;
import com.example.trackme.data.model.Category;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.data.model.TransactionType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {

    private static volatile TransactionRepository instance;

    private final TrackMEApiManager trackMEApiManager;

    private final MutableLiveData<List<Category>> categories = new MutableLiveData<>();
    private final MutableLiveData<Category> transactionsByCategory = new MutableLiveData<>();
    private final MutableLiveData<List<TransactionType>> transactionType = new MutableLiveData<>();
    private final MutableLiveData<TransactionType> transactionsByTransactionType = new MutableLiveData<>();
    private final MutableLiveData<List<Transaction>> transactions = new MutableLiveData<>();
    private final MutableLiveData<Transaction> transactionsByTransaction = new MutableLiveData<>();

    private TransactionRepository(TrackMEApiManager trackMEApiManager){
        this.trackMEApiManager = trackMEApiManager;
    }

    public static TransactionRepository getInstance(TrackMEApiManager trackMEApiManager){
        if(instance == null){
            instance = new TransactionRepository(trackMEApiManager);
        }
        return instance;
    }

    public MutableLiveData<List<Category>> getCategories(){
        trackMEApiManager.getCategories(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    List<Category> body = response.body();
                    categories.setValue(body);
                }else{
                    categories.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                categories.postValue(null);
            }
        });
        return categories;
    }

    public MutableLiveData<List<TransactionType>> getTransactionType(){
        trackMEApiManager.getTransactionType(new Callback<List<TransactionType>>() {
            @Override
            public void onResponse(Call<List<TransactionType>> call, Response<List<TransactionType>> response) {
                if(response.isSuccessful()){
                    List<TransactionType> body = response.body();
                    transactionType.setValue(body);
                }else{
                    transactionType.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<TransactionType>> call, Throwable t) {
                transactionType.postValue(null);
            }
        });
        return transactionType;
    }

    public MutableLiveData<List<Transaction>> getTransactions(){
        trackMEApiManager.getTransactions(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if(response.isSuccessful()){
                    List<Transaction> body = response.body();
                    transactions.setValue(body);
                }else{
                    transactions.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                transactions.postValue(null);
            }
        });
        return transactions;
    }

    public MutableLiveData<Category> getTransactionsByCategory(int id){
        trackMEApiManager.getTransactionsByCategory(id, new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    Category body = response.body();
                    transactionsByCategory.setValue(body);
                }else{
                    transactionsByCategory.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                transactionsByCategory.postValue(null);
            }
        });
        return transactionsByCategory;
    }

    public MutableLiveData<TransactionType> getTransactionsByTransactionType(int id){
        trackMEApiManager.getTransactionsByTransactionType(id, new Callback<TransactionType>() {
            @Override
            public void onResponse(Call<TransactionType> call, Response<TransactionType> response) {
                if(response.isSuccessful()){
                    TransactionType body = response.body();
                    transactionsByTransactionType.setValue(body);
                }else{
                    transactionsByTransactionType.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<TransactionType> call, Throwable t) {
                transactionsByTransactionType.postValue(null);
            }
        });
        return transactionsByTransactionType;
    }

    public MutableLiveData<Transaction> getTransactionsByTransaction(int id){
        trackMEApiManager.getTransactionsByTransaction(id, new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if(response.isSuccessful()){
                    Transaction body = response.body();
                    transactionsByTransaction.setValue(body);
                }else{
                    transactionsByTransaction.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                transactionsByTransaction.postValue(null);
            }
        });
        return transactionsByTransaction;
    }

}
