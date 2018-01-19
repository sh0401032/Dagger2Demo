package com.huan.dagger2demo.di.module;

import android.content.Context;
import android.util.Log;

import com.huan.dagger2demo.di.test.DaggerTest;
import com.huan.dagger2demo.di.test.DaggerTest2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by H_S on 2018/1/17.
 */
@Module
public class MainModule {

    @Provides
    DaggerTest provideDaggerTest() {
        Log.d("Main", "provide daggertest");
        return new DaggerTest();
    }

    @Provides
    DaggerTest2 provideDaggerTest2(Context context, DaggerTest daggerTest) {
        Log.d("Main", "provide daggertest2");
        return new DaggerTest2(context, daggerTest);
    }

}
