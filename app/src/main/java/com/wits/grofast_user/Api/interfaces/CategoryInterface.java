package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryInterface {

    @GET("fetchCategories/{page}")
    Call<CategoryResponse> fetchCategories(@Path("page") int page);
}
