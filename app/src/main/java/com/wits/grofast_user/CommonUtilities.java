package com.wits.grofast_user;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.AnyRes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Response;

public class CommonUtilities {

    public static void handleApiError(String TAG, Response  response, Context context){
        try {
            Log.e(TAG, "login status " + response.code());

            Gson gson = new Gson();
            JsonObject errorBodyJson = gson.fromJson(response.errorBody().string(), JsonObject.class);

            String errorMessage = errorBodyJson.get("errorMessage").getAsString();
            String status = errorBodyJson.get("status").getAsString();
            String message = errorBodyJson.get("message").getAsString();

            Log.e("TAG", "onResponse: Error ErrorMessege " + errorMessage);
            Log.e("TAG", "onResponse: Error status " + status);
            Log.e("TAG", "onResponse: Error message " + message);

            Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
