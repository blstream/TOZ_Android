package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model information about TOZ with nested objects.
 */

public class Info implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("bankAccount")
    private BankAccount bankAccount;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("address")
    private Address address;

    /**
     * getter name institution.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter object Address.
     * @return Address object.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * getter object Address.
     * @return Address object.
     */
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * getter object contact.
     * @return contact object.
     */
    public Contact getContact() {
        return contact;
    }
}
