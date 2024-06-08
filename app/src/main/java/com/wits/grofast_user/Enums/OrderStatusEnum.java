package com.wits.grofast_user.Enums;

import com.wits.grofast_user.R;

public enum OrderStatusEnum {

    Processing("Processing", R.color.color_button), Pending("Pending", R.color.change_button_color), Rejected("Rejected", R.color.expired), Cancelled("Cancelled", R.color.default_color), Delivered("Delivered", R.color.Login_theme);
    private int colorCode;
    private String tag;

    OrderStatusEnum(String tag, int colorCode) {
        this.colorCode = colorCode;
    }

    public int getColorCode() {
        return colorCode;
    }

    public String getTag() {
        return tag;
    }
}
