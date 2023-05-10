package com.example.libraryapp.api;

import android.content.ClipData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInstance {

    @POST("/api/tasks")
    Call<Void> createItem(@Body);

    @PUT("data{id}")
    Call<Void> updateItem(@Path("id") long id, @Body);

    @DELETE("data{id}")
    Call<Void> deleteItem(@Path("id") long id);

    @GET("data{id}")
    Call<List<ClipData.Item>> getAllItems();
}
