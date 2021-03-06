package com.khtn.healthcare.view.api;

import android.provider.SyncStateContract;

import com.khtn.healthcare.view.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



    public class UserService {
        public static final int CONNECT_TIMEOUT_IN_MS = 20000;

        private static Retrofit retrofit = null;

        public static UserApi getAPI() {
            return getRetrofit().create(UserApi.class);
        }

        private static Retrofit getRetrofit() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.IP_ADDRESS)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(provideOkHttpClient())
                        .build();
            }
            return retrofit;
        }

        private static OkHttpClient provideOkHttpClient() {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
}
