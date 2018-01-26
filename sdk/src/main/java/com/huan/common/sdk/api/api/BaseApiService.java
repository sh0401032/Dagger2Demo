package com.huan.common.sdk.api.api;

import com.huan.common.sdk.api.HttpResult;
import com.huan.common.sdk.api.bean.HomeTab;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by H_S on 2018/1/20.
 */

public interface BaseApiService {

    @GET("/getHomeTabList")
    Observable<HttpResult<List<HomeTab>>> getHomeTabList();

}
