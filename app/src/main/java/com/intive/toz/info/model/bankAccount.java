package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model bank account information. part Info.
 */

public class BankAccount {
    @SerializedName("number")
    private String number;
    @SerializedName("bankName")
    private String bankName;

    /**
     * getter bank name, part object info.
     * @return bank name.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * getter bank number, part object info.
     * @return bank number.
     */
    public String getNumber() {
        return number;
    }
}
