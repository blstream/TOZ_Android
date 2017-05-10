package com.intive.toz.account;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Interface for MVP.
 */

public interface ChangePasswordMVP {

    /**
     * Interface for communication between View - Presenter.
     */
    interface ChangePasswordView extends MvpView {
        void showEmptyOldPasswordError();
        void showEmptyNewPasswordError();
        void showEmptyRepeatedNewPasswordError();
        void showDifferentNewPasswordsError();
        void hideDifferentNewPasswordsError();
        void showWrongPasswordError();
        void showTooLongPasswordError();
    }
}
