package com.intive.toz.info.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model Address.
 */

public class Address {
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
     * getter address, part object info.
     * @return address.
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * getter nr apartment, part object info.
     * @return apartment.
     */
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    /**
     * getter city, part object info.
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * getter country, part object info.
     * @return country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * getter house number, part object info.
     * @return house number.
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * getter Street, part object info.
     * @return street.
     */
    public String getStreet() {
        return street;
    }
}
