package com.huan.common.sdk.service;

import com.huan.common.sdk.api.HttpClientHelper;
import com.huan.common.sdk.api.HttpResultFunc;
import com.huan.common.sdk.api.base.bean.HomeTab;
import com.huan.common.sdk.api.base.BaseApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by H_S on 2018/1/20.
 */

public class BaseService {

    private final static String BASE_SERVICE_URL = "http://192.168.199.172:3000/";
    private final BaseApiService baseApiService;
    private static BaseService instance;

    private BaseService() {
        baseApiService = HttpClientHelper.getClient(BASE_SERVICE_URL).create(BaseApiService.class);
    }

    public static BaseService getInstance() {
        if (instance == null) {
            synchronized (BaseService.class) {
                if (instance == null) instance = new BaseService();
            }
        }
        return instance;
    }

    public Observable<List<HomeTab>> getHomeTabList() {
        return baseApiService.getHomeTabList().map(new HttpResultFunc<List<HomeTab>>()).subscribeOn(Schedulers.io());
    }


}
