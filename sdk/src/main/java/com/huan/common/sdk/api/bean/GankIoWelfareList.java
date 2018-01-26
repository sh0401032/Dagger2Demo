package com.huan.common.sdk.api.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoWelfareList {
    @SerializedName("error")
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankIoWelfareItem> getResults() {
        return results;
    }

    public void setResults(List<GankIoWelfareItem> results) {
        this.results = results;
    }

    /**
     * _id : 5832662b421aa929b0f34e99
     * createdAt : 2016-11-21T11:12:43.567Z
     * desc :  深入Android渲染机制
     * publishedAt : 2016-11-24T11:40:53.615Z
     * source : web
     * type : Android
     * url : http://blog.csdn.net/ccj659/article/details/53219288
     * used : true
     * who : Chauncey
     */

    @SerializedName("results")
    private List<GankIoWelfareItem> results;
}
