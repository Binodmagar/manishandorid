package com.binod.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static Button btnSignUp;
    private static TextView tvAlreadyUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //binding
        btnSignUp = findViewById(R.id.btnSignUp);
        tvAlreadyUser = findViewById(R.id.tvAlreadyUser);

        //listener
        btnSignUp.setOnClickListener(this);
        tvAlreadyUser.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:
                checkValidation();
                break;

            case R.id.tvAlreadyUser:
                Intent intent = new Intent(RegisterActivity.this, LoginForm.class);
                startActivity(intent);
        }
    }

    private void checkValidation(){

    }
}
