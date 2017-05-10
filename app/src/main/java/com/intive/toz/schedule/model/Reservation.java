package com.intive.toz.schedule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Reservation.
 */
public class Reservation {

    @SerializedName("id")
    @Expose
    private String id;
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
    @SerializedName("ownerName")
    @Expose
    private String ownerName;
    @SerializedName("ownerSurname")
    @Expose
    private String ownerSurname;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("lastModified")
    @Expose
    private Integer lastModified;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
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
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
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
     * Gets owner id.
     *
     * @return the owner id
     */
    public String getOwnerId() {
        return ownerId;
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
     * Gets owner name.
     *
     * @return the owner name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets owner name.
     *
     * @param ownerName the owner name
     */
    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Gets owner surname.
     *
     * @return the owner surname
     */
    public String getOwnerSurname() {
        return ownerSurname;
    }

    /**
     * Sets owner surname.
     *
     * @param ownerSurname the owner surname
     */
    public void setOwnerSurname(final String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(final Integer created) {
        this.created = created;
    }

    /**
     * Gets last modified.
     *
     * @return the last modified
     */
    public Integer getLastModified() {
        return lastModified;
    }

    /**
     * Sets last modified.
     *
     * @param lastModified the last modified
     */
    public void setLastModified(final Integer lastModified) {
        this.lastModified = lastModified;
    }

}
