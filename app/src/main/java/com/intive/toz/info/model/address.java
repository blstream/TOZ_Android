package com.intive.toz.info.model;

/**
 * Model address.
 */

public class address {
    private String postCode;
    private String city;
    private String street;
    private String country;
    private int houseNumber;
    private int apartmentNumber;

    public String getPostCode() {
        return postCode;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }
}
