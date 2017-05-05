package com.intive.toz.info.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.info.FinancialMvp;
import com.intive.toz.info.model.info;
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
        getView().showProgres();
        Call call = financialService.getFinancialInfo();
        call.enqueue(new Callback<info>() {
            @Override
            public void onResponse(final Call<info> call, final Response<info> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    getView().setFinancialData(response.body());
                    getView().hideProgres();
                }
            }

            @Override
            public void onFailure(final Call<info> call, final Throwable t) {
                getView().hideProgres();
                getView().showError();
                Log.e("RESPONSE", "onFailure: ");
            }
        });
    }
}
