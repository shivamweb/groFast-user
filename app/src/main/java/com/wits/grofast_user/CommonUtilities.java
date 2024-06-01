package com.wits.grofast_user;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Response;

public class CommonUtilities {

    public static void handleApiError(String TAG, Response response, Context context) {
        try {
            Log.e(TAG, "login status " + response.code());

            String errorBodyString = response.errorBody().string();
            Gson gson = new Gson();
            JsonObject errorBodyJson = gson.fromJson(errorBodyString, JsonObject.class);

            String errorMessage = errorBodyJson.has("errorMessage") ? errorBodyJson.get("errorMessage").getAsString() : "No errorMessage";
            String status = errorBodyJson.has("status") ? errorBodyJson.get("status").getAsString() : "No status";
            String message = errorBodyJson.has("message") ? errorBodyJson.get("message").getAsString() : "No message";

            Log.e(TAG, "onResponse -> status       : " + status);
            Log.e(TAG, "onResponse -> errorMessage : " + errorMessage);
            Log.e(TAG, "onResponse -> message      : " + message);

            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "handleApiError: error " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static String getPathFromUri(Context context, Uri uri) {
        String[] projection = {android.provider.MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(column_index);
        cursor.close();
        return imagePath;
    }


    public static String formatDate(String inputDate, String inputFormat, String outputFormat) {
        String outputDate = null;
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
            Date date = inputDateFormat.parse(inputDate);
            outputDate = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }


    public static String showToast(Context context, String message) {
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_product_add_in_cart, null);
        TextView toastMessage = toastView.findViewById(R.id.toast_product_add_in_cart);
        toastMessage.setText(message);

        FrameLayout frameLayout = new FrameLayout(toastView.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 5, 10, 0);
        toastView.setLayoutParams(layoutParams);

        frameLayout.addView(toastView);

        Toast toast = new Toast(toastView.getContext());
        toast.setView(frameLayout);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.END, 0, 0);
        toast.show();
        return message;
    }
}
