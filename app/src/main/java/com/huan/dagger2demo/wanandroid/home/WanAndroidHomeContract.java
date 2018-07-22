package com.huan.dagger2demo.wanandroid.home;

import com.huan.common.sdk.api.wanandroid.bean.Pager;
import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;

/**
 * Created by H_S on 2018/7/21.
 */

public interface WanAndroidHomeContract {

    interface IAndroidHomePresenter extends BasePresenter {

        void getHomeList(int page);
    }

    interface IAndroidHomeView extends BaseView {
        void showHomePagerList(Pager pager);
    }
}
