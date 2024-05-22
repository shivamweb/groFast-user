package com.wits.grofast_user.Api.responseClasses;

import java.lang.Integer;
import java.lang.String;

public class LoginResponse  {
  private String access_token;

  private String phone_no;

  private String otp;

  private String message;

  public String getAccess_token() {
    return access_token;
  }

  public String getPhone_no() {
    return phone_no;
  }

  public String getOtp() {
    return otp;
  }

  public String getMessage() {
    return message;
  }
}
