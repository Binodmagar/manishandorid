package com.binod.api;

import com.binod.model.Expense;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ExpenseAPI {
    @POST("/expenses")
    Call<Expense> addProduct(@Body Expense expense);

    @GET("products/")
    Call<List<Expense>> getProduct();


}
