package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.adapter.TransactionAdpater;
import com.binod.api.ExpenseAPI;
import com.binod.model.Expense;
import com.binod.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsActivity extends AppCompatActivity {

    private Button btnIncomeT, btnExpenseT;
    private TextView tvDate;
    RecyclerView rcTransactionDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        //binding
        btnExpenseT = findViewById(R.id.btnExpenseT);
        btnIncomeT = findViewById(R.id.btnIncomeT);
        tvDate = (TextView) findViewById(R.id.tvDate);
        rcTransactionDay = findViewById(R.id.rcTransactionDay);


        //for incomming intent data
        Bundle extras = getIntent().getExtras();
        final String months = extras.getString("months");
        final String days = extras.getString("days");
        final String years = extras.getString("years");

        Log.d(months,"onSelectedDayChange: MMM d, ''yyyy: " + months);
        Log.d(days,"onSelectedDayChange: MMM d, ''yyyy: " + days);
        Log.d(years,"onSelectedDayChange: MMM d, ''yyyy: " + years);

        tvDate.setText(months + "-" + days + "-" + years);

        btnIncomeT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionsActivity.this, AddIncomeActivity.class);
                Bundle extra = new Bundle();
                extra.putString("months",months);
                extra.putString("days", days);
                extra.putString("years",years);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


        btnExpenseT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionsActivity.this, AddExpenseActivity.class);
                Bundle extra = new Bundle();
                extra.putString("months",months);
                extra.putString("days", days);
                extra.putString("years",years);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);
        Call<List<Expense>> listCall = expenseAPI.getByUser(Url.token);
        listCall.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(TransactionsActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Expense> expenseList = response.body();
                TransactionAdpater transactionAdpater = new TransactionAdpater(TransactionsActivity.this, expenseList);
                rcTransactionDay.setAdapter(transactionAdpater);
                rcTransactionDay.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this,LinearLayoutManager.VERTICAL,false));

            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {

                Toast.makeText(TransactionsActivity.this, "failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });



    }
}
