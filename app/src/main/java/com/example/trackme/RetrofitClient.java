package com.example.trackme;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static RetrofitClient retrofit;
    ApiInterface apiInterface;
    private static String BASE_URL = "https://expensemanager20230325125916.azurewebsites.net/";

    RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    public static RetrofitClient getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new RetrofitClient();
        }
        return retrofit;
    }


}
