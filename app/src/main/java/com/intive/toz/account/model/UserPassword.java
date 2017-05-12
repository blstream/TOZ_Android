package com.intive.toz.account.model;

/**
 * Model class for changing password.
 */
public class UserPassword {

    private String oldPassword;
    private String newPassword;

    /**
     * Set old password.
     * @param oldPassword user's old password(current).
     */
    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Set new password.
     * @param newPassword user's new password.
     */
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }
}
