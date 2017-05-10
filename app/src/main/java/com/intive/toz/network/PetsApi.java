package com.intive.toz.network;


import com.intive.toz.petslist.model.Pet;
import com.intive.toz.news.model.News;
import com.intive.toz.financial.model.FinancialData;
import com.intive.toz.schedule.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface json file.
 */
public interface PetsApi {
    /**
     * return json array.
     *
     * @return /pets.json.
     */

    @GET("/pets")
    Call<List<Pet>> getGalleryPetsListCall();

    /**
     * Get pet.
     *
     * @param id pet id
     * @return pet details
     */
    @GET("/pets/{id}")
    Call<Pet> getPetDetailsCall(@Path("id") String id);

    /**
     * Gets news.
     *
     * @return the news
     */
    @GET("/news")
    Call<List<News>> getNews();

    /**
     * Call to financial data.
     *
     * @return /financial.json
     */
    @GET("/financial")
    Call<FinancialData> getFinancialInfo();

    /**
     * Get one object of detailed news by Id.
     *
     * @param id id
     * @return /news/{id} json
     */
    @GET("/news/{id}")
    Call<News> getDetailNews(@Path("id") String id);

    /**
     * Gets schedule.
     *
     * @param from the from
     * @param to   the to
     * @return the schedule
     */
    @GET("/schedule")
    Call<List<Schedule>> getSchedule(@Path("from") String from, @Path("to") String to);
}


