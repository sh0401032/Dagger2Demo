package com.huan.common.sdk.api.base;

import com.huan.common.sdk.library.http.HttpResult;
import com.huan.common.sdk.api.base.bean.HomeTab;

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
