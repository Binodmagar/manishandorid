package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AddIncomeActivity extends AppCompatActivity {

    Spinner spIncome;
    TextView tvDateAI;

    public static final String Income[] = {
            "Salary",
            "Bonous",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        tvDateAI = findViewById(R.id.tvDateAI);

        spIncome = findViewById(R.id.spIncome);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Income);
        spIncome.setAdapter(arrayAdapter);

        Intent incommingIntent = getIntent();
        String date = incommingIntent.getStringExtra("currentDate");
        tvDateAI.setText(date);

    }

}
