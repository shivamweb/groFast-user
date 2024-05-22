package com.wits.grofast_user.Api.responseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.List;

public class CategoryResponse implements Serializable {

  private String message;

  private Integer status;

  @SerializedName("data")
  private CategoryPaginatedResponse paginatedCategories;

  public String getMessage() {
    return message;
  }

  public Integer getStatus() {
    return status;
  }

  public CategoryPaginatedResponse getPaginatedCategories() {
    return paginatedCategories;
  }
}
