package com.binod.api;

import com.binod.model.Expense;
import com.binod.model.Income;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IncomeAPI {

    @FormUrlEncoded
    @POST("/incomes")
    Call<Income> addIncome(@Header("Authorization") String token, @Field("name") String name, @Field("amount") Integer amount, @Field("category") String category, @Field("account") String account,
                             @Field("days") String days, @Field("months") String months, @Field("years") String years, @Field("description") String description);
    // Call<Expense> addProduct(@Header("Authorization") String token, @Body Expense expense);\


    @GET("/incomes")
    Call<List<Income>> getByUser(@Header("Authorization") String token);
}
