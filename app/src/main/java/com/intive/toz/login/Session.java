package com.intive.toz.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.hawk.Hawk;


/**
 * Class session.
 */
public final class Session {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static final String PASSWORD_KEY = "password";
    private static final String EMAIL_KEY = "email";

    private Session() {
    }

    /**
     * The constructor.
     *
     * @param context the context
     */
    public static void session(final Context context) {
        preferences = context.getSharedPreferences("com.intive.toz", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Hawk.init(context).build();
    }

    /**
     * Return session state.
     *
     * @return state of session
     */
    public static boolean isLogged() {

        return preferences.getBoolean("loggedState", false);
    }

    /**
     * Set session log in.
     *
     * @param jwt                 to keep token in preferences.
     * @param userId              the user id
     * @param role                the role
     * @param tokenExpirationDate the token expiration date
     */
    public static void logIn(final String jwt, final String userId, final String role, final long tokenExpirationDate) {
        editor.putBoolean("loggedState", true);
        editor.putString("jwt", jwt);
        editor.putString("userId", userId);
        editor.putString("role", role);
        editor.putLong("expirationDate", tokenExpirationDate);
        editor.commit();
    }

    /**
     * Store credentials.
     *
     * @param email    the email
     * @param password the password
     */
    public static void storeCredentials(final String email, final String password) {
        Hawk.put(EMAIL_KEY, email);
        Hawk.put(PASSWORD_KEY, password);
    }

    /**
     * Set session log out.
     */
    public static void logOut() {
        editor.putBoolean("loggedState", false);
        editor.putString("jwt", "");
        editor.putString("scope", "");
        editor.putString("userId", "");
        editor.putString("role", "");
        editor.putLong("expirationDate", 0);
        editor.commit();
        Hawk.deleteAll();
    }

    /**
     * get jwt from session.
     *
     * @return String jwt.
     */
    public static String getJwt() {
        return preferences.getString("jwt", "");
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public static String getUserId() {
        return preferences.getString("userId", "");
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public static String getRole() {
        return preferences.getString("role", "");
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    public static Long getExpirationDate() {
        return preferences.getLong("expirationDate", 0);
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public static String getPassword() {
        return Hawk.get(PASSWORD_KEY);
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public static String getEmail() {
        return Hawk.get(EMAIL_KEY);
    }

    /**
     * The enum Types.
     */
    public enum TYPES {
        /**
         * Volunteer types.
         */
        VOLUNTEER,
        /**
         * Toz types.
         */
        TOZ
    }
}
