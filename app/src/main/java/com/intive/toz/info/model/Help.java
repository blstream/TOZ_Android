package com.intive.toz.info.model;


import com.google.gson.annotations.SerializedName;

/**
 * Model how to help info.
 */

public class Help {

    @SerializedName("howToHelpDescription")
    String howToHelpDescription;

    @SerializedName("modifactionDate")
    String modifactionDate;

    /**
     * getter desc how to donate.
     *
     * @return description.
     */
    public String getHowToHelpDescription() {
        return howToHelpDescription;
    }

    /**
     * getter modification date.
     *
     * @return modification date.
     */
    public String getModifactionDate() {
        return modifactionDate;
    }
}