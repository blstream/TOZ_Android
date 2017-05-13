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
        void hideOldPasswordError();
        void hideNewPasswordError();
        void hideRepeatedNewPasswordError();
        void showWrongOldPasswordError();
        void showWrongNewPasswordError();
        void showTooLongPasswordError();
        void showError();
        void showErrorCode(int errorMessage);
        void showSuccessfulPasswordChange();

    }
}
