package com.intive.toz.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mmate on 06.03.2017.
 */

public class RetroClient {

    static final String PETS_URL = "http://private-dbfe1-zwierzakiandroid.apiary-mock.com";

    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(PETS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static PetsApi getPetsApiService(){
        return getRetrofitInstance().create(PetsApi.class);
    }

}
