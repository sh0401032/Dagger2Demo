package com.huan.dagger2demo.gankio;

import com.huan.common.sdk.library.http.ObserverAdapter;
import com.huan.common.sdk.api.gankio.bean.GankIoCustomList;
import com.huan.common.sdk.api.gankio.bean.GankIoWelfareList;
import com.huan.common.sdk.service.GankIoService;
import com.huan.dagger2demo.base.BaseView;
import com.huan.dagger2demo.gankio.GankCategoryContract.IGankCategoryPresenter;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/26.
 */

public class GankCategoryPresenter implements IGankCategoryPresenter {


    private final GankIoService mGankService;

    private GankCategoryContract.IGankCategoryView categoryView;



    public GankCategoryPresenter(GankIoService gankIoService) {
        mGankService = gankIoService;
    }


    @Override
    public void attachView(BaseView view) {
        if (view instanceof GankCategoryContract.IGankCategoryView)
            categoryView = (GankCategoryContract.IGankCategoryView) view;
    }

    @Override
    public void detachView() {
        Timber.d("取消view绑定");
        categoryView = null;
    }

    @Override
    public void getGankIoCustomList(String type, int prePage, int page) {
        mGankService.getGankIoCustomList(type, prePage, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<GankIoCustomList>() {
                    @Override
                    public void onNext(GankIoCustomList gankIoCustomList) {
                        super.onNext(gankIoCustomList);
                        Timber.d("gankIoCustomList");
                        if (categoryView != null && gankIoCustomList != null) {
                            categoryView.showGankIoCustomList(gankIoCustomList.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getGankIoWelfareList(int pre_page, int page) {
        mGankService.getGankIoWelfareList(pre_page, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<GankIoWelfareList>() {
                    @Override
                    public void onNext(GankIoWelfareList gankIoWelfareList) {
                        super.onNext(gankIoWelfareList);
                        Timber.d("gankIoWelfareList");
                        if (categoryView != null && gankIoWelfareList != null) {
                            categoryView.showGankIoWelfareList(gankIoWelfareList.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
