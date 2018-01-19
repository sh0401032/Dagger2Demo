package com.huan.dagger2demo.di.test;

import com.huan.dagger2demo.TestActivity;
import com.huan.dagger2demo.di.module.MainModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by H_S on 2018/1/17.
 */
@Subcomponent(modules = {MainModule.class})
public interface TestComponent extends AndroidInjector<TestActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TestActivity> {
    }
}
