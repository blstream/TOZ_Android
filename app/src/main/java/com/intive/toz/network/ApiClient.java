package com.intive.toz.network;

import com.intive.toz.TozApplication;
import com.intive.toz.login.Session;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Base url connection.
 */
public final class ApiClient {

    public static final String API_URL = "http://dev.patronage2017.intive-projects.com/api/";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String CACHE_DIRECTORY = "cache";
    private static final int BUFFER_SIZE = 10485760;
    private static final int MAX_STALE = 7;

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
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + Session.getJwt())
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                            .build();
                }
                return chain.proceed(request);
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
