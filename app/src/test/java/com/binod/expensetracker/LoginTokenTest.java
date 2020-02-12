package com.binod.expensetracker;

import com.binod.api.UserAPI;
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
        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> call = userAPI.checkUser("roman@gmail.com","roman12345");

        try{
            Response<SignUpResponse> response = call.execute();
            Url.token += response.body().getToken();
            assertThat(Url.token, is(IsNull.notNullValue()));
        }catch (IOException e){
//            Log.d("failure", "" + e);
            e.printStackTrace();
        }
    }
}
