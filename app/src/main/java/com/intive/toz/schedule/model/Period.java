package com.intive.toz.schedule.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Period.
 */
public class Period {

    @SerializedName("periodEnd")
    @Expose
    private String periodEnd;
    @SerializedName("periodStart")
    @Expose
    private String periodStart;

    /**
     * Gets period end.
     *
     * @return the period end
     */
    public String getPeriodEnd() {
        return periodEnd;
    }

    /**
     * Sets period end.
     *
     * @param periodEnd the period end
     */
    public void setPeriodEnd(final String periodEnd) {
        this.periodEnd = periodEnd;
    }

    /**
     * Gets period start.
     *
     * @return the period start
     */
    public String getPeriodStart() {
        return periodStart;
    }

    /**
     * Sets period start.
     *
     * @param periodStart the period start
     */
    public void setPeriodStart(final String periodStart) {
        this.periodStart = periodStart;
    }

}