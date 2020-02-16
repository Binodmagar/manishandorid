package com.binod.api;

import android.content.SharedPreferences;

import com.binod.model.Expense;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExpenseAPI {
    @FormUrlEncoded
    @POST("/expenses")
    Call<Expense> addProduct(@Header("Authorization") String token, @Field("name") String name, @Field("amount") Integer amount, @Field("category") String category, @Field("account") String account,
                             @Field("days") String days, @Field("months") String months, @Field("years") String years, @Field("description") String description);
   // Call<Expense> addProduct(@Header("Authorization") String token, @Body Expense expense);\


    @GET("/expenses")
    Call<List<Expense>> getByUser(@Header("Authorization") String token);

    @GET("/expenses/{days}")
    Call<List<Expense>> getByDays(@Header("Authorization") String token, @Path("days") String days);

    @FormUrlEncoded
    @PUT("/expenses/{id}")
    Call<Expense> updateProduct(@Header("Authorization") String token, @Path("id")String expenseId, @Field("name") String name, @Field("amount") Integer amount, @Field("category") String category, @Field("account") String account,
                                @Field("description") String description);
}
