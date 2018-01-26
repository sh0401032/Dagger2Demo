package com.huan.dagger2demo.gankio;

import com.huan.common.sdk.api.service.GankIoService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by H_S on 2018/1/26.
 * 用于提供gankio中所需的各种依赖
 */
@Module
public class GankModule {

    @Provides
    GankIoContract.IGankIoPresenter provideGankIoPresenter(GankIoService gankService) {
        return new GankIoPresenter(gankService);
    }

    @Provides
    GankCategoryContract.IGankCategoryPresenter provideGankCategoryPresenter(GankIoService gankIoService) {
        return new GankCategoryPresenter(gankIoService);
    }

}
