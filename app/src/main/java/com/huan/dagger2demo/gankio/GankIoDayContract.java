package com.huan.dagger2demo.gankio;

import com.huan.common.sdk.api.bean.GankIoCustomItem;
import com.huan.common.sdk.api.bean.GankIoDayItem;
import com.huan.common.sdk.api.bean.GankIoWelfareItem;
import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;

import java.util.List;

/**
 * Created by H_S on 2018/1/26.
 */

public interface GankIoDayContract {

    interface IGankIoDayPresenter extends BasePresenter {

        void getGankIoDay(String year, String month, String day);

        void showCategoryFragment(String category);

    }

    interface IGankIoDayView extends BaseView {
        void showGankIoDay(List<List<GankIoDayItem>> gankIoDayList);


    }
}
