package com.huan.dagger2demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.huan.dagger2demo.base.BaseActivity;
import com.huan.dagger2demo.di.test.DaggerTest;

import javax.inject.Inject;

/**
 * Created by H_S on 2018/1/17.
 */

public class TestActivity extends BaseActivity {

    @Inject
    DaggerTest daggerTest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
    }
}
