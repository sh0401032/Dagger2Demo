package com.huan.common.sdk.api.api;

import com.huan.common.sdk.api.bean.ZhiHuDailyItem;
import com.huan.common.sdk.api.bean.ZhiHuDailyList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by H_S on 2018/1/21.
 */

public interface ZhiHuApiService {
    @GET("/api/4/news/latest")
    Observable<ZhiHuDailyList> getLastDailyList();

    @GET("/api/4/news/before/{date}")
    Observable<ZhiHuDailyList> getDailyListWithDate(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhiHuDailyItem> getZhihuDailyDetail(@Path("id") String id);
}
