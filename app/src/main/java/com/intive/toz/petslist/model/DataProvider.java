package com.intive.toz.petslist.model;

import com.intive.toz.Pet;

import java.util.List;

/**
 * The interface Data provider.
 */
public interface DataProvider {
    /**
     * Fetch pets.
     *
     * @param listener the listener
     */
    void fetchPets(com.intive.toz.petslist.model.DataProvider.ResponseCallback<List<Pet>> listener);

    /**
     * The interface On data received listener.
     *
     * @param <T> the type parameter
     */
    interface ResponseCallback<T> {
        /**
         * On success.
         *
         * @param response the response
         */
        void onSuccess(T response);

        /**
         * On error.
         *
         * @param e the e
         */
        void onError(Throwable e);
    }
}