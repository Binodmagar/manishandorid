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

import com.binod.bll.ExpenseBLL;
import com.binod.bll.IncomeBLL;

public class AddIncomeActivity extends AppCompatActivity {

    Spinner spIncomeAI, spAccountAI;
    EditText etNameAI,etAmountAI,etNoteAI;
    Button btnSaveAI;
    TextView tvDateAI;

    public static final String Income[] = {
            "Salary",
            "Bonus",
            "Vacation Allowance"
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

        btnSaveAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    addIncome();
                    finish();
                }else{
                    Toast.makeText(AddIncomeActivity.this, "Please check input credentials again", Toast.LENGTH_SHORT).show();
                    etNameAI.requestFocus();
                    return;
                }
            }
        });

    }


    public boolean checkValidation(){
        boolean status = true;
        if(TextUtils.isEmpty(etNameAI.getText().toString())){
            etNameAI.setError("Please enter income name!");
            etNameAI.requestFocus();
            status = false;
        }
        if(TextUtils.isEmpty(etAmountAI.getText().toString())){
            etAmountAI.setError("Please enter amount!");
            etAmountAI.requestFocus();
            status = false;
        }
        if(TextUtils.isEmpty(etNoteAI.getText().toString())){
            etNoteAI.setError("Please enter note");
            etNoteAI.requestFocus();
            status = false;
        }
        return status;
    }

    public void addIncome(){
        String name = etNameAI.getText().toString();
        int amount = Integer.parseInt(etAmountAI.getText().toString());
        String income = spIncomeAI.getSelectedItem().toString();
        String account = spAccountAI.getSelectedItem().toString();
        String date = tvDateAI.getText().toString();
        String note = etNoteAI.getText().toString();


        IncomeBLL incomeBLL = new IncomeBLL(name, amount, income, account, date, note);

        if(incomeBLL.addIncome()){
            etNameAI.setText("");
            etAmountAI.setText("");
            etNoteAI.setText("");
            etNameAI.requestFocus();
            Toast.makeText(this, "Incomes added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Cannot add income", Toast.LENGTH_SHORT).show();
            etNameAI.requestFocus();
        }
    }

}
