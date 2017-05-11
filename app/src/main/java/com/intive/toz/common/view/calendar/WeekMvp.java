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
         *
         * @param week the week
         */
        void loadData(int week);


        /**
         * check calendar date state.
         *
         * @param position  the position
         * @param day       the day
         * @param week      the week
         * @param isMorning the is morning
         */
        void checkDate(int position, Date day, int week, boolean isMorning);

        /**
         * set calendar date state.
         *
         * @param date      the date
         * @param week      the week
         * @param isSaved   the is saved
         * @param isMorning the is morning
         */
        void setDate(String date, int week, boolean isSaved, boolean isMorning);

        /**
         * Fetch schedule.
         *
         * @param from the from
         * @param to   the to
         */
        void fetchSchedule(String from, String to);

    }

    /**
     * interface to view.
     */
    interface ButtonsView extends MvpView {


        /**
         * Sets schedule.
         *
         * @param schedule the schedule
         */
        void setSchedule(Schedule schedule);


        /**
         * Refresh schedule.
         */
        void refreshSchedule();


        /**
         * show dialog.
         *
         * @param dialog the dialog
         */
        void showDialog(DialogFragment dialog);


        /**
         * show snackbar in calendar activity.
         */
        void showSnackbar();


    }


}
