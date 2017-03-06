package com.intive.toz.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Base url connection.
 */
public final class RetroClient {

    static final String PETS_URL = "http://private-dbfe1-zwierzakiandroid.apiary-mock.com";

    private RetroClient() {

    }

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(PETS_URL)
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
