package com.binod.expensetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binod.api.UserAPI;
import com.binod.bll.RegisterBLL;
import com.binod.serverresponse.ImageResponse;
import com.binod.strictmode.StrictModeClass;
import com.binod.url.Url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView imgProfile;
    private  Button btnSignUp;
    private  TextView tvAlreadyUser;
    private  EditText etFirstName, etLastName, etMobileNumber, etRemail, etRpassword, etConfirmPassword;
    String imagePath;
    String imgName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //binding
        btnSignUp = findViewById(R.id.btnSignUp);
        tvAlreadyUser = findViewById(R.id.tvAlreadyUser);

//        //binding
        imgProfile = findViewById(R.id.imgProfile);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etRemail = findViewById(R.id.etRemail);
        etRpassword = findViewById(R.id.etRpassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        //listener
        btnSignUp.setOnClickListener(this);
        tvAlreadyUser.setOnClickListener(this);
        imgProfile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.imgProfile:
                ImageBrowse();
                break;

            case R.id.btnSignUp:
                if(etConfirmPassword.getText().toString().equals(etRpassword.getText().toString())) {
                    if (checkValidation()) {
                        saveImage();
                        register();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(RegisterActivity.this, "Password doesnot match", Toast.LENGTH_SHORT).show();
                        etRpassword.requestFocus();
                        return;
                    }
                }
                break;

            case R.id.tvAlreadyUser:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
        }
    }

    private boolean checkValidation(){
        boolean status = true;
        if(TextUtils.isEmpty(etFirstName.getText().toString())){
            etFirstName.setError("Please enter your first name");
            etFirstName.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etLastName.getText().toString())) {
            etLastName.setError("Please enter your last name");
            etLastName.requestFocus();
            status = false;
        }
        if(etMobileNumber.getText().toString().length() < 10){
            etMobileNumber.setError("Your phone number must be of 10 digits");
            etMobileNumber.requestFocus();
            status = false;
        }
        if(TextUtils.isEmpty(etRemail.getText().toString())){
            etRemail.setError("Please enter your email");
            etRemail.requestFocus();
            status = false;
        }
        if(etRpassword.getText().toString().length() < 5){
            etRpassword.setError("Your password must be more than 5");
            etRpassword.requestFocus();
            status = false;
        }
        return status;
    }

    //for image browser for storage
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
        imgProfile.setImageURI(uri);
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

        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<ImageResponse> responseCall = userAPI.uploadImage(body);

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

    private void register(){
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String mobileNumber = etMobileNumber.getText().toString();
        String email = etRemail.getText().toString();
        String password = etRpassword.getText().toString();

        //User userLogin = new User(firstName, lastName, mobileNumber, email, password, imgName);
        RegisterBLL registerBLL = new RegisterBLL(firstName, lastName, mobileNumber, email, password, imgName);

        CheckPermission();
        if(registerBLL.addUser()){
            Toast.makeText(RegisterActivity.this, "register success", Toast.LENGTH_SHORT);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void CheckPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }

}
