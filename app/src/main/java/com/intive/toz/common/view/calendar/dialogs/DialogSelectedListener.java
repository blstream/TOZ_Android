package com.intive.toz.common.view.calendar.dialogs;

/**
 * Created by mmate on 08.04.2017.
 */

public interface DialogSelectedListener {

    /**
     * Get dialog choice.
     *
     * @param position the position
     */
    void onSaveDateSelected(int position);

    /**
     * Get dialog choice.
     *
     * @param position the position
     */
    void onDeleteDateSelected(int position);
}
