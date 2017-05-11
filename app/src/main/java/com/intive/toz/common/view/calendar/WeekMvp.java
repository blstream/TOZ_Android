package com.intive.toz.common.view.calendar;

import android.support.v4.app.DialogFragment;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.intive.toz.schedule.model.Schedule;

import java.util.Date;


/**
 * interface for Presenter and ButtonsView.
 */

public interface WeekMvp {

    /**
     * interface to presenter.
     */
    interface Presenter extends MvpPresenter<ButtonsView> {

        /**
         * set calendar buttons state.
         */
        void loadData(int week);


        /**
         * check calendar date state.
         *
         * @param position the position
         * @param day
         */
        void checkDate(int position, Date day, int week, boolean isMorning);

        /**
         * set calendar date state.
         *
         * @param date the date
         */
        void setDate(String date, int week, boolean isSaved, boolean isMorning);

        void fetchSchedule(String from, String to);

    }

    /**
     * interface to view.
     */
    interface ButtonsView extends MvpView {


        void setSchedule(Schedule schedule);


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
