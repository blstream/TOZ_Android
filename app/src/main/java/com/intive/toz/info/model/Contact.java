package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model Contact with us.
 */

public class Contact implements Serializable {
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private int phone;
    @SerializedName("fax")
    private int fax;
    @SerializedName("website")
    private String website;

    /**
     * getter email, part object Info.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter fax, part object Info.
     * @return fax.
     */
    public int getFax() {
        return fax;
    }

    /**
     * getter phone, part object Info.
     * @return phone.
     */
    public int getPhone() {
        return phone;
    }

    /**
     * getter website, part object Info.
     * @return website.
     */
    public String getWebsite() {
        return website;
    }

}
