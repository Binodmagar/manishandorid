package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //using a thread and halt screen for 2 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
               Boolean agreed = sharedPreferences.getBoolean("agreed", false);

               if(agreed.equals(true)){
                   Toast.makeText(SplashScreenActivity.this, "Success", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                   startActivity(intent);
               }else {
                   Intent intent = new Intent(SplashScreenActivity.this, LoginForm.class);
                   startActivity(intent);
               }
            }
        },2000);
    }

//    private void checkUser(){
//        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
//        String email = sharedPreferences.getString("Email", "");
//        String password = sharedPreferences.getString("Password", "");
//
//        if(email.equals("admin@gmail.com") && password.equals("admin")){
//            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//            startActivity(intent);
//        }else{
//            Intent intent = new Intent(SplashScreenActivity.this, LoginForm.class);
//            startActivity(intent);
//        }
//    }
}
