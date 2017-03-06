package com.intive.toz.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by mmate on 05.03.2017.
 */

public interface PetsApi {
    @GET("/pets")
     Call<PetsList> getMyJSON();
}
