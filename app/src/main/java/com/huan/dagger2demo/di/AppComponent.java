package com.huan.dagger2demo.di;

import com.huan.dagger2demo.MyApplication;
import com.huan.dagger2demo.di.module.HomeModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by H_S on 2018/1/17.
 */
@Singleton
@Component(modules = {
        // 通用module
        AndroidSupportInjectionModule.class,
        AppModule.class,

        // 各Module
        HomeModule.class

})
interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(MyApplication application);

        AppComponent build();
    }

}
