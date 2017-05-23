package com.intive.toz.network;

import com.google.gson.Gson;
import com.intive.toz.TozApplication;
import com.intive.toz.login.Session;
import com.intive.toz.login.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.awaitility.Awaitility.await;

/**
 * Base url connection.
 */
public final class ApiClient {

    public static final String API_URL = "http://dev.patronage2017.intive-projects.com/api/";
    public static final String IMAGES_BASE_URL = "http://dev.patronage2017.intive-projects.com/";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String CACHE_DIRECTORY = "cache";
    private static final int BUFFER_SIZE = 10485760;
    private static final int MAX_STALE = 7;
    private static final int MAX_DELAY = 3;

    private static Boolean flag = false;

    private ApiClient() {
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addInterceptor(provideAuthorizationInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache())
                .build();
    }

    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(TozApplication.getInstance().getCacheDir(), CACHE_DIRECTORY), BUFFER_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    private static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(1, TimeUnit.MINUTES)
                        .build();

                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    private static Interceptor provideOfflineCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                Request request = chain.request();

                if (!TozApplication.getInstance().isOnline()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(MAX_STALE, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    private static Interceptor provideAuthorizationInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                Request request = chain.request();
                if (Session.isLogged()) {
                    final long time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                    if (time > Session.getExpirationDate()) {
                        Request refresh = refreshTokenRequestBuilder();
                        OkHttpClient client = new OkHttpClient();
                        client.newCall(refresh).enqueue(new Callback() {
                            @Override
                            public void onFailure(final Call call, final IOException e) {
                                TozApplication.getInstance().onTokenRefreshError();
                            }

                            @Override
                            public void onResponse(final Call call, final Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    Gson gson = new Gson();
                                    User user = gson.fromJson(response.body().charStream(), User.class);
                                    Session.logIn(user.getJwt(), user.getUserId(), user.getRoles().get(0), user.getExpirationDateSeconds());
                                    flag = true;
                                } else {
                                    TozApplication.getInstance().onTokenRefreshError();
                                }
                            }
                        });

                        await().atMost(MAX_DELAY, TimeUnit.SECONDS).until(flag());
                        if (flag) {
                            request = request.newBuilder()
                                    .addHeader("Authorization", "Bearer " + Session.getJwt())
                                    .addHeader("Content-Type", "application/json")
                                    .addHeader("Accept", "application/json")
                                    .build();
                            return chain.proceed(request);
                        }
                    }
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + Session.getJwt())
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }

    private static Request refreshTokenRequestBuilder() {
        JSONObject json = new JSONObject();

        try {
            json.put("email", Session.getEmail());
            json.put("password", Session.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());

        return new Request.Builder()
                .url(API_URL + "/tokens/acquire")
                .method("POST", requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .post(requestBody)
                .build();
    }

    private static Callable<Boolean> flag() {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return flag;
            }
        };
    }

    /**
     * @return pets Api
     */
    public static PetsApi getPetsApiService() {
        return getRetrofitInstance().create(PetsApi.class);
    }

}
