package com.intive.toz.data;

import com.intive.toz.Pet;
import com.intive.toz.news.model.News;

import java.util.List;

/**
 * The interface Data provider.
 */
public interface DataProvider {
    /**
     * Fetch news.
     *
     * @param listener the listener
     */
    void fetchNews(ResponseCallback<List<News>> listener);

    /**
     *  Fetch pets.
     *
     * @param listener the listener
     */
    void fetchPets(ResponseCallback<List<Pet>> listener);

    /**
     * Fetch pet.
     *
     * @param petID  pet id
     * @param listener listener
     */
    void fetchPetDetails(String petID, ResponseCallback<Pet> listener);

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
