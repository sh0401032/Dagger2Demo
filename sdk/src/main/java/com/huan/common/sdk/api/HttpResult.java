package com.huan.common.sdk.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by H_S on 2018/1/15.
 */

public class HttpResult<T> {
    @SerializedName("status")
    private int resultCode = -1;
    @SerializedName("message")
    private String resultMessage;

    @SerializedName("data")
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public T getData() {
        return data;
    }
}
