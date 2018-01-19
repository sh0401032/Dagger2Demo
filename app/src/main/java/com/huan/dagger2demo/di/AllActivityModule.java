package com.huan.dagger2demo.di;

import android.app.Activity;

import com.huan.dagger2demo.MainActivity;
import com.huan.dagger2demo.TestActivity;
import com.huan.dagger2demo.di.module.MainModule;
import com.huan.dagger2demo.di.test.TestComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by H_S on 2018/1/17.
 */
@Module
public abstract class AllActivityModule {

    // 通过ContributesAndroidInjector注解，将自动为MainActivity创建SubComponent并添加到Map集合中
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributesMainActivityInjector();

//    // 将TestComponent添加到Map集合中作为某Activity的SubComponent,代码中为TestActivity
//    // TestComponent将Builder暴露给外部，Module获取到TestComponent.Builder返回一个AndroidInjector.Factory
//    @Binds
//    @IntoMap
//    @ActivityKey(TestActivity.class)
//    abstract AndroidInjector.Factory<? extends Activity> bindTestActivityInjectorFactory(TestComponent.Builder builder);

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract TestActivity contributesTestActivityInjector();

}
