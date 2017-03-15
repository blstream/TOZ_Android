package com.intive.toz.homescreen.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.intive.toz.Pet;
import com.intive.toz.homescreen.model.Pets;
import com.intive.toz.homescreen.view.PetsListView;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  Presenter for pets list.
 */

public class PetsListPresenter extends MvpBasePresenter<PetsListView> {
    //call business logic to get data from server

    private PetsApi petsApi;

    /**
     *  Sends a request to the server using retrofit. On success invokes a method displaying
     *  loaded data.
     */
    public void loadData() {
        getView().showProgress();
        petsApi = ApiClient.getPetsApiService();
        final Call<List<Pet>> call = petsApi.getGalleryPetsListCall();

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(final Call<List<Pet>> call, final Response<List<Pet>> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    getView().hideProgress();
                    getView().showPetsList(response.body());
                } else {
                    getView().showError();
                }
            }

            @Override
            public void onFailure(final Call<List<Pet>> call, final Throwable t) {
                Log.e("RESPONSE", "onFailure: ");
                getView().showError();
            }
        });
    }
}
