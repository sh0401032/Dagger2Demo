package com.huan.dagger2demo.zhihu;

import com.huan.dagger2demo.MyApplication;
import com.huan.dagger2demo.base.BaseView;
import com.huan.common.sdk.api.ObserverAdapter;
import com.huan.common.sdk.api.base.bean.HomeTab;
import com.huan.common.sdk.service.BaseService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by H_S on 2018/1/20.
 */

public class HomePresenter implements HomeContract.IHomePresenter {

    private BaseService baseService;
    private HomeContract.IHomeView homeView;
    private List<HomeTab> mHomeTab = new ArrayList<>();

    public HomePresenter(BaseService baseService) {
        this.baseService = baseService;
    }

    @Override
    public List<HomeTab> getTabList() {

        if (MyApplication.DEBUG_TEST) {
            mHomeTab = new ArrayList<>();
            HomeTab e1 = new HomeTab();
            e1.id = 1;
            e1.name = "今日头条";
            mHomeTab.add(e1);
            HomeTab e2 = new HomeTab();
            e2.id = 2;
            e2.name = "微信精选";
            mHomeTab.add(e2);
            HomeTab e3 = new HomeTab();
            e3.id = 3;
            e3.name = "热点新闻";
            mHomeTab.add(e3);
            if (homeView != null) {
                homeView.showTabList(mHomeTab);
            }

            return mHomeTab;
        }


        baseService.getHomeTabList().observeOn(AndroidSchedulers.mainThread()).subscribe(new ObserverAdapter<List<HomeTab>>() {

            @Override
            public void onNext(List<HomeTab> homeTabs) {
                super.onNext(homeTabs);
                if (homeTabs != null && homeTabs.size() > 0) {
                    mHomeTab = homeTabs;

                    if (homeView != null) {
                        homeView.showTabList(mHomeTab);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        return mHomeTab;
    }

    @Override
    public void attachView(BaseView view) {
        if (view instanceof HomeContract.IHomeView) {
            homeView = (HomeContract.IHomeView) view;
        }
    }

    @Override
    public void detachView() {
        homeView = null;
    }
}
