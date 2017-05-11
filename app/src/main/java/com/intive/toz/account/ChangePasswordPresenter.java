package com.intive.toz.account;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.account.model.ResponseMessage;
import com.intive.toz.account.model.UserPassword;
import com.intive.toz.data.DataLoader;
import com.intive.toz.data.DataProvider;


/**
 * Presenter for ChangePasswordActivity.
 */

public class ChangePasswordPresenter extends MvpBasePresenter<ChangePasswordMVP.ChangePasswordView> {

    private boolean allInputsCorrect;
    PasswordChangeValidator validator = new PasswordChangeValidator();
    DataLoader dataLoader = new DataLoader();

    void validatePassword(final String oldPassword, final String newPassword,
                          final String repeatedNewPassword) {
        allInputsCorrect = true;
        checkForEmptyFields(oldPassword, newPassword, repeatedNewPassword);
        assertNewPasswordEqualsRepeatedNewPassword(newPassword, repeatedNewPassword);
        checkLengthOfPassword(newPassword);
        if (allInputsCorrect) {
            UserPassword userPassword = new UserPassword();
            userPassword.setOldPassword(oldPassword);
            userPassword.setNewPassword(newPassword);
            dataLoader.requestPasswordChange(new DataProvider.ResponseChangePasswordCallback<ResponseMessage>() {
                @Override
                public void onSuccess(final ResponseMessage response) {
                    getView().showSuccessfulPasswordChange();
                }

                @Override
                public void onError(final Throwable e) {
                    getView().showError();
                }

                @Override
                public void onWrongPassword() {
                    if (validator.assertNewPasswordEqualsOld(oldPassword, newPassword)) {
                        getView().showWrongNewPasswordError();
                    } else {
                        getView().showWrongOldPasswordError();
                    }

                }

                @Override
                public void onErrorCode(final int errorMessage) {
                    getView().showErrorCode(errorMessage);
                }
            }, userPassword);
        }
    }

    void checkForEmptyFields(final String oldPassword, final String newPassword,
                              final String repeatedNewPassword) {
        if (oldPassword.isEmpty()) {
            getView().showEmptyOldPasswordError();
            allInputsCorrect = false;
        } else {
            getView().hideOldPasswordError();
        }
        if (newPassword.isEmpty()) {
            getView().showEmptyNewPasswordError();
            allInputsCorrect = false;
        } else {
            getView().hideNewPasswordError();
        }
        if (repeatedNewPassword.isEmpty()) {
            getView().showEmptyRepeatedNewPasswordError();
            allInputsCorrect = false;
        } else {
            getView().hideRepeatedNewPasswordError();
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

    void checkLengthOfPassword(final String newPassword) {
        if (!validator.isLengthOfPasswordValid(newPassword)) {
            allInputsCorrect = false;
            getView().showTooLongPasswordError();
        }
    }
}
