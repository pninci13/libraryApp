package com.example.libraryapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://25.22.177.97";

    private static Retrofit retrofit = null;


    public static OkHttpClient client(){

    }

    public static Gson gson (){return new GsonBuilder().create();}

    public static RetrofitInstance getItem() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RetrofitInstance.class);
    }
}
