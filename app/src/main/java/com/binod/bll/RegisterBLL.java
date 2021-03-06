package com.binod.bll;

import com.binod.api.UserAPI;
import com.binod.model.User;
import com.binod.serverresponse.SignUpResponse;
import com.binod.url.Url;

import java.io.IOException;

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
        UserAPI userAPI = Url.getInstance().create(UserAPI.class);

        User user = new User(firstName, lastName, mobileNumber, email, password, image);
        Call<SignUpResponse> userCall = userAPI.registerUser(user);

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
