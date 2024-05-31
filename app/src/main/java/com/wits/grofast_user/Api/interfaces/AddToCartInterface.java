package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.AddToCartResponse;
import com.wits.grofast_user.Api.responseClasses.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddToCartInterface {
    @POST("add-to-cart")
    Call<AddToCartResponse> fetchcart( @Query("product_id") Integer product_id,@Query("amount") Integer amount,@Query("quantity") Integer quantity);
}
