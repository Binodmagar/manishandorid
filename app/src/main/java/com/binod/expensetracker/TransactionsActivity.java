package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TransactionsActivity extends AppCompatActivity {

    private TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        tvDate = (TextView) findViewById(R.id.tvDate);

        Intent incommingIntent = getIntent();
        String date = incommingIntent.getStringExtra("date");

        tvDate.setText(date);
    }
}
