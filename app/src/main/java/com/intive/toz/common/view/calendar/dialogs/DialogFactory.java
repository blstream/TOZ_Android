package com.intive.toz.common.view.calendar.dialogs;


import android.text.format.DateFormat;

import java.util.Date;

/**
 * class to create type of dialogs.
 */
public final class DialogFactory {

    public static Date day;
    public static int position;
    public static boolean isMorning;
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
     * @return dialog
     */
    public static ModelDialog infoDialog(final String name) {
        ModelDialog dialog = ModelDialog.newInstance(1);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
        dialog.setUserName(name);
        return dialog;
    }

    /**
     * New instance delete dialog fragment.
     *
     * @param name the name
     * @return dialog
     */
    public static ModelDialog deleteDialog(final String name) {
        ModelDialog dialog = ModelDialog.newInstance(2);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
        dialog.setUserName(name);
        return dialog;
    }

    /**
     * New instance save dialog fragment.
     *
     * @return dialog
     */
    public static ModelDialog saveDialog() {
        ModelDialog dialog = ModelDialog.newInstance(0);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
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
     * Get date.
     *
     * @param day the day
     * @return date string
     */
    private static String getDate(final Date day) {
        return DateFormat.format("dd", day).toString()
                + DateFormat.format("MM", day).toString()
                + DateFormat.format("yy", day).toString();
    }

    /**
     * Title dialog fragment.
     *
     * @return title
     */
    public static String getTitle() {

        if (isMorning) {
            return "Rano";
        } else {
            return "Popołudnie";
        }
    }
}
