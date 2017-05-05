package com.intive.toz.login;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Class session.
 */
public final class Session {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

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
     */
    public static void logIn(String jwt) {
        editor.putBoolean("loggedState", true);
        editor.putString("jwt", jwt);
        editor.commit();
    }

    /**
     * Set session log out.
     */
    public static void logOut() {
        editor.putBoolean("loggedState", false);
        editor.putString("jwt", "");
        editor.commit();
    }

    /**
     * get jwt from session.
     * @return String jwt.
     */
    public static String getJwt() {
        return preferences.getString("jwt", "");
    }
}
