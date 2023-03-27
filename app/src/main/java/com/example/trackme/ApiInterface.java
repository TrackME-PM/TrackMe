package com.example.trackme;

import com.example.trackme.data.model.Category;
import com.example.trackme.data.model.Transaction;
import com.example.trackme.data.model.TransactionType;
import com.example.trackme.data.model.User;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

//    @FormUrlEncoded
    @POST("api/Account/login")
    Call<User> getLoginData(@Body User user);

    @POST("api/transactions")
    Call<Transaction> addTransaction(@Body Transaction transaction);

    @GET("api/transactions/generatepdf")
    Call<InputStream> getPdf(@Body String month, @Body String year);

}
