package com.wits.grofast_user.session;

import static com.wits.grofast_user.Api.RetrofitService.domain;

import android.content.Context;
import android.content.SharedPreferences;

public class UserDetailSession {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public UserDetailSession(Context context) {
        sharedPreferences = context.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setPhoneNo(String phone) {
        editor.putString("userPhone", phone);
        editor.apply();
    }
    public String getPhoneNo() {
        return sharedPreferences.getString("userPhone", "");
    }


    public void setName(String name) {
        editor.putString("userName", name);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString("userName", "");
    }

    public void setImage(String image) {
        editor.putString("userImage", domain+image);
        editor.apply();
    }

    public String getImage() {
        return sharedPreferences.getString("userImage", null);
    }

    public void setEmail(String email) {
        editor.putString("userEmail", email);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString("userEmail", "");
    }

    public void setGender(String gender) {
        editor.putString("userGender", gender);
        editor.apply();
    }

    public String getGender() {
        return sharedPreferences.getString("userGender", "");
    }

    public void clearSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
