package com.intive.toz.petDetails.model;

import com.google.gson.annotations.SerializedName;

/**
 * Model how to help info.
 */

public class Help {

    @SerializedName("howToHelpDescription")
    String howToHelpDescription;

    @SerializedName("modifactionDate")
    String modifactionDate;

    public String getHowToHelpDescription() {
        return howToHelpDescription;
    }

    public String getModifactionDate() {
        return modifactionDate;
    }
}
