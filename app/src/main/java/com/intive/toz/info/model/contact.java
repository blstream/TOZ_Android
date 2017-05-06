package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model contact with us.
 */

public class contact implements Serializable{
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private int phone;
    @SerializedName("fax")
    private int fax;
    @SerializedName("website")
    private String website;

    /**
     * getter email, part object info.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter fax, part object info.
     * @return fax.
     */
    public int getFax() {
        return fax;
    }

    /**
     * getter phone, part object info.
     * @return phone.
     */
    public int getPhone() {
        return phone;
    }

    /**
     * getter website, part object info.
     * @return website.
     */
    public String getWebsite() {
        return website;
    }
}
