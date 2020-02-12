package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.binod.bll.LoginBLL;
import com.binod.notification.NotificationChannel;
import com.binod.strictmode.StrictModeClass;

public class SplashScreenActivity extends AppCompatActivity {


    private NotificationManagerCompat notificationManagerCompat;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        NotificationChannel notificationChannel = new NotificationChannel(this);
        notificationChannel.createChannel();


        //using a thread and halt screen for 2 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                String email = sharedPreferences.getString("Email", "");
                String password = sharedPreferences.getString("Password", "");

                LoginBLL loginBLL = new LoginBLL();
                StrictModeClass.StrictMode();
                if (loginBLL.checkUser(email, password)) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    DisplayNotification();
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent1 = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent1);
                    Toast.makeText(SplashScreenActivity.this, "Email and password does not found", Toast.LENGTH_SHORT).show();

                }

            }
        }, 2000);
    }


    private void DisplayNotification() {
//        Intent intent = new Intent(this, MyBoar)
        Notification notification = new NotificationCompat.Builder(this, NotificationChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Login Message")
                .setContentText("User login success")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(count++, notification);


    }
}
