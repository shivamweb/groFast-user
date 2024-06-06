package com.wits.grofast_user.Api.responseClasses;

import com.google.gson.annotations.SerializedName;

public class OrderPlaceResponse {
    private int status;
    private String message;

    @SerializedName("order")
    private OrderModelPlaceOrder orderDetails;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OrderModelPlaceOrder getOrderDetails() {
        return orderDetails;
    }
}
