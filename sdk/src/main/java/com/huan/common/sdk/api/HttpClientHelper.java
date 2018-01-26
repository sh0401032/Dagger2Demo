package com.huan.common.sdk.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by H_S on 2018/1/15.
 */

public class HttpClientHelper {

    private static Retrofit instance;

    private HttpClientHelper() {
    }

    public static Retrofit getClient(String url) {


        instance = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

        return instance;
    }

    /**
     * 创建apiService
     */
    public static <T> T createApi(Class<T> clazz) {
        return instance == null ? null : instance.create(clazz);
    }

}
