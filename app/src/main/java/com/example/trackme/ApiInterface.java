package com.example.trackme;

import com.example.trackme.data.model.Category;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.data.model.TransactionType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/categories")
    Call<List<Category>> getCategories();

    @GET("api/categories/{id}")
    Call<Category> getTransactionsByCategory(@Path("id") int id);

    @GET("api/transactions")
    Call<List<Transaction>> getTransactions();

    @GET("api/transactions/{id}")
    Call<Transaction> getTransactionsByTransaction(@Path("id") int id);

    @GET("api/transactiontype")
    Call<List<TransactionType>> getTransactionType();

    @GET("api/transactions/{id}")
    Call<TransactionType> getTransactionsByTransactionType(@Path("id") int id);
}
