package com.intive.toz;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.intive.toz.login.LoginActivity;
import com.intive.toz.login.Session;

/**
 * The type Toz application.
 */
public class TozApplication extends Application {
    private static TozApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Session.session(getApplicationContext());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TozApplication getInstance() {
        return instance;
    }

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * On token refresh error.
     */
    public void onTokenRefreshError() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Session.logOut();
    }
}
