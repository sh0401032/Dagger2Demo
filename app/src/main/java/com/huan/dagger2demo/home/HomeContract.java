package com.huan.dagger2demo.home;

import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;
import com.huan.common.sdk.api.bean.HomeTab;

import java.util.List;

/**
 * Created by H_S on 2018/1/20.
 */

public interface HomeContract {

    interface IHomePresenter extends BasePresenter {
        List<HomeTab>  getTabList();
    }

    interface IHomeView extends BaseView {
        void showTabList(List<HomeTab> homeTabs);
    }
}
