package com.intive.toz.kindOfHelp.model;

import java.io.Serializable;

/**
 * The type KindOfHelp.
 */

public class KindOfHelp implements Serializable {

    private int charityPaymentPhoto;
    private int volunteerPhoto;


    /**
     * Instantiates a new KindOfHelp.
     *
     * @param charityPaymentPhoto the charity payment image
     * @param volunteerPhoto the volounteer image
     */
    public KindOfHelp(final int charityPaymentPhoto, final int volunteerPhoto) {
        this.charityPaymentPhoto = charityPaymentPhoto;
        this.volunteerPhoto = volunteerPhoto;
    }

    /**
     * Gets charityPaymentPhoto.
     *
     * @return the charityPaymentPhoto
     */
    public int getCharityPaymentPhoto() {
        return charityPaymentPhoto;
    }

    /**
     * Gets volunteerPhoto.
     *
     * @return the volunteerPhoto
     */
    public int getVolunteerPhoto() {
        return volunteerPhoto;
    }
}