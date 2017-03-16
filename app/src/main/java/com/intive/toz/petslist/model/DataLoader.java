package com.intive.toz.petslist.model;

import com.intive.toz.Pet;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  Model layer of pets list. Used to get data from server using Retrofit.
 */

public class DataLoader implements DataProvider {
    private final PetsApi api = ApiClient.getPetsApiService();

    @Override
    public void fetchPets(final ResponseCallback<List<Pet>> listener) {
        api.getGalleryPetsListCall().enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(final Call<List<Pet>> call, final Response<List<Pet>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<List<Pet>> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }
}