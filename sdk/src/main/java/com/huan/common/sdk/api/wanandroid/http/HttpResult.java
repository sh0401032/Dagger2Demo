package com.huan.common.sdk.api.wanandroid.http;

import com.google.gson.annotations.SerializedName;

/**
 * Created by H_S on 2018/3/24.
 * {
 *     "data": ...,
 *     "errorCode": 0,
 *     "errorMsg": ""
 * }
 */

public class HttpResult<D> {
    @SerializedName("errorCode")
    private  int errorCode=-1;
    @SerializedName("errorMsg")
    private String errorMsg;
    @SerializedName("data")
    private D data;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public D getData() {
        return data;
    }
}
