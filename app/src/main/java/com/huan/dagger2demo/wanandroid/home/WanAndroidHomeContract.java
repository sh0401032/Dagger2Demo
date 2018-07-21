package com.huan.dagger2demo.wanandroid.home;

import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.common.sdk.api.wanandroid.bean.Pager;
import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;

import java.util.List;

/**
 * Created by H_S on 2018/7/21.
 */

public interface WanAndroidHomeContract {

    interface IAndroidHomePresenter extends BasePresenter {
        void getHomeBannerList();

        void getHomeList(int page);
    }

    interface IAndroidHomeView extends BaseView {
        void showHomeBanner(List<Banner> bannerList);

        void showHomePagerList(Pager pager);
    }
}
