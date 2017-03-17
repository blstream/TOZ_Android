package com.intive.toz.network;


import com.intive.toz.Pet;
import com.intive.toz.news.model.News;
import com.intive.toz.financial.model.FinancialData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

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
     * Gets news.
     *
     * @return the news
     */
    @GET("/news")
    Call<List<News>> getNews();

    /**
     * Call to financial data.
     * @return /financial.json
     */
    @GET("/financial")
    Call<FinancialData> getFinancialInfo();
}


