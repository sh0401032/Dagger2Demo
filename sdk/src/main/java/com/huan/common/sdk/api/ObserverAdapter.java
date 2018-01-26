package com.huan.common.sdk.api;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by H_S on 2018/1/20.
 */

public abstract class ObserverAdapter<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }


}
