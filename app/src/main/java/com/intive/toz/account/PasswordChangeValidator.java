package com.intive.toz.account;

import java.util.Objects;

/**
 * Class used to validate password change.
 */

class PasswordChangeValidator {

    boolean isLengthOfPasswordValid(final String password) {
        return password.length() > 0 && password.length() <= 35;
    }

    /**
     * Checks if new password and repeated new password are different.
     * @param newPassword new password
     * @param repeatNewPassword  repeated new password
     * @return true if both passwords are different.
     */
    boolean areNewPasswordsDifferent (final String newPassword, final String repeatNewPassword) {
        return !Objects.equals(newPassword, repeatNewPassword);
    }

    /**
     * Checks if old password given is really user's old(current) password.
     * @param oldPassword users' old password
     * @return true if old password given is user's old(current) password.
     */
    boolean validateOldPassword(final String oldPassword) {
        //TODO requires backend to check old password
        return true;
    }

    /**
     * Checks if new password is different than old.
     * @param oldPassword user's old(current) password.
     * @param newPassword user's new password
     * @return true if new password is different.
     */
    boolean isNewPasswordDifferentThanOld(final String oldPassword, final String newPassword) {
        //TODO requires backend to check old password
        return true;
    }
}
