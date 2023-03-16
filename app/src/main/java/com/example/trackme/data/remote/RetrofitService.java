package com.example.trackme.data.remote;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;






public class RetrofitService {


    public static iTrackMEAPI Create(){


        Retrofit retrofit = new Retrofit.Builder()


                .baseUrl("http://10.0.2.2:7151/")


                .addConverterFactory(GsonConverterFactory.create())


                .build();


        return retrofit.create(iTrackMEAPI.class);


    }

}
