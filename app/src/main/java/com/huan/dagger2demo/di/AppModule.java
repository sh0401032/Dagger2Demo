package com.huan.dagger2demo.di;

import android.content.Context;

import com.huan.common.sdk.service.AndroidService;
import com.huan.common.sdk.service.GankIoService;
import com.huan.dagger2demo.MyApplication;
import com.huan.common.sdk.service.BaseService;
import com.huan.common.sdk.service.ZhiHuService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by H_S on 2018/1/17.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(MyApplication application) {
        return application;
    }

    @Provides
    BaseService provideBaseService() {
        return BaseService.getInstance();
    }

    @Provides
    ZhiHuService provideZhiHuService() {
        return ZhiHuService.getInstance();
    }

    @Provides
    GankIoService provideGankIoService() {
        return GankIoService.getInstance();
    }

    @Provides
    AndroidService provideAndroidService() {
        return AndroidService.getInstance();
    }

}
