package com.binod.bll;

import android.widget.Toast;

import com.binod.api.ExpenseAPI;
import com.binod.model.Expense;
import com.binod.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ExpenseBLL {

    private String name;
    private Integer amount;
    private String category;
    private String account;
    private String date;
    private String description;
    private boolean issuccess;

    public ExpenseBLL(String name, Integer amount, String category, String account, String date, String description) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.account = account;
        this.date = date;
        this.description = description;
    }

    public boolean addExpense(){
        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);

        Expense expense = new Expense(name, amount, category, account, date, description);
        Call<Expense> expenseCall = expenseAPI.addProduct(expense);

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
