package com.binod.api;

import com.binod.model.Expense;
import com.binod.model.Income;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IncomeAPI {

    @POST("/incomes")
    Call<Income> addIncome(@Body Income income);

}
