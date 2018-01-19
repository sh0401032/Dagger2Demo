package com.huan.dagger2demo.di.module;

import android.content.Context;
import android.util.Log;

import com.huan.dagger2demo.MyApplication;
import com.huan.dagger2demo.di.test.DaggerTest;
import com.huan.dagger2demo.di.test.DaggerTest2;

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


}
