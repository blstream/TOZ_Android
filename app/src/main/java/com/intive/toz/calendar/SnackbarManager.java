package com.intive.toz.calendar;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

/**
 * class to create Snackbar.
 */

public final class SnackbarManager {


    private SnackbarManager() {

    }

    /**
     * return snackbar on activity.
     *
     * @return snackbar
     * @param  activity
     * @param text
     */
    public static Snackbar getSnackbar(final Activity activity, final String text) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        return snackbar;
    }

}
