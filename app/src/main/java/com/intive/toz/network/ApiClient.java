package com.intive.toz.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Base url connection.
 */
public final class ApiClient {

    static final String API_URL = "http://dev.patronage2017.intive-projects.com";

    private ApiClient() {
    }

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * @return pets Api
     */
    public static PetsApi getPetsApiService() {
        return getRetrofitInstance().create(PetsApi.class);
    }

}
