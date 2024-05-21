package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseModels.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserInterface {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("phone_no") String phone_no);
}
