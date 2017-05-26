package com.intive.toz.volunteerForm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Become volunteer info.
 */
public class BecomeVolunteerInfo {
    @SerializedName("howToHelpDescription")
    @Expose
    private String howToHelpDescription;

    /**
     * Gets how to help description.
     *
     * @return the how to help description
     */
    public String getHowToHelpDescription() {
        return howToHelpDescription;
    }
}
