package com.intive.toz.schedule.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Schedule.
 */
public class Schedule {
    @SerializedName("configs")
    @Expose
    private List<Config> configs = null;
    @SerializedName("reservations")
    @Expose
    private List<Reservation> reservations = null;

    /**
     * Gets configs.
     *
     * @return the configs
     */
    public List<Config> getConfigs() {
        return configs;
    }

    /**
     * Sets configs.
     *
     * @param configs the configs
     */
    public void setConfigs(final List<Config> configs) {
        this.configs = configs;
    }

    /**
     * Gets reservations.
     *
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Sets reservations.
     *
     * @param reservations the reservations
     */
    public void setReservations(final List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
