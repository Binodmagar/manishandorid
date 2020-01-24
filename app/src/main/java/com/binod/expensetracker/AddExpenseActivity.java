package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AddExpenseActivity extends AppCompatActivity {

    TextView tvDateAE;
    Spinner spExpense;

    public static final String Expense[] = {
            "Food",
            "Clothes",
            "General Expenses",
            "Medical",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        //binding
        tvDateAE = findViewById(R.id.tvDateAE);
        Intent incommingIntent = getIntent();
        String date = incommingIntent.getStringExtra("currentDate");
        tvDateAE.setText(date);

        spExpense = findViewById(R.id.spExpense);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Expense);
        spExpense.setAdapter(arrayAdapter);
    }
}
