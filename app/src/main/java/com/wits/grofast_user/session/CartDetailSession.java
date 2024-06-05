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

    public void setTotalAmount(Integer total) {
        editor.putInt("userCartTotal", total);
    }

    public Integer getTotalAmount() {
        return sharedPreferences.getInt("userCartTotal", 0);
    }

    public void setDiscount(Integer discount) {
        editor.putInt("userCartDiscount", discount);
    }

    public Integer getDiscount() {
        return sharedPreferences.getInt("userCartDiscount", 0);
    }

    public void setDeleveryCharges(Integer charges) {
        editor.putInt("userCartDeleveryCharges", charges);
    }

    public Integer getDeleveryCharges() {
        return sharedPreferences.getInt("userCartDeleveryCharges", 0);
    }

    public void setSgst(Integer sgst) {
        editor.putInt("userCartSgst", sgst);
    }

    public Integer getSgst() {
        return sharedPreferences.getInt("userCartSgst", 0);
    }

    public void setCgst(Integer cgst) {
        editor.putInt("userCartCgst", cgst);
    }

    public Integer getCgst() {
        return sharedPreferences.getInt("userCartCgst", 0);
    }

    public void clearSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
