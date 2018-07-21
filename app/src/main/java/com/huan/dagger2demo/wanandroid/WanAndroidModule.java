package com.huan.dagger2demo.wanandroid;

import com.huan.common.sdk.service.AndroidService;
import com.huan.dagger2demo.wanandroid.home.AndroidHomePresenter;
import com.huan.dagger2demo.wanandroid.home.WanAndroidHomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by H_S on 2018/7/21.
 */
@Module
public class WanAndroidModule {

    @Provides
    WanAndroidHomeContract.IAndroidHomePresenter provideAndroidHomePresenter(AndroidService androidService) {
        return new AndroidHomePresenter(androidService);
    }
}
