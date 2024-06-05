package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.AddressFetchResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AddressInterface {
    @GET("fetch-user-address")
    Call<AddressFetchResponse> fetchCustmerAddress();
}
