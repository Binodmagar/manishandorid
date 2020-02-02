package com.binod.bll;

import android.content.SharedPreferences;
import android.util.Log;

import com.binod.api.UserLoginAPI;
import com.binod.serverresponse.SignUpResponse;
import com.binod.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;


    public boolean checkUser(String email, String password) {

        UserLoginAPI userLoginAPI = Url.getInstance().create(UserLoginAPI.class);
        Call<SignUpResponse> userCall = userLoginAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = userCall.execute();
            if (loginResponse.isSuccessful() && loginResponse.body().getStatus().equals("Login success")) {
                Url.token += loginResponse.body().getToken();

                isSuccess = true;
            }
        }catch (IOException e){
            Log.d("failure", " " + e);
            e.printStackTrace();
        }
        return isSuccess;
    }
}
