package com.intive.toz.schedule;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * The interface Schedule mvp.
 */
public interface ScheduleMvp {
    /**
     * The interface View.
     */
    interface View extends MvpView {
        /**
         * Config calendar.
         */
        void configCalendar();
    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends MvpPresenter<View> {

    }
}
