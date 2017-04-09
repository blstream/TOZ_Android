package com.intive.toz.common.view.calendar;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

/**
 * class to create Snackbar.
 */

public final class SnackbarFactory {


    private SnackbarFactory() {

    }

    /**
     * return snackbar on activity.
     *
     * @return snackbar
     * @param  activity the activity
     * @param text the text
     */
    public static Snackbar getSnackbar(final Activity activity, final String text) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        return snackbar;
    }

}
