package com.binod.bll;

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
    private String date;
    private String description;
    private boolean issuccess;

    public IncomeBLL(String name, Integer amount, String category, String account, String date, String description) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.date = date;
        this.description = description;
    }

    public boolean addIncome(){
        IncomeAPI incomeAPI = Url.getInstance().create(IncomeAPI.class);
        Income income = new Income(name, amount, category, account, date, description);
        Call<Income> incomeCall =  incomeAPI.addIncome(income);

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
