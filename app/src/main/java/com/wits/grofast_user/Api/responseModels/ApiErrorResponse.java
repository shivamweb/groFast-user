package com.wits.grofast_user.Api.responseModels;

import java.lang.Integer;
import java.lang.String;

public class ApiErrorResponse{

  private String errorMessage;

  private String message;

  private Integer status;

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getMessage() {
    return message;
  }

  public Integer getStatus() {
    return status;
  }
}
