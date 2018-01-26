package com.huan.dagger2demo.di.module;

import dagger.Module;

/**
 * Created by H_S on 2018/1/20.
 * 用于管理app module所需的各种Activity和Fragment等依赖模块
 */
@Module(includes = {BindActivityModule.class, BindFragmentModule.class})
public class HomeModule {

}
