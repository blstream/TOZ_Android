package com.intive.toz.info.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.info.FinancialMvp;
import com.intive.toz.info.model.Help;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.PetsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * mvp presenter for financial Activity.
 */
public class FinancialPresenter extends MvpBasePresenter<FinancialMvp.FinancialView> implements FinancialMvp.Presenter {

    /**
     * make callback from server.
     * @param financialService initialized in fragment.
     */
    public void loadFinancialData(final PetsApi financialService) {
        getView().showProgress();
        Call<Info> call = financialService.getFinancialInfo();
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(final Call<Info> call, final Response<Info> response) {
                if (response.isSuccessful()) {
                    getView().setFinancialData(response.body());
                    getView().hideProgress();
                }
            }

            @Override
            public void onFailure(final Call<Info> call, final Throwable t) {
                getView().hideProgress();
                getView().showError();
            }
        });
    }

    @Override
    public void loadHowToDonateData(final PetsApi donateService) {
        getView().showProgress();
        Call<Help> call = donateService.getDonateInfo();
        call.enqueue(new Callback<Help>() {
            @Override
            public void onResponse(final Call<Help> call, final Response<Help> response) {
                if (response.isSuccessful()) {
                    getView().setDonateInfo(response.body());
                    getView().hideProgress();
                }
            }

            @Override
            public void onFailure(final Call<Help> call, final Throwable t) {
                getView().hideProgress();
                getView().showError();
            }
        });
    }
}
