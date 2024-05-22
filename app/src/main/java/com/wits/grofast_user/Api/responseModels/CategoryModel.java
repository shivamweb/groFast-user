package com.wits.grofast_user.Api.responseModels;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class CategoryModel implements Parcelable {
  private String image;

  private String category_name;

  private Integer is_active;

  private Integer id;

  private String uuid;

  protected CategoryModel(Parcel in) {
    image = in.readString();
    category_name = in.readString();
    if (in.readByte() == 0) {
      is_active = null;
    } else {
      is_active = in.readInt();
    }
    if (in.readByte() == 0) {
      id = null;
    } else {
      id = in.readInt();
    }
    uuid = in.readString();
  }

  public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
    @Override
    public CategoryModel createFromParcel(Parcel in) {
      return new CategoryModel(in);
    }

    @Override
    public CategoryModel[] newArray(int size) {
      return new CategoryModel[size];
    }
  };

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeString(image);
    dest.writeString(category_name);
    if (is_active == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(is_active);
    }
    if (id == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeInt(id);
    }
    dest.writeString(uuid);
  }
}
