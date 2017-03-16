package com.intive.toz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.intive.toz.financial.view.FinancialActivity;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FIXME Set proper name when implemented.
 * Main app screen.
 */
public class MainActivity extends AppCompatActivity {

    List<Pet> petsList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /**
         *
         * Example of use retrofit.
         *
         *
         * */
        petsList = new ArrayList<>();
        final PetsApi petsApi = ApiClient.getPetsApiService();
        final Call<List<Pet>> call = petsApi.getGalleryPetsListCall();


        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(final Call<List<Pet>> call, final Response<List<Pet>> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    petsList = response.body();
                    for (Pet p : petsList) {
                        Log.e("Lista ", "imie " + p.getName());

                    }
                }
            }

            @Override
            public void onFailure(final Call<List<Pet>> call, final Throwable t) {
                Log.e("RESPONSE", "onFailure: ");

            }
        });

    }

    @OnClick(R.id.btn_tmp_finance)
    public void showFinance(View view) {
        Intent i = new Intent(this, FinancialActivity.class);
        startActivity(i);
    }
}
