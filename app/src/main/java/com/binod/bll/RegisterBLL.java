package com.binod.bll;

import com.binod.api.UserLoginAPI;
import com.binod.model.UserLogin;
import com.binod.serverresponse.SignUpResponse;
import com.binod.url.Url;

import java.io.IOException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBLL {

    private  String firstName;
    private  String lastName;
    private  String mobileNumber;
    private  String email;
    private  String password;
    private  String image;
    boolean issuccess = false;

    public RegisterBLL(String firstName, String lastName, String mobileNumber, String email, String password, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public boolean addUser (){
        UserLoginAPI userLoginAPI = Url.getInstance().create(UserLoginAPI.class);

        UserLogin userLogin = new UserLogin(firstName, lastName, mobileNumber, email, password, image);
        Call<SignUpResponse> userCall = userLoginAPI.registerUser(userLogin);

        try {
            Response<SignUpResponse> response = userCall.execute();
            if(response.isSuccessful() && response.body().getStatus().equals("Register successfully!!")){
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
