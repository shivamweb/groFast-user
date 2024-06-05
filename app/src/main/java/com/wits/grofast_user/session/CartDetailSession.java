package com.wits.grofast_user.session;

import android.content.Context;
import android.content.SharedPreferences;

public class CartDetailSession {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;


    public CartDetailSession(Context context) {
        sharedPreferences = context.getSharedPreferences("UserCartDetail", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
