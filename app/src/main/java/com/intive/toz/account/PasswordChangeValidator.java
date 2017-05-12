package com.intive.toz.account;

import java.util.Objects;

/**
 * Class used to validate password change.
 */

class PasswordChangeValidator {

    private static final int MAX_LENGTH = 35;

    boolean isLengthOfPasswordValid(final String password) {
        return password.length() <= MAX_LENGTH;
    }

    /**
     * Checks if new password and repeated new password are different.
     * @param newPassword new password
     * @param repeatNewPassword  repeated new password
     * @return true if both passwords are different.
     */
    boolean areNewPasswordsDifferent(final String newPassword, final String repeatNewPassword) {
        return !Objects.equals(newPassword, repeatNewPassword);
    }

    /**
     * Checks if new password is equal to old one.
     * @param oldPassword user's old(current) password.
     * @param newPassword user's new password
     * @return true if new password is equal to old one.
     */
    boolean assertNewPasswordEqualsOld(final String oldPassword, final String newPassword) {
        return Objects.equals(oldPassword, newPassword);
    }
}
