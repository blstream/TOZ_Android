package com.intive.toz.info.model;

/**
 * Model information about TOZ with nested objects.
 */

public class info {

    private String name;
    private bankAccount bankAccount;
    private contact contact;
    private address address;

    public String getName() {
        return name;
    }

    public com.intive.toz.info.model.address getAddress() {
        return address;
    }

    public com.intive.toz.info.model.bankAccount getBankAccount() {
        return bankAccount;
    }

    public com.intive.toz.info.model.contact getContact() {
        return contact;
    }
}
