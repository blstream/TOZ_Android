package com.intive.toz.data;

import com.intive.toz.info.model.Info;

/**
 * Created by Krzysiek on 2017-05-25.
 */

public class AddressChecker {

    String address;

    /**
     * method to prevent setText error when apartment or house number are not filled.
     *
     * @param financial obiect Info with address field.
     * @return correct string with number.
     */
    public String getCorrectAddress(final Info financial) {

        if(financial.getAddress().getHouseNumber().isEmpty()) {
            address = "" + financial.getAddress().getApartmentNumber();
        } else if (financial.getAddress().getApartmentNumber() == 0) {
            address = "" + financial.getAddress().getHouseNumber();
        } else {
            address = financial.getAddress().getApartmentNumber() + "/" + financial.getAddress().getHouseNumber();
        }
        return address;
    }
}
