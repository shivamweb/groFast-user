package com.wits.grofast_user.Api.responseClasses;

import com.wits.grofast_user.Api.responseModels.WalletModel;

import java.util.List;

public class WalletResponse {
    private String message;

    private Integer status;

    private List<WalletModel> wallet;

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public List<WalletModel> getWallet() {
        return wallet;
    }
}
