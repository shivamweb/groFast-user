package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.CartResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CartInterface {
    @POST("add-to-cart")
    Call<CartResponse> addToCart(@Query("product_id") Integer product_id, @Query("amount") Integer amount, @Query("quantity") Integer quantity);
}
