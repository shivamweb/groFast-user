package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductInerface {
    @GET("fetchProducts")
    Call<CategoryResponse> fetchProducts(@Query("page") int page);
}
