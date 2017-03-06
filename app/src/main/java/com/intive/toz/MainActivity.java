package com.intive.toz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.intive.toz.Retrofit.Pet;
import com.intive.toz.Retrofit.PetsApi;
import com.intive.toz.Retrofit.PetsList;
import com.intive.toz.Retrofit.RetroClient;

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

        petsList = new ArrayList<>();

        final PetsApi petsApi = RetroClient.getPetsApiService();
        final Call<PetsList> call = petsApi.getMyJSON();

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
