package com.wits.grofast_user.session;

import android.content.Context;
import android.content.SharedPreferences;

public class UserActivitySession {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public UserActivitySession(Context context) {
        sharedPreferences = context.getSharedPreferences("UserActivity", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoginStaus(boolean status) {
        editor.putBoolean("loginStatus", status);
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean("loginStatus", false);
    }
    public void setToken(String token) {
        editor.putString("userAccessToken", token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString("userAccessToken", "");
    }

    public void setProductFetchIndicator(int value) {
        editor.putInt("productFetchIndicator", value).apply();

//        0 -> fetchAllProducts
//        1 -> fetchProductsByCategory
    }

    public int getProductFetchIndicator() {
        return sharedPreferences.getInt("productFetchIndicator", 0);
    }

    public void setTip(String tip) {
        editor.putString("userOrderTip", tip);
        editor.apply();
    }

    public String getTip() {
        String tip = sharedPreferences.getString("userOrderTip", "0");
        if (tip.equals("")) {
            tip = "0";
        }
        return tip;
    }

    public void setCoupon(String tip) {
        editor.putString("userOrderCoupon", tip.trim());
        editor.apply();
    }

    public String getCoupon() {
        return sharedPreferences.getString("userOrderCoupon", "");
    }

    public void storeAditionalNote(String tip) {
        editor.putString("userAditionalNote", tip.trim());
        editor.apply();
    }

    public String getAditionalNote() {
        return sharedPreferences.getString("userAditionalNote", "");
    }

    public void clearSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
