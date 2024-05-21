package com.wits.grofast_user.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static String domain = "https://grofast.in/";


    public static Retrofit getClient() {

            return new Retrofit.Builder()
                    .baseUrl(domain+"api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}
