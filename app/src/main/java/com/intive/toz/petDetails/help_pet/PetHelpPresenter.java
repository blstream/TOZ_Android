package com.intive.toz.petDetails.help_pet;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.PetsApi;
import com.intive.toz.petDetails.model.Help;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Presenter for Pet Help fragment.
 */

class PetHelpPresenter extends MvpBasePresenter<HelpPetMvp.HelpPetView> implements HelpPetMvp.Presenter{

    public void loadFinancialData(final PetsApi financialService) {
        getView().showProgress();
        Call<Info> call = financialService.getFinancialInfo();
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(final Call<Info> call, final Response<Info> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    getView().setFinancialData(response.body());
                    getView().hideProgress();
                }
            }

            @Override
            public void onFailure(final Call<Info> call, final Throwable t) {
                getView().hideProgress();
                getView().showError(t);
                Log.e("RESPONSE", "onFailure: ");
            }
        });
    }

    @Override
    public void loadHowToDonateData(PetsApi donateService) {
        getView().showProgress();
        Call<Help> call = donateService.getDonateInfo();
        call.enqueue(new Callback<Help>() {
            @Override
            public void onResponse(final Call<Help> call, final Response<Help> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    getView().setDonateInfo(response.body());
                    getView().hideProgress();
                }
            }

            @Override
            public void onFailure(final Call<Help> call, final Throwable t) {
                getView().hideProgress();
                getView().showError(t);
                Log.e("RESPONSE", "onFailure: ");
            }
        });
    }
}
