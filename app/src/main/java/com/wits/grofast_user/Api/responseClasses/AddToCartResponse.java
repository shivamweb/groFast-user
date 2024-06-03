package com.wits.grofast_user.Api.responseClasses;

import com.wits.grofast_user.Api.responseModels.CartItem;

import java.util.List;

public class AddToCartResponse {
    private String message;

    private CartItem cart_item;

    public String getMessage() {
        return message;
    }

    public CartItem getCart_item() {
        return cart_item;
    }
}
