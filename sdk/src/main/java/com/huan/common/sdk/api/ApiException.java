package com.huan.common.sdk.api;

/**
 * Created by H_S on 2018/1/15.
 */

public class ApiException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public ApiException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;

    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
