package com.intive.toz.financial;

import android.util.Log;

import com.intive.toz.network.PetsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * mvp presenter for financial Activity.
 */
public class FinancialPresenter implements IFinancialPresenter{

    private final FinancialView view;

    public FinancialPresenter(FinancialView view) {
        this.view = view;
    }

    /**
     * make callback from server.
     */
    public void loadFinancialData(PetsApi financialService) {
        view.showProgres();

        Call call = financialService.getFinancialJSON();
        call.enqueue(new Callback<FinancialData>() {
            @Override
            public void onResponse(final Call<FinancialData> call, final Response<FinancialData> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    view.setFinancialData(response.body());
                    view.hideProgres();
                }
            }

            @Override
            public void onFailure(final Call<FinancialData> call, final Throwable t) {
                view.hideProgres();
                view.showError();
                Log.e("RESPONSE", "onFailure: ");
            }
        });
    }
}
