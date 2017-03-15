package com.intive.toz.financial;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * mvp presenter for financial Activity.
 */
public class Presenter extends MvpBasePresenter<FinancialView> implements MvpPresenter<FinancialView> {

    PetsApi financialService = ApiClient.getPetsApiService();
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
