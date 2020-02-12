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

import com.binod.adapter.HomeIncomeAdapter;
import com.binod.adapter.HomeExpenseAdpater;
import com.binod.adapter.TransactionExpenseAdapter;
import com.binod.adapter.TransactionIncomeAdapter;
import com.binod.api.ExpenseAPI;
import com.binod.api.IncomeAPI;
import com.binod.model.Expense;
import com.binod.model.Income;
import com.binod.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsActivity extends AppCompatActivity {

    private Button btnIncomeT, btnExpenseT;
    private TextView tvDate, tvTodayRefresh;
    RecyclerView rcTransactionDay, rcTransactionDay1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        //binding
        btnExpenseT = findViewById(R.id.btnExpenseT);
        btnIncomeT = findViewById(R.id.btnIncomeT);
        tvDate = (TextView) findViewById(R.id.tvDate);
        rcTransactionDay = findViewById(R.id.rcTransactionDay);
        rcTransactionDay1 = findViewById(R.id.rcTransactionDay1);
        tvTodayRefresh = findViewById(R.id.tvTodayRefresh);


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

        tvTodayRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseRecycle();
                incomeRecycle();
            }
        });
        expenseRecycle();
        incomeRecycle();

    }
    private void expenseRecycle(){
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
                TransactionExpenseAdapter transactionExpenseAdapter = new TransactionExpenseAdapter(TransactionsActivity.this, expenseList);
                rcTransactionDay.setAdapter(transactionExpenseAdapter);
                rcTransactionDay.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this,LinearLayoutManager.VERTICAL,false));

            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {

                Toast.makeText(TransactionsActivity.this, "failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void incomeRecycle(){
        IncomeAPI incomeAPI = Url.getInstance().create(IncomeAPI.class);
        Call<List<Income>> listCall1 = incomeAPI.getByUser(Url.token);
        listCall1.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(Call<List<Income>> call, Response<List<Income>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(TransactionsActivity.this, "Code" + response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Income> incomeList = response.body();
                TransactionIncomeAdapter transactionIncomeAdapter = new TransactionIncomeAdapter(TransactionsActivity.this, incomeList);
                rcTransactionDay1.setAdapter(transactionIncomeAdapter);
                rcTransactionDay1.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<Income>> call, Throwable t) {
                Toast.makeText(TransactionsActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
