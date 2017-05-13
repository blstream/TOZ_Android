package com.intive.toz.common.view.calendar.dialogs;


import android.text.format.DateFormat;

import java.util.Date;

/**
 * class to create type of dialogs.
 */
public final class DialogFactory {

    /**
     * The constant day.
     */
    public static Date day;
    /**
     * The constant position.
     */
    public static int position;
    /**
     * The constant isMorning.
     */
    public static boolean isMorning;
    /**
     * The constant week.
     */
    public static int week;

    /**
     * class constructor.
     */
    private DialogFactory() {
    }

    /**
     * New instance information dialog fragment.
     *
     * @param name the name
     * @return dialog model dialog
     */
    public static ModelDialog infoDialog(final String name) {
        ModelDialog dialog = ModelDialog.newInstance(1);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
        dialog.setUserName(name);
        dialog.setDay(day);
        return dialog;
    }

    /**
     * New instance delete dialog fragment.
     *
     * @param name     the name
     * @param id       the id
     * @param listener the listener
     * @return dialog model dialog
     */
    public static ModelDialog deleteDialog(final String name, final String id, final OnReservationChangeListener listener) {
        ModelDialog dialog = ModelDialog.newInstance(2);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
        dialog.setUserName(name);
        dialog.setDay(day);
        dialog.setId(id);
        dialog.setListener(listener);
        return dialog;
    }

    /**
     * New instance save dialog fragment.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param listener  the listener
     * @return dialog model dialog
     */
    public static ModelDialog saveDialog(final String startDate, final String endDate, final OnReservationChangeListener listener) {
        ModelDialog dialog = ModelDialog.newInstance(0);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
        dialog.setStartDate(startDate);
        dialog.setEndDate(endDate);
        dialog.setDay(day);
        dialog.setListener(listener);
        return dialog;
    }

    /**
     * Title dialog fragment.
     *
     * @return date title
     */
    private static String getTitleDate() {
        return DateFormat.format("EEEEE", day).toString()
                + DateFormat.format("EEEE", day).toString().substring(1) + ", "
                + DateFormat.format("d", day).toString() + " "
                + DateFormat.format("MMMM", day).toString();
    }

    /**
     * Title dialog fragment.
     *
     * @return title title
     */
    public static String getTitle() {

        if (isMorning) {
            return "Rano";
        } else {
            return "Popołudnie";
        }
    }
}
