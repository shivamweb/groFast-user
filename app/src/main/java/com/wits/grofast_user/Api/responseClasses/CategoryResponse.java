package com.wits.grofast_user.Api.responseClasses;

import com.google.gson.annotations.SerializedName;
import com.wits.grofast_user.Api.paginatedResponses.CategoryPaginatedRes;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class CategoryResponse implements Serializable {

  private String message;

  private Integer status;

  @SerializedName("data")
  private CategoryPaginatedRes paginatedCategories;

  public String getMessage() {
    return message;
  }

  public Integer getStatus() {
    return status;
  }

  public CategoryPaginatedRes getPaginatedCategories() {
    return paginatedCategories;
  }
}
