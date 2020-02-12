package com.binod.api;

import com.binod.model.User;
import com.binod.serverresponse.ImageResponse;
import com.binod.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserAPI {

    @POST("users/register")
    Call<SignUpResponse> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);


    @FormUrlEncoded
    @PUT("users/me")
    Call<User> updateUser(@Header("Authorization") String token, @Field("firstName") String firstName, @Field("lastName") String lastName, @Field("mobileNumber") String mobileNumber,
                          @Field("email") String email, @Field("password") String password, @Field("image") String image);

}
