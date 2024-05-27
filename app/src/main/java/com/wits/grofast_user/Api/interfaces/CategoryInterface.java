package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CategoryInterface {
    @GET("fetchAllCategories")
    Call<CategoryResponse> fetchCategories(@Query("page") int page);
}
