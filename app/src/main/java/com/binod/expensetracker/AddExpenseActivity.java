package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.api.ExpenseAPI;
import com.binod.bll.ExpenseBLL;
import com.binod.model.Expense;
import com.binod.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExpenseActivity extends AppCompatActivity {

    TextView tvDateAE;
    Spinner spCategoryAE, spAccountAE;
    EditText etNameAE, etAmountAE, etNoteAE;
    Button btnSaveAE;

    public static final String Expense[] = {
            "Food",
            "Clothes",
            "General Expenses",
            "Medical",
    };

    public static final String Account[] = {
            "Cash",
            "Cheque",
            "Card"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        //binding
        tvDateAE = findViewById(R.id.tvDateAE);
        spCategoryAE = findViewById(R.id.spCategoryAE);
        spAccountAE = findViewById(R.id.spAccountAE);
        etNameAE = findViewById(R.id.etNameAE);
        etAmountAE = findViewById(R.id.etAmountAE);
        etNoteAE = findViewById(R.id.etNoteAE);
        btnSaveAE = findViewById(R.id.btnSaveAE);

        //for incomming intent data
        Intent incommingIntent = getIntent();
        String date = incommingIntent.getStringExtra("currentDate");
        tvDateAE.setText(date);

        //for setting up data in category
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Expense);
        spCategoryAE.setAdapter(arrayAdapter);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Account);
        spAccountAE.setAdapter(arrayAdapter1);


        btnSaveAE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    addExpense();
                }else{
                    Toast.makeText(AddExpenseActivity.this, "Please check input credentials again", Toast.LENGTH_SHORT).show();
                    etNameAE.requestFocus();
                    return;
                }
            }
        });
    }


    private boolean checkValidation(){
        boolean status = true;
        if(TextUtils.isEmpty(etNameAE.getText().toString())){
            etNameAE.setError("Please enter expense name!");
            etNameAE.requestFocus();
            status = false;
        }
        if(TextUtils.isEmpty(etAmountAE.getText().toString())){
            etAmountAE.setError("Please enter amount!");
            etAmountAE.requestFocus();
            status = false;
        }
        if(TextUtils.isEmpty(etNoteAE.getText().toString())){
            etNoteAE.setError("Please enter note");
            etNoteAE.requestFocus();
            status = false;
        }
        return  status;
    }

    private void addExpense(){
        String name = etNameAE.getText().toString();
        int amount = Integer.parseInt(etAmountAE.getText().toString());
        String category = spCategoryAE.getSelectedItem().toString();
        String account = spAccountAE.getSelectedItem().toString();
        String date = tvDateAE.getText().toString();
        String description = etNoteAE.getText().toString();

        ExpenseBLL expenseBLL = new ExpenseBLL(name, amount, category, account, date, description);

        if(expenseBLL.addExpense()){
            Toast.makeText(AddExpenseActivity.this, "Expense Added successfully", Toast.LENGTH_SHORT).show();
            etNameAE.setText("");
            etAmountAE.setText("");
            etNoteAE.setText("");
        }else{
            Toast.makeText(AddExpenseActivity.this, "Cannot add expense", Toast.LENGTH_SHORT).show();

        }

//        Expense expense = new Expense(name, amount, category, account, date, description);
//        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);
//        Call<Expense> expenseCall = expenseAPI.addProduct(expense);
//
//        expenseCall.enqueue(new Callback<com.binod.model.Expense>() {
//            @Override
//            public void onResponse(Call<com.binod.model.Expense> call, Response<com.binod.model.Expense> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(AddExpenseActivity.this, "Cannot add expense", Toast.LENGTH_SHORT).show();
//                    return;
//                }else {
//                    Toast.makeText(AddExpenseActivity.this, "Expense added successfully!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<com.binod.model.Expense> call, Throwable t) {
//                Toast.makeText(AddExpenseActivity.this, "Error code" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
