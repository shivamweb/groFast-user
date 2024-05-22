package com.wits.grofast_user;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.UserInterface;
import com.wits.grofast_user.Api.responseModels.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    AppCompatButton Continue;
    EditText phoneNo;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Continue = findViewById(R.id.Continue);
        phoneNo = findViewById(R.id.phone_no);


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNo.getText().toString().trim();
                Log.e(TAG, "onClick: phone no " + phone);
                UserInterface userInterface = RetrofitService.getClient().create(UserInterface.class);
                Call<LoginResponse> call = userInterface.login(phone);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            Toast.makeText(getApplicationContext(), ""+loginResponse.getMessage() , Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: message " + loginResponse.getMessage());
                            Log.i(TAG, "onResponse: phoneNo " + loginResponse.getPhone_no());
                            Log.i(TAG, "onResponse: otp " + loginResponse.getOtp());

                            Intent in = new Intent(getApplicationContext(), OtpPage.class);
                            in.putExtra("mobileNo", loginResponse.getPhone_no());
                            in.putExtra("mobileOtp", loginResponse.getOtp());
                            startActivity(in);
                        } else {
                            handleApiError(TAG,response,getApplicationContext());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}