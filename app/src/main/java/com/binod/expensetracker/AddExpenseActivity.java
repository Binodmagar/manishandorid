package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.bll.ExpenseBLL;

import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {

    TextView tvDateAE, tvDayE, tvMonthE, tvYearE;
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
        tvDateAE = findViewById(R.id.tvDateAE);
        tvDayE = findViewById(R.id.tvDayE);
        tvMonthE = findViewById(R.id.tvMonthE);
        tvYearE = findViewById(R.id.tvYearE);

        //for incomming intent data
        Bundle extras = getIntent().getExtras();
        final String months = extras.getString("months");
        tvMonthE.setText(months);
        final String days = extras.getString("days");
        tvDayE.setText(days);
        final String years = extras.getString("years");
        tvYearE.setText(years);
        tvDateAE.setText(months + "-" + days + "-" + years);

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

    private void addExpense() {
        String name = etNameAE.getText().toString();
        int amount = Integer.parseInt(etAmountAE.getText().toString());
        String category = spCategoryAE.getSelectedItem().toString();
        String account = spAccountAE.getSelectedItem().toString();
        String days = tvDayE.getText().toString();
        String months = tvMonthE.getText().toString();
        String years = tvYearE.getText().toString();
        String description = etNoteAE.getText().toString();


        ExpenseBLL expenseBLL = new ExpenseBLL(name, amount, category, account, days, months, years, description);

        if (expenseBLL.addExpense(this)) {
            Toast.makeText(AddExpenseActivity.this, "Expense Added successfully", Toast.LENGTH_SHORT).show();
            etNameAE.setText("");
            etAmountAE.setText("");
            etNoteAE.setText("");
//            Intent intent = new Intent(AddExpenseActivity.this, TransactionsActivity.class);
//            startActivity(intent);
        } else {
            Toast.makeText(AddExpenseActivity.this, "Cannot add expense", Toast.LENGTH_SHORT).show();

        }
    }
}
