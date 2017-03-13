package com.intive.toz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.intive.toz.network.PetsApi;
import com.intive.toz.network.ApiClient;

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

        /**
         *
         * Example of use retrofit.
         *
         *
         * */
        petsList = new ArrayList<>();
        final PetsApi petsApi = ApiClient.getPetsApiService();
        final Call<ArrayList<Pet>> call = petsApi.getGalleryPetsListCall();


        call.enqueue(new Callback<ArrayList<Pet>>() {
            @Override
            public void onResponse(final Call<ArrayList<Pet>> call, final Response<ArrayList<Pet>> response) {
                Log.i("RESPONSE", "onResponse: ");
                if (response.isSuccessful()) {
                    petsList = response.body();
                    for (Pet p : petsList) {
                        Log.e("Lista ", "imie " + p.getName());

                    }
                }
            }

            @Override
            public void onFailure(final Call<ArrayList<Pet>> call, final Throwable t) {
                Log.e("RESPONSE", "onFailure: ");

            }
        });

    }
}
