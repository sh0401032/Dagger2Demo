package com.huan.common.sdk.service;

import com.huan.common.sdk.api.HttpClientHelper;
import com.huan.common.sdk.api.zhihu.bean.ZhiHuDailyItem;
import com.huan.common.sdk.api.zhihu.bean.ZhiHuDailyList;
import com.huan.common.sdk.api.zhihu.ZhiHuApiService;

import io.reactivex.Observable;

/**
 * Created by H_S on 2018/1/21.
 */

public class ZhiHuService {

    private final static String ZHIHUBASEURL = "http://news-at.zhihu.com";

    private final ZhiHuApiService apiService;
    private static ZhiHuService instance;

    private ZhiHuService() {

        apiService = HttpClientHelper.getClient(ZHIHUBASEURL).create(ZhiHuApiService.class);
    }

    public static ZhiHuService getInstance() {
        if (instance == null) {
            synchronized (ZhiHuService.class) {
                if (instance == null) instance = new ZhiHuService();
            }
        }
        return instance;
    }

    public Observable<ZhiHuDailyList> getLastDailyList() {
        return apiService.getLastDailyList();
    }

    public Observable<ZhiHuDailyList> getDailyListWithDate(String date) {
        return apiService.getDailyListWithDate(date);
    }

    public Observable<ZhiHuDailyItem> getZhihuDailyDetail(String id) {
        return apiService.getZhihuDailyDetail(id);
    }


}
