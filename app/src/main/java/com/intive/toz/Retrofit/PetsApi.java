package com.intive.toz.Retrofit;

import com.intive.toz.financial.FinancialData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface json file.
 */

public interface PetsApi {
    /**
     * return json array.
     * @return /pets.json.
     */

    @GET("/pets")
    Call<PetsList> getMyJSON();

    @GET("/financial")
    Call<FinancialData> getFinancialJSON();

}


