package com.huan.dagger2demo.gankio;

import com.huan.common.sdk.service.GankIoService;
import com.huan.dagger2demo.base.BaseView;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoPresenter implements GankIoContract.IGankIoPresenter {

    private GankIoService mGankService;

    private GankIoContract.IGankIoView mView;


    public GankIoPresenter(GankIoService gankIoService) {
        mGankService = gankIoService;
    }


    @Override
    public void getCategoryList() {
        if (mView != null) {
            ArrayList<String> list = new ArrayList<>();
            list.add("每日推荐");
            list.add("all");
            list.add("Android");
            list.add("iOS");
            list.add("休息视频");
            list.add("福利");
            list.add("拓展资源");
            list.add("前端");
            list.add("瞎推荐");
            list.add("App");
            Timber.d("categoryList = " + list.toString());
            mView.showCategoryList(list);
        }
    }

    @Override
    public void attachView(BaseView view) {
        if (view instanceof GankIoContract.IGankIoView)
            mView = (GankIoContract.IGankIoView) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }


}
