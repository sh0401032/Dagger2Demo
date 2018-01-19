package com.huan.dagger2demo.di.test;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by H_S on 2018/1/17.
 */

public class DaggerTest2 {


    public DaggerTest2(Context context, DaggerTest daggerTest) {
        Log.d("Main", "创建daggerTest2对象");
        Log.d("Main", "context=" + context);
    }
}
