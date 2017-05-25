package com.intive.toz.reset_password;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * The interface Reset password mvp.
 */
public interface ResetPasswordMvp {
    /**
     * The interface View.
     */
    interface View extends MvpView {
        /**
         * On success.
         */
        void onSuccess();

        /**
         * On error.
         */
        void onError();

        /**
         * On validation error.
         */
        void onValidationError();

        /**
         * Show progress bar.
         *
         * @param show the show
         */
        void showProgressBar(int show);
    }

    /**
     * The interface Presenter.
     */
    interface Presenter extends MvpPresenter<View> {
        /**
         * Reset password.
         *
         * @param email the email
         */
        void resetPassword(String email);
    }
}
