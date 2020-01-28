package com.binod.expensetracker;

import android.util.Log;

import com.binod.api.UserLoginAPI;
import com.binod.serverresponse.SignUpResponse;
import com.binod.url.Url;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginTokenTest  {
    @Test
    public void testToken(){
        UserLoginAPI userLoginAPI = Url.getInstance().create(UserLoginAPI.class);
        Call<SignUpResponse> call = userLoginAPI.checkUser("binod@gmail.com","admin12345");

        try{
            Response<SignUpResponse> response = call.execute();
            Url.token += response.body().getToken();
            assertThat(Url.token, is(IsNull.notNullValue()));
        }catch (IOException e){
            Log.d("failure", "" + e);
            e.printStackTrace();
        }
    }
}