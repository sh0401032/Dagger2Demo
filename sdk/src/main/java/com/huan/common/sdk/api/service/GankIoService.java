package com.huan.common.sdk.api.service;

import com.huan.common.sdk.api.HttpClientHelper;
import com.huan.common.sdk.api.bean.GankIoCustomList;
import com.huan.common.sdk.api.bean.GankIoDay;
import com.huan.common.sdk.api.bean.GankIoWelfareList;
import com.huan.common.sdk.api.api.GankIoApiService;

import io.reactivex.Observable;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoService {

    private final static String GANKIOURL = "http://gank.io";

    private static GankIoService instance;
    private GankIoApiService apiService;

    private GankIoService() {
        apiService = HttpClientHelper.getClient(GANKIOURL).create(GankIoApiService.class);
    }


    public static GankIoService getInstance() {
        if (instance == null) {
            synchronized (GankIoService.class) {
                if (instance == null)
                    instance = new GankIoService();
            }
        }
        return instance;
    }

    public Observable<GankIoDay> getGankIoDay(String year, String month, String day) {
        return apiService.getGankIoDay(year, month, day);
    }

    public Observable<GankIoCustomList> getGankIoCustomList(String type, int prePage, int page) {
        return apiService.getGankIoCustomList(type, prePage, page);
    }

    public Observable<GankIoWelfareList> getGankIoWelfareList(int pre_page, int page) {
        return apiService.getGankIoWelfareList(pre_page, page);
    }
}
