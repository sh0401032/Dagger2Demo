package com.huan.dagger2demo.di.module;

import com.huan.dagger2demo.gankio.GankIoCategoryFragment;
import com.huan.dagger2demo.gankio.GankIoDayFragment;
import com.huan.dagger2demo.gankio.GankIoFragment;
import com.huan.dagger2demo.gankio.GankModule;
import com.huan.dagger2demo.wanandroid.WanAndroidModule;
import com.huan.dagger2demo.wanandroid.home.WanAndroidHomeFragment;
import com.huan.dagger2demo.zhihu.ZhiHuHomeFragment;
import com.huan.dagger2demo.zhihu.HomeFragmentModule;
import com.huan.dagger2demo.zhihu.fragment.ReDianFragment;
import com.huan.dagger2demo.zhihu.fragment.TestFragment;
import com.huan.dagger2demo.zhihu.fragment.WeiXinFragment;
import com.huan.dagger2demo.zhihu.zhihu.ZhiHuFragment;
import com.huan.dagger2demo.wanandroid.WanAndroidFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by H_S on 2018/1/20.
 */
@Module
public abstract class BindFragmentModule {

    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    abstract ZhiHuHomeFragment contributesHomeFragmentInjector();

    @ContributesAndroidInjector(modules = {})
    abstract TestFragment contributesTestFragmentInjector();

    @ContributesAndroidInjector(modules = {HomeFragmentModule.class})
    abstract ZhiHuFragment contributesZhiHuFragmentInjector();

    @ContributesAndroidInjector(modules = {})
    abstract WeiXinFragment contributesWeiXinFragmentInjector();

    @ContributesAndroidInjector(modules = {})
    abstract ReDianFragment contributesReDianFragmentInjector();

    @ContributesAndroidInjector(modules = {GankModule.class})
    abstract GankIoFragment contributesGankIoFragmentInjector();

    @ContributesAndroidInjector(modules = {GankModule.class})
    abstract GankIoCategoryFragment contributesGankIoCategoryFragmentInjector();

    @ContributesAndroidInjector(modules = {GankModule.class})
    abstract GankIoDayFragment contributesGankIoDayFragmentInjector();

    @ContributesAndroidInjector(modules = {})
    abstract WanAndroidFragment contributesWanAndroidFragmentInjector();

    @ContributesAndroidInjector(modules = {WanAndroidModule.class})
    abstract WanAndroidHomeFragment contributesWanAndroidHomeFragmentInjector();


}
