package com.intive.toz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.intive.toz.financial.view.FinancialActivity;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;
import com.intive.toz.network.PetsList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * FIXME Set proper name when implemented.
 * Main app screen.
 */
public class MainActivity extends AppCompatActivity {

    ArrayList<Pet> petsList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Intent i = new Intent(this, FinancialActivity.class);
        startActivity(i);

        /**
         *
         * Example of use retrofit.
         *
         *
         * */
        petsList = new ArrayList<>();

        final PetsApi petsApi = ApiClient.getPetsApiService();
        final Call<PetsList> call = petsApi.getJSON();

        call.enqueue(new Callback<PetsList>() {
            @Override
            public void onResponse(final Call<PetsList> call, final Response<PetsList> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    petsList = response.body().getPets();
                }
            }

            @Override
            public void onFailure(final Call<PetsList> call, final Throwable t) {
                Log.e("RESPONSE", "onFailure: ");

            }
        });


    }
}
