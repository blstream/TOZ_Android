package com.intive.toz.login.model;

/**
 * Created by Krzysiek on 2017-04-29.
 */

/**
 * required object model to send on server while login.
 */
public class Login {

    private String email;
    private String password;

    /**
     * set email taking from input screen.
     *
     * @param email email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * set password taking from input screen.
     *
     * @param password password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
