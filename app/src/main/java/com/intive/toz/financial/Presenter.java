package com.intive.toz.financial;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.intive.toz.Retrofit.PetsApi;
import com.intive.toz.Retrofit.RetroClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * mvp presenter for financial Activity.
 */
public class Presenter extends MvpBasePresenter<FinancialView> {

    PetsApi financialService = RetroClient.getPetsApiService();
    Call call = financialService.getFinancialJSON();

    /**
     * make callback from server.
     */
    public void loadFinancialData() {
        call.enqueue(new Callback<FinancialData>() {
            @Override
            public void onResponse(final Call<FinancialData> call, final Response<FinancialData> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    getView().showProgres();
                    getView().setFinancialData(response.body());
                    getView().hideProgres();
                }
            }

            @Override
            public void onFailure(final Call<FinancialData> call, final Throwable t) {
                getView().hideProgres();
                getView().showError();
                Log.e("RESPONSE", "onFailure: ");
            }
        });
    }
}
