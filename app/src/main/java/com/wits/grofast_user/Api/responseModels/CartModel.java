package com.wits.grofast_user.Api.responseModels;

public class CartModel {
    private Integer customer_id;
    private Integer product_id;
    private Integer quantity;
    private String uuid;
    private Integer id;
    private Integer amount;

    public Integer getCustomer_id() {
        return customer_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }
}
