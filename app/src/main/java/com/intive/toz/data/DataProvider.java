package com.intive.toz.data;

import com.intive.toz.petslist.model.Pet;
import com.intive.toz.news.model.News;
import com.intive.toz.schedule.model.Schedule;

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
     * Fetch pets.
     *
     * @param listener the listener
     */
    void fetchPets(ResponseCallback<List<Pet>> listener);

    /**
     * Fetch just one object of News.
     *
     * @param listener the listener
     * @param id       id
     */
    void fetchDetailNews(ResponseCallback<News> listener, String id);

    /**
     * Fetch pet.
     *
     * @param petID    pet id
     * @param listener listener
     */
    void fetchPetDetails(String petID, ResponseCallback<Pet> listener);

    /**
     * Fetch schedule.
     *
     * @param listener the listener
     * @param from     the from
     * @param to       the to
     */
    void fetchSchedule(ResponseCallback<List<Schedule>> listener, String from, String to);

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
