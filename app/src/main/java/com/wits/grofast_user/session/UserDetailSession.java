package com.wits.grofast_user.session;

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
        return sharedPreferences.getString("userPhone", null);
    }

}
