package com.wits.grofast_user.Api;

import okhttp3.MultipartBody;

public class UserRequest {
    private String phone_no;
    private String name;
    private String email;
    private String gender;
    private MultipartBody.Part image;

    public UserRequest(String phone_no, String name, String email, String gender, MultipartBody.Part image) {
        this.phone_no = phone_no;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.image = image;
    }
}
