package com.wits.grofast_user.Api.responseModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel {

    private Integer sgst;

    private String country;

    private String city;

    private String latitude;

    private Integer discount;

    private String created_at;

    private String uuid;

    private Integer order_status;

    private Integer delivery_charges;

    private String updated_at;

    private Integer payment_metod;

    private String receiver_name;

    private Integer tip;

    private Integer id;

    private String state;

    private String longitude;

    private String pincode;

    private String address;

    private String coupon;

    private Integer cgst;

    private String receiver_phone_no;

    private Integer total_amount;

    private Integer customer_id;

    private String additional_note;

    @SerializedName("customer_order_items")
    private List<OrderItemModel> orderItems;
}
