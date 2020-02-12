package com.binod.bll;

import com.binod.api.UserAPI;
import com.binod.serverresponse.SignUpResponse;
import com.binod.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;


    public boolean checkUser(String email, String password) {

        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> userCall = userAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = userCall.execute();
            if (loginResponse.isSuccessful() && loginResponse.body().getStatus().equals("Login success")) {
                Url.token += loginResponse.body().getToken();

                isSuccess = true;
            }
        }catch (IOException e){
//            Log.d("failure", " " + e);
            e.printStackTrace();
        }
        return isSuccess;
    }
}
