package com.huan.dagger2demo.home;

import com.huan.dagger2demo.home.zhihu.ZhiHuFragmentContract;
import com.huan.dagger2demo.home.zhihu.ZhiHuPresenter;
import com.huan.common.sdk.api.service.BaseService;
import com.huan.common.sdk.api.service.ZhiHuService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by H_S on 2018/1/20.
 */
@Module
public class HomeFragmentModule {

    @Provides
    HomeContract.IHomePresenter provideHomePresenter(BaseService baseService) {
        return new HomePresenter(baseService);
    }

    @Provides
    ZhiHuFragmentContract.IZhiHuPresenter provideZhiHuPresenter(ZhiHuService zhiHuService) {
        return new ZhiHuPresenter(zhiHuService);
    }
}
