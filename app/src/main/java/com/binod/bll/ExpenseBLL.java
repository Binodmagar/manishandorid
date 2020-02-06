package com.binod.bll;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.binod.api.ExpenseAPI;
import com.binod.model.Expense;
import com.binod.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ExpenseBLL {
    private String name;
    private Integer amount;
    private String category;
    private String account;
    private String days;
    private String months;
    private String years;
    private String description;
    private boolean issuccess;

    public ExpenseBLL(String name, Integer amount, String category, String account, String days, String months, String years, String description) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.days = days;
        this.months = months;
        this.years = years;
        this.description = description;
    }

    public boolean addExpense(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");

        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);
        //Expense expense = new Expense();
        Call<Expense> expenseCall = expenseAPI.addProduct(token, name, amount, category, account, days, months, years, description);

        try{
            Response<Expense> response = expenseCall.execute();
            if(!response.isSuccessful()){
                issuccess = false;
            }else {
                issuccess = true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return issuccess;
    }



}
