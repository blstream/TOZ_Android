package com.intive.toz.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Class for checking whether device is connected to internet.
 */

public class NetworkState {

    private Context context;

    /**
     * Constructor.
     * @param context context
     */
    public NetworkState(Context context) {
        this.context = context;
    }

    /**
     *  Method checking wheter device is connected to internet.
     * @return
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
