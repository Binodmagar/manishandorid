package com.binod.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import com.binod.adapter.ViewPagerAdapter;
import com.binod.fragment.BalanceFragment;
import com.binod.fragment.IncomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CalendarView calendarView;
    private TextView tvAddIncome, tvAddExpense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tvAddIncome = findViewById(R.id.tvAddIncome);
        tvAddExpense = findViewById(R.id.tvAddExpense);

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
        viewPagerAdapter.addFragment(new BalanceFragment(),"Balance");
        viewPagerAdapter.addFragment(new BalanceFragment(), "Expense");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        //listener for calender
        calendarView = (CalendarView) findViewById(R.id.cvCalender);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Calendar calendar = Calendar.getInstance();
              String date = (month + 1) + "/" + dayOfMonth + "/" + year;
              Log.d(date,"onSelectedDayChange: MMM d, ''yyyy: " + date);

              Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
              intent.putExtra("date", date);
              startActivity(intent);

            }
        });
    }
}
