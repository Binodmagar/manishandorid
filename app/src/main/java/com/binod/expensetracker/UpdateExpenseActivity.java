package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UpdateExpenseActivity extends AppCompatActivity {

    EditText etNameAEU, etAmountAEU, etNoteAEU;
    Button btnSaveAEU;
    Spinner spCategoryAEU, spAccountAEU;

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
        setContentView(R.layout.activity_update_expense);
        etNameAEU = findViewById(R.id.etNameAEU);
        etAmountAEU = findViewById(R.id.etAmountAEU);
        spCategoryAEU = findViewById(R.id.spCategoryAEU);
        spAccountAEU = findViewById(R.id.spAccountAEU);
        etNoteAEU = findViewById(R.id.etNoteAEU);
        btnSaveAEU = findViewById(R.id.btnSaveAEU);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            etNameAEU.setText(bundle.getString("nameE"));
            etAmountAEU.setText(bundle.getString("amountE"));
            etNoteAEU.setText(bundle.getString("noteE"));
        }

        //for setting up data in category
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Expense);
        spCategoryAEU.setAdapter(arrayAdapter);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Account);
        spAccountAEU.setAdapter(arrayAdapter1);

    }

}

