package com.intive.toz.common.view.calendar.dialogs;

/**
 * Created by mmate on 08.04.2017.
 */

public interface DialogSelectedListener {

    /**
     * Get dialog choice.
     *
     * @param isMorning the is morning
     * @param week      the week
     * @param date      the date
     */
    void onSaveDateSelected(String date, int week, boolean isMorning);

    /**
     * Get dialog choice.
     *
     * @param isMorning the is morning
     * @param week      the week
     * @param date      the date
     */
    void onDeleteDateSelected(String date, int week, boolean isMorning);
}
