package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model Address.
 */

public class Address implements Serializable {
    @SerializedName("postCode")
    private String postCode;
    @SerializedName("city")
    private String city;
    @SerializedName("street")
    private String street;
    @SerializedName("country")
    private String country;
    @SerializedName("houseNumber")
    private int houseNumber;
    @SerializedName("apartmentNumber")
    private int apartmentNumber;

    /**
     * getter Address, part object Info.
     * @return Address.
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * getter nr apartment, part object Info.
     * @return apartment.
     */
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    /**
     * getter city, part object Info.
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * getter country, part object Info.
     * @return country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * getter house number, part object Info.
     * @return house number.
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * getter Street, part object Info.
     * @return street.
     */
    public String getStreet() {
        return street;
    }
}
