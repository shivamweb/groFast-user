package com.wits.grofast_user.Api.responseClasses;

import com.wits.grofast_user.Api.responseModels.CartModel;

import java.util.List;

public class CartResponse {
    private String message;

    private List<CartModel> addToCartModels;

    public String getMessage() {
        return message;
    }

    public List<CartModel> getAddToCartModels() {
        return addToCartModels;
    }
}
