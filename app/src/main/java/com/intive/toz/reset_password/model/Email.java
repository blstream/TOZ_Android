package com.intive.toz.reset_password.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Email.
 */
public class Email {
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * Instantiates a new Email.
     *
     * @param email the email
     */
    public Email(final String email) {
        this.email = email;
    }
}
