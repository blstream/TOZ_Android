package com.intive.toz.data;

import com.intive.toz.petslist.model.Pet;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;
import com.intive.toz.news.model.News;
import com.intive.toz.schedule.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Data loader.
 */
public class DataLoader implements DataProvider {
    private final PetsApi api = ApiClient.getPetsApiService();

    @Override
    public void fetchNews(final ResponseCallback<List<News>> listener) {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(final Call<List<News>> call, final Response<List<News>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<List<News>> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void fetchPets(final ResponseCallback<List<Pet>> listener) {
        api.getGalleryPetsListCall().enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(final Call<List<Pet>> call, final Response<List<Pet>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<List<Pet>> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void fetchDetailNews(final ResponseCallback<News> listener, final String id) {
        api.getDetailNews(id).enqueue(new Callback<News>() {
            @Override
            public void onResponse(final Call<News> call, final Response<News> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(final Call<News> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void fetchPetDetails(final String petID, final ResponseCallback<Pet> listener) {
        api.getPetDetailsCall(petID).enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(final Call<Pet> call, final Response<Pet> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<Pet> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void fetchSchedule(final ResponseCallback<List<Schedule>> listener, final String from, final String to) {
        api.getSchedule(from, to).enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(final Call<List<Schedule>> call, final Response<List<Schedule>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<List<Schedule>> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }
}
