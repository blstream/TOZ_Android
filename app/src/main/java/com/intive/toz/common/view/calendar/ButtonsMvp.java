package com.intive.toz.common.view.calendar;

import android.support.v4.app.DialogFragment;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.Date;
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
         * check calendar date state.
         *
         * @param position the position
         * @param day
         */
        void checkDate(int position, Date day);

        /**
         * set calendar date state.
         *
         * @param position the position
         */
        void setDate(int position, boolean save, boolean delete);

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
         * show snackbar in calendar activity.
         */
        void showSnackbar();


    }


}
