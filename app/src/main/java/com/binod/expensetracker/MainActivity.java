package com.binod.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.adapter.TodayExpenseHomeAdapter;
import com.binod.adapter.TodayIncomeHomeAdapter;
import com.binod.adapter.ViewPagerAdapter;
import com.binod.api.ExpenseAPI;
import com.binod.api.IncomeAPI;
import com.binod.fragment.ExpenseFragment;
import com.binod.fragment.IncomeFragment;
import com.binod.model.Expense;
import com.binod.model.Income;
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
    private TextView tvAddIncome, tvAddExpense, tvTodayRefreshH;
    RecyclerView rvTodayHome, rvTodayHomeI;
    ImageView imgSetting;

    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        imgSetting = findViewById(R.id.imgSetting);

        tvAddIncome = findViewById(R.id.tvAddIncome);
        tvAddExpense = findViewById(R.id.tvAddExpense);
        rvTodayHome = findViewById(R.id.rvTodayHome);
        rvTodayHomeI = findViewById(R.id.rvTodayHomeI);
        tvTodayRefreshH = findViewById(R.id.tvTodayRefreshH);

        sensorProximity();

        tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                String thisYear = (calendar.get(Calendar.YEAR)) + "";
                Log.d(thisYear, "thisYear : " + thisYear);

                String thisMonth = (calendar.get(Calendar.MONTH) + 1) + "";
                Log.d(thisMonth, "thisMonth : " + thisMonth);

                String thisDay = (calendar.get(Calendar.DAY_OF_MONTH)) + "";
                Log.d(thisDay,"thisDay : " + thisDay);

                Intent intent = new Intent(MainActivity.this, AddIncomeActivity.class);
                Bundle extra = new Bundle();
                extra.putString("thisDay", thisDay);
                extra.putString("thisMonth", thisMonth);
                extra.putString("thisYear", thisYear);
                intent.putExtras(extra);
                startActivity(intent);

            }
        });


        tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                String thisYear = String.valueOf(calendar.get(Calendar.YEAR));
                Log.d(thisYear, "thisYear : " + thisYear);

                String thisMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                Log.d(thisMonth, "thisMonth : " + thisMonth);

                String thisDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                Log.d(thisDay,"thisDay : " + thisDay);

                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                Bundle extra = new Bundle();
                extra.putString("thisDay", thisDay);
                extra.putString("thisMonth", thisMonth);
                extra.putString("thisYear", thisYear);
                intent.putExtras(extra);
                startActivity(intent);
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateProfileActiivty.class);
                startActivity(intent);
            }
        });


        //for top bar design
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new IncomeFragment(), "Income");
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
        todayExpenseH();
        todayIncomeH();

        tvTodayRefreshH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todayExpenseH();
                todayIncomeH();
            }
        });

    }
    private void todayExpenseH(){
        SimpleDateFormat formatter = new SimpleDateFormat("d");
        Date date = new Date();
        String today = formatter.format(date);


        ExpenseAPI expenseAPI = Url.getInstance().create(ExpenseAPI.class);
        Call<List<Expense>> listCall = expenseAPI.getByDays(Url.token,today);
        listCall.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "No data found" + response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Expense> expenseList = response.body();
                TodayExpenseHomeAdapter todayExpenseHomeAdapter = new TodayExpenseHomeAdapter(MainActivity.this, expenseList);
                rvTodayHome.setAdapter(todayExpenseHomeAdapter);
                rvTodayHome.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No data transaction", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void todayIncomeH(){
        SimpleDateFormat formatter = new SimpleDateFormat("d");
        Date date = new Date();
        String today = formatter.format(date);
        //for today incomes
        IncomeAPI incomeAPI = Url.getInstance().create(IncomeAPI.class);
        Call<List<Income>> listCall1 = incomeAPI.getByDays(Url.token, today);
        listCall1.enqueue(new Callback<List<Income>>() {
            @Override
            public void onResponse(Call<List<Income>> call, Response<List<Income>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Income> incomeList = response.body();
                TodayIncomeHomeAdapter todayIncomeHomeAdapter = new TodayIncomeHomeAdapter(MainActivity.this, incomeList);
                rvTodayHomeI.setAdapter(todayIncomeHomeAdapter);
                rvTodayHomeI.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<Income>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Errors" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void sensorProximity(){
        sensorManager=(SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);

        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener proxyListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0]<=1)
                {
                    Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(proxyListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
