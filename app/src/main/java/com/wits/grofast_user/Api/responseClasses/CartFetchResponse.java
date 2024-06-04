package com.wits.grofast_user.Api.responseClasses;

import com.google.gson.annotations.SerializedName;
import com.wits.grofast_user.Api.responseModels.CartModel;
import com.wits.grofast_user.Api.responseModels.TaxAndCharge;

import java.util.List;

public class CartFetchResponse {

    private Integer status;
    private String message;

    @SerializedName("cartDetails")
    private List<CartModel> cartModelList;

    private Double subtotal;
    private Double total;

    @SerializedName("tax_and_charges")
    private List<TaxAndCharge> taxAndCharges;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<CartModel> getCartModelList() {
        return cartModelList;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public List<TaxAndCharge> getTaxAndCharges() {
        return taxAndCharges;
    }
}
