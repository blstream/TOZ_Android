package com.intive.toz.common.view.calendar.dialogs;


import android.text.format.DateFormat;

import java.util.Date;

/**
 * class to create type of dialogs.
 */
public final class DialogFactory {

    public static Date day;
    public static int position;

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
        ModelDialog dialog = ModelDialog.newInstance(1, position);
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
        ModelDialog dialog = ModelDialog.newInstance(2, position);
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
        ModelDialog dialog = ModelDialog.newInstance(0, position);
        dialog.setTitle(DialogFactory.getTitle());
        dialog.setDate(DialogFactory.getTitleDate());
        return dialog;
    }

    /**
     * Title dialog fragment.
     *
     * @return date title
     */
    public static String getTitleDate() {
        return DateFormat.format("EEEEE", day).toString() + DateFormat.format("EEEE", day).toString().substring(1)
                + ", " + DateFormat.format("d", day).toString() + " " + DateFormat.format("MMMM", day).toString();
    }

    /**
     * Title dialog fragment.
     *
     * @return title
     */
    public static String getTitle() {
        final int morningButtons = 7;
        if (position < morningButtons) {
            return "Rano";
        } else {
            return "Popołudnie";
        }
    }
}
