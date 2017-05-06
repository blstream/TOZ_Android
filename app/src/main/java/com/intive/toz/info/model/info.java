package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model information about TOZ with nested objects.
 */

public class info implements Serializable{

    @SerializedName("name")
    private String name;
    @SerializedName("bankAccount")
    private com.intive.toz.info.model.bankAccount bankAccount;
    @SerializedName("contact")
    private com.intive.toz.info.model.contact contact;
    @SerializedName("address")
    private address adres;

    /**
     * getter name department.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter object address.
     * @return address object.
     */
    public address getAddress() {
        return adres;
    }

    /**
     * getter object address.
     * @return address object.
     */
    public com.intive.toz.info.model.bankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * getter object contact.
     * @return contact object.
     */
    public com.intive.toz.info.model.contact getContact() {
        return contact;
    }
}
