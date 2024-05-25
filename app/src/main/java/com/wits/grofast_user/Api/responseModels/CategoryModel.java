package com.wits.grofast_user.Api.responseModels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class CategoryModel  {
  private String image;

  private String category_name;

  private Integer is_active;

  private Integer id;

  private String uuid;

  public String getImage() {
    return image;
  }

  public String getCategory_name() {
    return category_name;
  }

  public Integer getIs_active() {
    return is_active;
  }

  public Integer getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

}
