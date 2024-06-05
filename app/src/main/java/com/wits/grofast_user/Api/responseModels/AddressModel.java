package com.wits.grofast_user.Api.responseModels;

public class AddressModel {

    private Integer id;

    private Integer customer_id;

    private String country;

    private String state;

    private String city;

    private String pin_code;

    private String address;

    private String latitude;

    private String longitude;

    public Integer getId() {
        return id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getPin_code() {
        return pin_code;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
