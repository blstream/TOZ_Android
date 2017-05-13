package com.intive.toz.schedule.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Config.
 */
public class Config {

    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("numberOfPeriods")
    @Expose
    private Integer numberOfPeriods;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = null;

    /**
     * Gets day of week.
     *
     * @return the day of week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets day of week.
     *
     * @param dayOfWeek the day of week
     */
    public void setDayOfWeek(final String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Gets number of periods.
     *
     * @return the number of periods
     */
    public Integer getNumberOfPeriods() {
        return numberOfPeriods;
    }

    /**
     * Sets number of periods.
     *
     * @param numberOfPeriods the number of periods
     */
    public void setNumberOfPeriods(final Integer numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }

    /**
     * Gets periods.
     *
     * @return the periods
     */
    public List<Period> getPeriods() {
        return periods;
    }

    /**
     * Sets periods.
     *
     * @param periods the periods
     */
    public void setPeriods(final List<Period> periods) {
        this.periods = periods;
    }

}
