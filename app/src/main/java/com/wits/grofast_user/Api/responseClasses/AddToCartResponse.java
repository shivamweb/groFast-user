package com.wits.grofast_user.Api.responseClasses;

import com.wits.grofast_user.Api.responseModels.AddToCartModel;

import java.util.List;

public class AddToCartResponse {
    private String message;

    private List<AddToCartModel> addToCartModels;

    public String getMessage() {
        return message;
    }

    public List<AddToCartModel> getAddToCartModels() {
        return addToCartModels;
    }
}
