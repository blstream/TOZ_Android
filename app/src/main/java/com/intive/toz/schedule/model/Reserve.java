package com.intive.toz.schedule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Reserve.
 */
public class Reserve {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("ownerId")
    @Expose
    private String ownerId;
    @SerializedName("modificationMessage")
    @Expose
    private String modificationMessage;

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets owner id.
     *
     * @param ownerId the owner id
     */
    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Sets modification message.
     *
     * @param modificationMessage the modification message
     */
    public void setModificationMessage(final String modificationMessage) {
        this.modificationMessage = modificationMessage;
    }
}
