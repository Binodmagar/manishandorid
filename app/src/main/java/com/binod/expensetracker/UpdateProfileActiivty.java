package com.binod.expensetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.binod.api.UserLoginAPI;
import com.binod.model.UserLogin;
import com.binod.serverresponse.ImageResponse;
import com.binod.strictmode.StrictModeClass;
import com.binod.url.Url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActiivty extends AppCompatActivity {

    CircleImageView imgProfileUP;
    EditText etFirstNameUP, etLastNameUP, etMobileNumberUP, etEmailUP, etPasswordUP;
    Button btnUpdate;
    String imagePath;
    String imgName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_actiivty);

        imgProfileUP = findViewById(R.id.imgProfileUP);
        etFirstNameUP = findViewById(R.id.etFirstNameUP);
        etLastNameUP = findViewById(R.id.etLastNameUP);
        etMobileNumberUP = findViewById(R.id.etMobileNumberUP);
        etEmailUP = findViewById(R.id.etEmailUP);
        etPasswordUP = findViewById(R.id.etPasswordUP);
        btnUpdate = findViewById(R.id.btnUpdate);

        loadCurrentUser();

        imgProfileUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageBrowse();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    saveImage();

                    String firstName = etFirstNameUP.getText().toString();
                    String lastName = etLastNameUP.getText().toString();
                    String mobile = etMobileNumberUP.getText().toString();
                    String email = etEmailUP.getText().toString();
                    String password = etPasswordUP.getText().toString();

                    UserLogin userLogin = new UserLogin(firstName, lastName, mobile, email, password, imagePath);

                    UserLoginAPI userLoginAPI = Url.getInstance().create(UserLoginAPI.class);
                    Call<UserLogin> userLoginAPICall = userLoginAPI.updateUser(Url.token, userLogin);

                    userLoginAPICall.enqueue(new Callback<UserLogin>() {
                        @Override
                        public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                            if(!response.isSuccessful()){
                                Toast.makeText(UpdateProfileActiivty.this, "Error Code", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(UpdateProfileActiivty.this, "Profile Updated successfully!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<UserLogin> call, Throwable t) {
                            Toast.makeText(UpdateProfileActiivty.this, "Error code" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(UpdateProfileActiivty.this, "Please provide valid credential!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void loadCurrentUser(){
        UserLoginAPI userLoginAPI = Url.getInstance().create(UserLoginAPI.class);
        Call<UserLogin> userLoginCall = userLoginAPI.getUserDetails(Url.token);

        userLoginCall.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UpdateProfileActiivty.this, "code", Toast.LENGTH_SHORT).show();
                    return;
                }

                String imgPath = Url.imagePath + response.body().getImage();
                Picasso.get().load(imgPath).into(imgProfileUP);
                String firstName = response.body().getFirstName();
                etFirstNameUP.setText(firstName);
                String lastName = response.body().getLastName();
                etLastNameUP.setText(lastName);
                String mobile = response.body().getMobileNumber();
                etMobileNumberUP.setText(mobile);
                String email = response.body().getEmail();
                etEmailUP.setText(email);
                String password = response.body().getPassword();
                etPasswordUP.setText(password);
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Toast.makeText(UpdateProfileActiivty.this, "Erros" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(){

    }
    public void ImageBrowse() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(data == null) {
                Toast.makeText(this, "Please select your image", Toast.LENGTH_SHORT).show();
            }
        }

        Uri uri = data.getData();
        imgProfileUP.setImageURI(uri);
        imagePath = getRealPathFormUri(uri);

    }

    private String getRealPathFormUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri,projection,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return  result;
    }

    private void saveImage(){
        File file = new File((imagePath));
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        UserLoginAPI userLoginAPI = Url.getInstance().create(UserLoginAPI.class);
        Call<ImageResponse> responseCall = userLoginAPI.uploadImage(body);

        StrictModeClass.StrictMode();

        //synchronous method
        try {
            Response<ImageResponse> imageResponseResponse = responseCall.execute();
            imgName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image Inserted success", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(this, "Errors", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public boolean validation(){
        boolean status = true;
        if(TextUtils.isEmpty(etFirstNameUP.getText().toString())){
            etFirstNameUP.setError("Please enter your first name");
            etFirstNameUP.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etLastNameUP.getText().toString())) {
            etLastNameUP.setError("Please enter your last name");
            etLastNameUP.requestFocus();
            status = false;
        }
        if(etMobileNumberUP.getText().toString().length() < 10){
            etMobileNumberUP.setError("Your phone number must be of 10 digits");
            etMobileNumberUP.requestFocus();
            status = false;
        }
        if(TextUtils.isEmpty(etEmailUP.getText().toString())){
            etEmailUP.setError("Please enter your email");
            etEmailUP.requestFocus();
            status = false;
        }
        if(etPasswordUP.getText().toString().length() < 5){
            etPasswordUP.setError("Your password must be more than 5");
            etPasswordUP.requestFocus();
            status = false;
        }
        return status;
    }
}
