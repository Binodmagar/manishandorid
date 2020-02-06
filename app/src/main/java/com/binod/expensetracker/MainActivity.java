package com.binod.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.adapter.TransactionAdpater;
import com.binod.adapter.ViewPagerAdapter;
import com.binod.api.ExpenseAPI;
import com.binod.fragment.BalanceFragment;
import com.binod.fragment.ExpenseFragment;
import com.binod.fragment.IncomeFragment;
import com.binod.model.Expense;
import com.binod.url.Url;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private CalendarView calendarView;
    private TextView tvAddIncome, tvAddExpense;
    RecyclerView rvTodayHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        tvAddIncome = findViewById(R.id.tvAddIncome);
        tvAddExpense = findViewById(R.id.tvAddExpense);
       // rvTodayHome = findViewById(R.id.rvTodayHome);

        tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                Intent intent = new Intent(MainActivity.this, AddIncomeActivity.class);
                intent.putExtra("currentDate", currentDate);
                startActivity(intent);
            }
        });


        tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                intent.putExtra("currentDate", currentDate);
                startActivity(intent);
            }
        });


        //for top bar design
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new IncomeFragment(), "Income");
//        viewPagerAdapter.addFragment(new BalanceFragment(),"Balance");
        viewPagerAdapter.addFragment(new ExpenseFragment(), "Expense");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //listener for calender
        calendarView = (CalendarView) findViewById(R.id.cvCalender);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Calendar calendar = Calendar.getInstance();

              String months = ((month + 1) +"");
              String days = dayOfMonth + "";
              String years = year +"";
              Log.d(months,"onSelectedDayChange: MMM d, ''yyyy: " + months);
                Log.d(days,"onSelectedDayChange: MMM d, ''yyyy: " + days);
                Log.d(years,"onSelectedDayChange: MMM d, ''yyyy: " + years);


              Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
              Bundle extra = new Bundle();
              extra.putString("months",months);
              extra.putString("days", days);
              extra.putString("years",years);
              intent.putExtras(extra);
              startActivity(intent);

            }
        });
//
//        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);
//        Call<List<Expense>> listCall = expenseAPI.getByUser(Url.token);
//        listCall.enqueue(new Callback<List<Expense>>() {
//            @Override
//            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
//                if (!response.isSuccessful()) {
//
//                    Toast.makeText(MainActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<Expense> expenseList = response.body();
//                TransactionAdpater transactionAdpater = new TransactionAdpater(MainActivity.this, expenseList);
//                rvTodayHome.setAdapter(transactionAdpater);
//                rvTodayHome.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Expense>> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this, "failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
    }
}
