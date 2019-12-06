package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.UniversalTimeScale;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginForm extends AppCompatActivity implements View.OnClickListener {

    private static View view;
    public static EditText etEmail, etPassword;
    private static Button btnLogin;
    private static TextView tvCreateAccount;
    private static Animation animation;

    public void Login_Fragment(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
//        getSupportActionBar().setTitle("test");

        //binding
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        //listener
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);

//      animation = AnimationUtils.loadAnimation() ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                checkValidation();
                if(etEmail.getText().toString().equals("admin@gmail.com") && etPassword.getText().toString().equals("admin")){
                    Intent intent = new Intent(LoginForm.this, MainActivity.class);
                    startActivity(intent);
                }
                break;


            case R.id.tvCreateAccount:
                Intent intent = new Intent(LoginForm.this, RegisterActivity.class);
                startActivity(intent);


        }
    }

    private  void checkValidation(){
        //get email and password
        String getEmail = etEmail.getText().toString();
        String getPassword = etPassword.getText().toString();

        if(TextUtils.isEmpty(etEmail.getText()) || etEmail.length() == 0){
         Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();

            return;
        }else if(TextUtils.isEmpty(etPassword.getText())){
            etPassword.setError("Please enter your password");
            return;
        }


        //check pattern for email
//        Pattern p = Pattern.compile();
//        Matcher m = p.matcher(getEmail);

        //check if email and password is empty or not
        if(getEmail.equals("") || getEmail.length() == 0
        || getPassword.equals("") || getPassword.length() == 0){
            Toast.makeText(getApplicationContext(), "Enter Both credentials.", Toast.LENGTH_SHORT).show();
        }

//        else if(!m.find())

    }
}
