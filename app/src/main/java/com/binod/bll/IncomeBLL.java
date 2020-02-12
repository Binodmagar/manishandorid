package com.binod.bll;

import android.content.Context;
import android.content.SharedPreferences;

import com.binod.api.IncomeAPI;
import com.binod.model.Income;
import com.binod.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class IncomeBLL {

    private String name;
    private Integer amount;
    private String category;
    private String account;
    private String days;
    private String months;
    private String years;
    private String description;
    private boolean issuccess;

    public IncomeBLL(String name, Integer amount, String category, String account, String days, String months, String years, String description) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.days = days;
        this.months = months;
        this.years = years;
        this.description = description;
    }

    public boolean addIncome(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");

        IncomeAPI incomeAPI = Url.getInstance().create(IncomeAPI.class);
        Call<Income> incomeCall = incomeAPI.addIncome(token, name, amount, category, account, days, months, years, description);

        try{
            Response<Income> response = incomeCall.execute();
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
