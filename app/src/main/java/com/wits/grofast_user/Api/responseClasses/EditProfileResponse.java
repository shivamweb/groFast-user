package com.wits.grofast_user.Api.responseClasses;

import com.google.gson.annotations.SerializedName;
import com.wits.grofast_user.Api.responseModels.UserResponseModel;

public class EditProfileResponse {
    private int status;
    private String message;

    @SerializedName("data")
    private UserResponseModel userProfile;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserResponseModel getUserProfile() {
        return userProfile;
    }
}

