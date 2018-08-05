package com.huan.dagger2demo.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Created by huanshao on 2018/7/27.
 */

public interface ILifeCyclePresenter extends IBaseLifeCycle {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop();

}
