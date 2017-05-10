package com.intive.toz.account;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


/**
 * Presenter for ChangePasswordActivity.
 */

public class ChangePasswordPresenter extends MvpBasePresenter<ChangePasswordMVP.ChangePasswordView> {

    private boolean allInputsCorrect;
    PasswordChangeValidator validator = new PasswordChangeValidator();

    void validatePassword(final String oldPassword, final String newPassword,
                          final String repeatedNewPassword) {
        allInputsCorrect = true;
        checkForEmptyFields(oldPassword, newPassword, repeatedNewPassword);
        assertNewPasswordEqualsRepeatedNewPassword(newPassword, repeatedNewPassword);
        checkLengthOfPassword(newPassword);
        validateOldPassword(oldPassword);
        assertOldDifferentThanNew(oldPassword, newPassword);
        //TODO makeRequestToChangePassword - requires backend
       /* if (allInputsCorrect) {
        }*/
    }

    void checkForEmptyFields(final String oldPassword, final String newPassword,
                              final String repeatedNewPassword) {
        if (oldPassword.isEmpty()) {
            getView().showEmptyOldPasswordError();
            allInputsCorrect = false;
        }
        if (newPassword.isEmpty()) {
            getView().showEmptyNewPasswordError();
            allInputsCorrect = false;
        }
        if (repeatedNewPassword.isEmpty()) {
            getView().showEmptyRepeatedNewPasswordError();
            allInputsCorrect = false;
        }
    }

    void assertNewPasswordEqualsRepeatedNewPassword(final String newPassword, final String repeatedNewPassword) {
        if (!newPassword.isEmpty()
                && !repeatedNewPassword.isEmpty()
                && validator.areNewPasswordsDifferent(newPassword, repeatedNewPassword)) {
            allInputsCorrect = false;
            getView().showDifferentNewPasswordsError();
        } else if (!newPassword.isEmpty() && !repeatedNewPassword.isEmpty()) {
            getView().hideDifferentNewPasswordsError();
        }
    }

    void validateOldPassword(final String oldPassword) {
        if (!oldPassword.isEmpty() && !validator.validateOldPassword(oldPassword)) {
            allInputsCorrect = false;
            getView().showWrongPasswordError();
        }
    }

    void assertOldDifferentThanNew(final String oldPassword, final String newPassword) {
        if (!newPassword.isEmpty() && !validator.isNewPasswordDifferentThanOld(oldPassword, newPassword)) {
            allInputsCorrect = false;
            getView().showWrongPasswordError();
        }
    }

    void checkLengthOfPassword(final String newPassword) {
        if (!validator.isLengthOfPasswordValid(newPassword)) {
            allInputsCorrect = false;
            getView().showTooLongPasswordError();
        }
    }
}
