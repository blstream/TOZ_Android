package com.intive.toz.data;

import com.intive.toz.R;
import com.intive.toz.account.model.ResponseMessage;
import com.intive.toz.account.model.UserPassword;
import com.intive.toz.login.Session;
import com.intive.toz.login.model.Jwt;
import com.intive.toz.login.model.Login;
import com.intive.toz.network.ApiClient;
import com.intive.toz.network.PetsApi;
import com.intive.toz.news.model.News;
import com.intive.toz.petslist.model.Pet;

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
    private static final int ERROR_CODE_WRONG_PASSWORD = 400;
    private static final int ERROR_CODE_INCORRECRT_USER_OR_PASSWORD = 401;
    private static final int ERROR_CODE_UNATHORIZED = 401;
    private static final int ERROR_CODE_FORBIDDEN = 403;
    private static final int ERROR_CODE_NOT_FOUND = 404;
    private static final String NEWS_TYPE_RELEASED = "RELEASED";


    @Override
    public void fetchReleasedNews(final ResponseCallback<List<News>> listener) {
        api.getReleasedNews(NEWS_TYPE_RELEASED).enqueue(new Callback<List<News>>() {
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
    public void fetchAllNews(final ResponseCallback<List<News>> listener) {
        api.getAllNews().enqueue(new Callback<List<News>>() {
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
    public void fetchResponseLogin(final ResponseLoginCallback<Jwt> listener, final Login loginObj) {
        api.login(loginObj).enqueue(new Callback<Jwt>() {
            @Override
            public void onResponse(final Call<Jwt> call, final Response<Jwt> response) {
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
            public void onFailure(final Call<Jwt> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void requestPasswordChange(final ResponseChangePasswordCallback<ResponseMessage> listener, final UserPassword userPassword) {
        api.changePassword(userPassword, Session.getJwtWithBearerPrefix()).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(final Call<ResponseMessage> call, final Response<ResponseMessage> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else if (response.code() == ERROR_CODE_WRONG_PASSWORD) {
                    listener.onWrongPassword();
                } else if (response.code() == ERROR_CODE_UNATHORIZED) {
                    listener.onErrorCode(R.string.error_unauthorized);
                } else if (response.code() == ERROR_CODE_FORBIDDEN) {
                    listener.onErrorCode(R.string.error_forbidden);
                } else if (response.code() == ERROR_CODE_NOT_FOUND) {
                    listener.onErrorCode(R.string.error_not_found);
                }
            }

            @Override
            public void onFailure(final Call<ResponseMessage> call, final Throwable t) {
                listener.onError(t);
            }
        });
    }
}
