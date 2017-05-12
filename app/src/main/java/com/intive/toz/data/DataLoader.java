package com.intive.toz.data;

import com.intive.toz.R;
import com.intive.toz.login.model.Login;
import com.intive.toz.login.model.User;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;
import com.intive.toz.news.model.News;
import com.intive.toz.petslist.model.Pet;
import com.intive.toz.schedule.model.Reservation;
import com.intive.toz.schedule.model.Reserve;
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

    private static final int ERROR_CODE_VALIDATION = 400;
    private static final int ERROR_CODE_INCORRECRT_USER_OR_PASSWORD = 401;
    private static final int ERROR_CODE_FORBIDDEN = 403;
    private static final int ERROR_CODE_NOT_FOUND = 404;

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
    public void fetchResponseLogin(final ResponseLoginCallback<User> listener, final Login loginObj) {
        api.login(loginObj).enqueue(new Callback<User>() {
            @Override
            public void onResponse(final Call<User> call, final Response<User> response) {
                if (response.isSuccessful()) { // 201
                    listener.onSuccess(response.body());
                } else if (response.code() == ERROR_CODE_VALIDATION) {
                    // Validation error
                    listener.onErrorCode(R.string.auth_error_validation);
                } else if (response.code() == ERROR_CODE_INCORRECRT_USER_OR_PASSWORD) {
                    // Incorrect user or password - bad password
                    listener.onErrorPassword();
                } else if (response.code() == ERROR_CODE_FORBIDDEN) {
                    // Forbidden
                    listener.onErrorCode(R.string.auth_forbidden);
                } else if (response.code() == ERROR_CODE_NOT_FOUND) {
                    // Not found - bad login
                    listener.onErrorLogin();
                } else {
                    // Handle other responses
                    listener.onErrorCode(R.string.auth_error_response);
                }
            }

            @Override
            public void onFailure(final Call<User> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void fetchSchedule(final ResponseCallback<Schedule> listener, final String from, final String to) {
        api.getSchedule(from, to).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(final Call<Schedule> call, final Response<Schedule> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<Schedule> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void reserve(final ResponseCallback<Reservation> listener, final Reserve reserve) {
        api.reservation(reserve).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(final Call<Reservation> call, final Response<Reservation> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(final Call<Reservation> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }
}
