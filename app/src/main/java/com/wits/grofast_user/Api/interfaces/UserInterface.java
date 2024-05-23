package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.UserRequest;
import com.wits.grofast_user.Api.responseClasses.EditProfileResponse;
import com.wits.grofast_user.Api.responseClasses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface UserInterface {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("phone_no") String phone_no);

    @POST("createCustomerProfile")
    @Multipart
    Call<EditProfileResponse> updateProfile();

    @POST("createCustomerProfile")
    Call<EditProfileResponse> updateProfile1(@Body UserRequest userRequest);
}
