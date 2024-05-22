package com.wits.grofast_user.Api.interfaces;
import com.wits.grofast_user.Api.responseClasses.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductInerface {
    @GET("fetchProducts")
    Call<ProductResponse> fetchProducts(@Query("page") int page);
}
