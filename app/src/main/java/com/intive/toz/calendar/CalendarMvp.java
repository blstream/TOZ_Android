package com.intive.toz.calendar;

import android.support.v4.app.DialogFragment;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;


/**
 * interface for Presenter and View.
 */

public interface CalendarMvp {

    /**
     * interface to presenter.
     */
    interface Presenter extends MvpPresenter<CalendarView> {
        /**
         * set calendar buttons state.
         */

        void loadData();

        /**
         * check calendar button state.
         * @param view
         */

        void checkButtonState(View view);
    }

    /**
     * interface to view.
     */
    interface CalendarView extends MvpView {

        /**
         * set state of callendar buttons.
         * @param arrayState
         */
        void setCalendarButtons(boolean[] arrayState);


        /**
         *show dialog.
         * @param dialog
         */
        void showDialog(DialogFragment dialog);

        /**
         * set button state.
         *
         * @param id
         */
        void setButton(final View view, final String name);

        /**
         *hide dialog.
         * @param dialog
         */
        void hideDialog(DialogFragment dialog);

        /**
         * show snackbar in calendar activity.
         */

        void showSnackbar();

        /**
         * hide snackbar in calendar activity.
         */

        void hideSnackbar();

    }


}
