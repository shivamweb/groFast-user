package com.wits.grofast_user.Api.responseClasses;

import com.wits.grofast_user.Api.responseModels.CartModel;

public class CartResponse {
    private String message;

    private CartModel cart_item;

    public String getMessage() {
        return message;
    }

    public CartModel getCart_item() {
        return cart_item;
    }
}
