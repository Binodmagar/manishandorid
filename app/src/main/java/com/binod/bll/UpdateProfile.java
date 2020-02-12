package com.binod.bll;

import android.content.Context;
import android.content.SharedPreferences;

import com.binod.api.UserAPI;
import com.binod.model.User;
import com.binod.serverresponse.SignUpResponse;
import com.binod.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UpdateProfile {
    private  String firstName;
    private  String lastName;
    private  String mobileNumber;
    private  String email;
    private  String password;
    private  String image;
    boolean issuccess = false;

    public UpdateProfile(String firstName, String lastName, String mobileNumber, String email, String password, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public boolean updateUser (Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");

        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<User> userCall = userAPI.updateUser(token, firstName, lastName, mobileNumber, email, password, image);

        try {
            Response<User> response = userCall.execute();
            if(response.isSuccessful()){
                issuccess=true;
            }else {
                issuccess = false;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return issuccess;

    }
}
