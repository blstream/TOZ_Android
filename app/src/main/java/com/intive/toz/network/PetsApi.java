package com.intive.toz.network;


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
    Call<PetsList> getJSON();

}


