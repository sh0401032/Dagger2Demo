package com.huan.dagger2demo.wanandroid.home;

import android.util.Log;

import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.common.sdk.api.wanandroid.bean.Pager;
import com.huan.common.sdk.service.AndroidService;
import com.huan.dagger2demo.base.BaseView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by H_S on 2018/7/21.
 */

public class AndroidHomePresenter implements WanAndroidHomeContract.IAndroidHomePresenter {

    WanAndroidHomeContract.IAndroidHomeView homeView;

    private AndroidService mAndroidService;

    public AndroidHomePresenter(AndroidService androidService) {
        mAndroidService = androidService;
    }

    @Override
    public void attachView(BaseView view) {
        if (view instanceof WanAndroidHomeContract.IAndroidHomeView) {
            homeView = (WanAndroidHomeContract.IAndroidHomeView) view;
        }
    }

    @Override
    public void detachView() {
        homeView = null;
    }

    @Override
    public void getHomeBannerList() {
        if (mAndroidService == null) return;
        mAndroidService.getBannerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Banner> banners) {
                        if (homeView != null) {
                            homeView.showHomeBanner(banners);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (homeView != null) {
                            homeView.showHomeBanner(null);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHomeList(int page) {
        if (mAndroidService == null) return;
        mAndroidService.getHomeList(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pager>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Pager pager) {
                        if (homeView != null) {
                            homeView.showHomePagerList(pager);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (homeView != null) {
                            homeView.showHomePagerList(null);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
