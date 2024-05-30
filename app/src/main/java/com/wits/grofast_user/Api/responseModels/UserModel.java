package com.wits.grofast_user.Api.responseModels;

import java.io.Serializable;

public class UserModel implements Serializable {

  private Integer id;
  private Integer is_wallet_activated;
  private String uuid;
  private String phone_no;
  private String image;
  private String email;
  private String gender;
  private String name;

  public Integer getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getPhone_no() {
    return phone_no;
  }

  public String getImage() {
    return image;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public String getName() {
    return name;
  }

  public Integer getWalletStatus() {
    return is_wallet_activated;
  }
}
