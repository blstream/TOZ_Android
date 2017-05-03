package com.intive.toz.form;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class for Validate form.
 */
class ValidateForm {
    private Pattern pattern;
    private Matcher matcher;
    private static final  String NAME_PATTERN = "^[ [a-zA-Z][żźćńółęąśŻŹĆĄŚĘŁÓŃ]?]{1,35}$";
    private static final  String EMAIL_PATTERN = "^[[0-9][_.-]]?$";
    private static final  String PHONE_PATTERN = "^[0-9]{9,11}$";

    /**
     * Class constructor.
     */
    ValidateForm() {
    }

    /**
     * Function to validate name and surname.
     *
     * @param name is name
     * @return state of validate
     */
    public boolean name(final String name) {
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * Function to validate email.
     *
     * @param email is email
     * @return state of validate
     */
    boolean email(final String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email.substring(0, 1));
            return !matcher.matches();
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    /**
     * Function to validate phone number.
     *
     * @param number is number
     * @return state of validate
     */
    boolean phoneNumber(final String number) {
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
