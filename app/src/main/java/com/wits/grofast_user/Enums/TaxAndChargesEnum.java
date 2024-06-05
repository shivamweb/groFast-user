package com.wits.grofast_user.Enums;

public enum TaxAndChargesEnum {
    CGST("CGST"), SGST("SGST"), DISCOUNT("Discount"), DELEVERYCHARGES("Delivery Charges");

    private String tag;

    TaxAndChargesEnum(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
