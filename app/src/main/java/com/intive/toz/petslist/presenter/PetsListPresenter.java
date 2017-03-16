package com.intive.toz.homescreen.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.Pet;
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

    private PetsApi petsApi;

    /**
     *  Sends a request to the server using retrofit. On success invokes a method displaying
     *  loaded data.
     *
     */
    public void loadPetsList(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        petsApi = ApiClient.getPetsApiService();
        final Call<List<Pet>> call = petsApi.getGalleryPetsListCall();

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(final Call<List<Pet>> call, final Response<List<Pet>> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    getView().setData(response.body());
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(final Call<List<Pet>> call, final Throwable t) {
                Log.e("RESPONSE", "onFailure: ");
                getView().showError(t, pullToRefresh);
            }
        });
    }
}
