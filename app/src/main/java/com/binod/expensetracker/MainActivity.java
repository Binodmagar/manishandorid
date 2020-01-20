package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.binod.adapter.ViewPagerAdapter;
import com.binod.fragment.BalanceFragment;
import com.binod.fragment.IncomeFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new IncomeFragment(), "Income");
        viewPagerAdapter.addFragment(new BalanceFragment(),"Balance");
        viewPagerAdapter.addFragment(new BalanceFragment(), "Expense");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
