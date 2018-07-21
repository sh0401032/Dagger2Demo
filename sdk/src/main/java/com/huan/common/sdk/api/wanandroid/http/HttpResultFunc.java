package com.huan.common.sdk.api.wanandroid.http;

import android.util.Log;

import io.reactivex.functions.Function;

/**
 * Created by H_S on 2018/3/24.
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {
    @Override
    public T apply(HttpResult<T> httpResult) throws Exception {
        if (httpResult.getErrorCode() != 0) {
            Log.d("HttpClient", "接口调用失败");
        }
        return httpResult.getData();
    }
}
