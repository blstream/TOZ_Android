package com.intive.toz.common.view.calendar.model;

/**
 * Class to create reserved day.
 */
public class ReservedDay {

    private String userNameMorning;
    private String userNameAfternoon;
    private int stateMorning;
    private int stateAfternoon;
    private String date;

    /**
     * Base constructor.
     */
    ReservedDay() {
        this.userNameMorning = "XX";
        this.userNameAfternoon = "XX";
        this.stateMorning = 0;
        this.date = "";
        this.stateAfternoon = 0;
    }

    /**
     * Get user name afternoon.
     *
     * @return user name afternoon
     */
    public String getUserNameAfternoon() {
        return userNameAfternoon;
    }

    /**
     * Get user name morning.
     *
     * @return user name morning
     */
    public String getUserNameMorning() {
        return userNameMorning;
    }

    /**
     * Get state morning.
     *
     * @return state morning
     */
    public int getStateMorning() {
        return stateMorning;
    }

    /**
     * Set state morning.
     *
     * @param stateMorning the state morning
     */
    public void setStateMorning(final int stateMorning) {
        this.stateMorning = stateMorning;
    }

    /**
     * Get state afternoon.
     *
     * @return state afternoon
     */
    public int getStateAfternoon() {
        return stateAfternoon;
    }

    /**
     * Set state afternoon.
     *
     * @param stateAfternoon the state afternoon
     */
    public void setStateAfternoon(final int stateAfternoon) {
        this.stateAfternoon = stateAfternoon;
    }

    /**
     * Get date.
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set date.
     *
     * @param date the date
     */
    void setDate(final String date) {
        this.date = date;
    }


}
