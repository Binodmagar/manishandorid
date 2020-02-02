package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddIncomeActivity extends AppCompatActivity {

    Spinner spIncomeAI, spAccountAI;
    EditText etNameAI,etAmountAI,etNoteAI;
    Button btnSaveAI;
    TextView tvDateAI;

    public static final String Income[] = {
            "Salary",
            "Bonous",
    };

    public static final String Account[] = {
            "Cash",
            "Cheque",
            "Card"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        //binding
        etNameAI = findViewById(R.id.etNameAI);
        etAmountAI = findViewById(R.id.etAmountAI);
        etNoteAI = findViewById(R.id.etNoteAI);
        tvDateAI = findViewById(R.id.tvDateAI);
        spIncomeAI = findViewById(R.id.spIncomeAI);
        spAccountAI = findViewById(R.id.spAccountAI);
        btnSaveAI = findViewById(R.id.btnSaveAI);

        //setting up data in adapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Income);
        spIncomeAI.setAdapter(arrayAdapter);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Account);
        spAccountAI.setAdapter(arrayAdapter1);

        //for incomming data from intent
        Intent incommingIntent = getIntent();
        String date = incommingIntent.getStringExtra("currentDate");
        tvDateAI.setText(date);

    }

}
