package com.huan.dagger2demo.gankio;

import com.huan.common.sdk.api.ObserverAdapter;
import com.huan.common.sdk.api.gankio.bean.GankIoDay;
import com.huan.common.sdk.api.gankio.bean.GankIoDayItem;
import com.huan.common.sdk.service.GankIoService;
import com.huan.dagger2demo.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/27.
 */

public class GankIoDayPresenter implements GankIoDayContract.IGankIoDayPresenter {

    private GankIoDayContract.IGankIoDayView dayView;

    private GankIoDay mGankIoDay;

    private List<List<GankIoDayItem>> gankIoDayList = new ArrayList<>();

    private final GankIoService mGankService;

    public GankIoDayPresenter(GankIoService mGankService) {
        this.mGankService = mGankService;
    }

    @Override
    public void attachView(BaseView view) {
        if (view instanceof GankIoDayContract.IGankIoDayView) {
            dayView = (GankIoDayContract.IGankIoDayView) view;
        }
    }

    @Override
    public void detachView() {
        dayView = null;

    }

    @Override
    public void showCategoryFragment(String category){
        //TODO 使用RxBus实现


    }


    @Override
    public void getGankIoDay(String year, String month, String day) {
        mGankService.getGankIoDay(year, month, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<GankIoDay, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(GankIoDay gankIoDay) throws Exception {
                        mGankIoDay = gankIoDay;
                        return Observable.fromIterable(gankIoDay.getCategory());
                    }
                })
                .map(new Function<String, List<GankIoDayItem>>() {
                    @Override
                    public List<GankIoDayItem> apply(String s) throws Exception {
                        GankIoDay.ResultsBean result = mGankIoDay.getResults();
                        List<GankIoDayItem> list = new ArrayList<>();
                        switch (s) {
                            case "福利":
                                list = result.getWelfare();
                                break;
                            case "Android":
                                list = result.getAndroid();
                                break;
                            case "iOS":
                                list = result.getiOS();
                                break;
                            case "前端":
                                list = result.getFront();
                                break;
                            case "休息视频":
                                list = result.getRestMovie();
                                break;
                            case "瞎推荐":
                                list = result.getRecommend();
                                break;
                            case "拓展资源":
                                list = result.getResource();
                                break;
                            case "App":
                                list = result.getApp();
                                break;

                        }
                        return list;
                    }
                })
                .subscribe(new ObserverAdapter<List<GankIoDayItem>>() {


                    @Override
                    public void onNext(List<GankIoDayItem> gankIoDayItemList) {
                        if (gankIoDayItemList != null && gankIoDayItemList.size() > 0) {
                            Timber.d("添加数据 ：" + gankIoDayItemList.size());
                            if (gankIoDayList == null) {
                                gankIoDayList = new ArrayList<>();
                            }
                            gankIoDayList.add(gankIoDayItemList);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (dayView != null) {
                            Timber.d("gankIoDayList size is " + gankIoDayList.size());
                            List<List<GankIoDayItem>> result = new ArrayList<>();
                            result.addAll(gankIoDayList);
                            dayView.showGankIoDay(result);
                        }
                        if (gankIoDayList != null) {
                            gankIoDayList.clear();
                            gankIoDayList = null;
                        }
                    }
                });
    }
}
