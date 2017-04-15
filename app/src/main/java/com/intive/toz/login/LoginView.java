package com.intive.toz.login;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Interface for communicating in login screen.
 */

public interface LoginView extends MvpView {

    /**
     * Shows error when password view is empty.
     */
    void emptyPasswordViewError();

    /**
     * Shows error when login view is empty.
     */
    void emptyLoginViewError();

    /**
     * Shows login error.
     */
    void showLoginError();

    /**
     * Shows login error.
     */
    void showPasswordError();

    /**
     * Hides all error views.
     */
    void hideErrorViews();

    /**
     * Shows progress bar.
     */
    void showProgress();

    /**
     * Hides progress bar.
     */
    void hideProgress();


    /**
     * On successfull login go to mainactivity.
     */
    void onLoginSuccessful();
}
