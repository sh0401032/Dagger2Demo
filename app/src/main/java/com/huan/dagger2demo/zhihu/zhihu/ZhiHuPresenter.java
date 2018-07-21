package com.huan.dagger2demo.zhihu.zhihu;

import android.util.Log;

import com.huan.dagger2demo.base.BaseView;
import com.huan.common.sdk.api.ObserverAdapter;
import com.huan.common.sdk.api.zhihu.bean.ZhiHuDailyList;
import com.huan.common.sdk.service.ZhiHuService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/21.
 */

public class ZhiHuPresenter implements ZhiHuFragmentContract.IZhiHuPresenter {

    private ZhiHuFragmentContract.IZhiHuView zhiHuView;

    private ZhiHuService service;

    private String mDate;

    public ZhiHuPresenter(ZhiHuService service) {
        this.service = service;
    }

    @Override
    public void attachView(BaseView view) {
        if (view instanceof ZhiHuFragmentContract.IZhiHuView) {
            zhiHuView = (ZhiHuFragmentContract.IZhiHuView) view;
        }
    }

    @Override
    public void detachView() {
        zhiHuView = null;
    }

    @Override
    public void getLastDailyList() {

        service.getLastDailyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<ZhiHuDailyList>() {
                    @Override
                    public void onNext(ZhiHuDailyList zhiHuDailyList) {
                        super.onNext(zhiHuDailyList);

                        mDate = zhiHuDailyList.getDate();
                        Timber.d("mData = " + mDate);
                        if (zhiHuView != null) {
                            zhiHuView.updateDailyList(zhiHuDailyList.getStories());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("debug", e.getMessage());
                    }
                });
    }

    @Override
    public void getMoreDaily() {
        service.getDailyListWithDate(mDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverAdapter<ZhiHuDailyList>() {
                    @Override
                    public void onNext(ZhiHuDailyList zhiHuDailyList) {
                        super.onNext(zhiHuDailyList);
                        if (mDate.equals(zhiHuDailyList.getDate())) return;
                        mDate = zhiHuDailyList.getDate();
                        Timber.d("mData = " + mDate);
                        if (zhiHuView != null) {
                            zhiHuView.updateDailyList(zhiHuDailyList.getStories());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void getZhihuDailyDetail(String id) {
        service.getZhihuDailyDetail(id);
    }
}
