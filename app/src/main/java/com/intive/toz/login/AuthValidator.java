package com.intive.toz.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Class for authenticating user login and password.
 */
public class AuthValidator {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private boolean isLoginValid;
    private boolean isPasswordValid;
    /**
     * Method for validating login (must be in format [identyfikator]@[domena]).
     * @param login login
     * @return true if login is valid
     */
    public boolean validateLogin(final String login) {
        isLoginValid = android.util.Patterns.EMAIL_ADDRESS.matcher(login).matches();
        return isLoginValid;
    }

    /**
     * Method for validating password (right now checks
     * if it is longer than 8 characters, no whitespace).
     * @param password password
     * @return true if password is valid
     */
    public boolean validatePassword(final String password) {
        final String passwordPattern = "^(?=\\S+$).{" + MIN_PASSWORD_LENGTH + ",}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        isPasswordValid = matcher.matches();
        return isPasswordValid;
    }

    /**
     *  Method checking if login and password are valid.
     * @return true if login and password are valid.
     */
    public boolean isAllValid() {
        return isLoginValid && isPasswordValid;
    }
}
