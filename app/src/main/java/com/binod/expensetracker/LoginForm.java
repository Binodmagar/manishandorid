package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.bll.LoginBLL;
import com.binod.notification.NotificationChannel;
import com.binod.strictmode.StrictModeClass;


public class LoginForm extends AppCompatActivity implements View.OnClickListener {

    private NotificationManagerCompat notificationManagerCompat;

      EditText etEmail, etPassword;
      Button btnLogin;
      TextView tvCreateAccount;

    int count = 0;


    public void Login_Fragment(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
//        getSupportActionBar().setTitle("test");
        notificationManagerCompat = NotificationManagerCompat.from(this);
        NotificationChannel notificationChannel = new NotificationChannel(this);
        notificationChannel.createChannel();

        //binding
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        //listener
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                if(checkValidation()){
                    login();
                    DisplayNotification();
                    SaveEmailPassword();
                }else{
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

            case R.id.tvCreateAccount:

                Intent intent = new Intent(LoginForm.this, RegisterActivity.class);
                startActivity(intent);
        }
    }

    private   boolean checkValidation(){
        boolean status = true;
        if(TextUtils.isEmpty(etEmail.getText()) || etEmail.length() == 0){
         etEmail.setError("Please enter email");
         etEmail.requestFocus();
            status = false;
        }else if(TextUtils.isEmpty(etPassword.getText())){
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            status = false;
        }
        return status;
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if(loginBLL.checkUser(email, password)){
            Intent intent = new Intent(LoginForm.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Either Username or password is incorrect", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
        }
    }

    private void SaveEmailPassword(){
        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", etEmail.getText().toString());
        editor.putString("Password",etPassword.getText().toString());
        editor.commit();
    }


    private void DisplayNotification(){
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
