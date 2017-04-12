package com.intive.toz.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Network state receiver.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    /**
     * The Listeners.
     */
    protected final List<NetworkStateListener> listeners;
    /**
     * The Connected.
     */
    protected Boolean connected;

    /**
     * Instantiates a new Network state receiver.
     */
    public NetworkStateReceiver() {
        listeners = new ArrayList<>();
        connected = null;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        connected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        notifyStateToAll();
    }

    private void notifyStateToAll() {
        for (NetworkStateListener listener : listeners) {
            notifyState(listener);
        }
    }

    private void notifyState(final NetworkStateListener listener) {
        if (connected == null || listener == null) {
            return;
        }

        if (connected) {
            listener.networkAvailable();
        } else {
            listener.networkUnavailable();
        }
    }

    /**
     * Add listener.
     *
     * @param l the l
     */
    public void addListener(final NetworkStateListener l) {
        listeners.add(l);
        notifyState(l);
    }

    /**
     * Remove listener.
     *
     * @param l the l
     */
    public void removeListener(final NetworkStateListener l) {
        listeners.remove(l);
    }

    /**
     * The interface Network state listener.
     */
    public interface NetworkStateListener {
        /**
         * Network available.
         */
        void networkAvailable();

        /**
         * Network unavailable.
         */
        void networkUnavailable();
    }
}
