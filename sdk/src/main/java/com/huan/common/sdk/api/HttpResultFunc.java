package com.huan.common.sdk.api;

import io.reactivex.functions.Function;

/**
 * Created by H_S on 2018/1/15.
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

    @Override
    public T apply(HttpResult<T> tHttpResult) throws Exception {
        if (tHttpResult.getResultCode() != 1) {
            throw new ApiException(tHttpResult.getResultCode(), tHttpResult.getResultMessage());
        }
        return tHttpResult.getData();
    }
}
