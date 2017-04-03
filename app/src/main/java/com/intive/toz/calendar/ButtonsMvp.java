package com.intive.toz.calendar;

import android.support.v4.app.DialogFragment;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;


/**
 * interface for Presenter and ButtonsView.
 */

public interface ButtonsMvp {

    /**
     * interface to presenter.
     */
    interface Presenter extends MvpPresenter<ButtonsView> {
        /**
         * set calendar buttons state.
         */

        void loadData();


        /**
         * check calendar buttons state.
         */


        void checkButton(int position, View view, boolean isMorning);

    }

    /**
     * interface to view.
     */
    interface ButtonsView extends MvpView {

        /**
         * set state of callendar buttons.
         *
         * @param afternoon data afternoon
         * @param morning   data morning
         */

        void setButtons(List<Integer> afternoon, List<Integer> morning);

        /**
         * show dialog.
         *
         * @param dialog
         */
        void showDialog(DialogFragment dialog);

        /**
         * set button state.
         *
         * @param view view
         * @param name set name
         */
        void setButton(final android.view.View view, final String name);

        /**
         * hide dialog.
         *
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
