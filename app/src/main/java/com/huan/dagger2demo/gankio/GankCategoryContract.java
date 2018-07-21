package com.huan.dagger2demo.gankio;

import com.huan.common.sdk.api.gankio.bean.GankIoCustomItem;
import com.huan.common.sdk.api.gankio.bean.GankIoWelfareItem;
import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;

import java.util.List;

/**
 * Created by H_S on 2018/1/26.
 */

public interface GankCategoryContract {

    interface IGankCategoryPresenter extends BasePresenter {
        void getGankIoCustomList(String type, int prePage, int page);

        void getGankIoWelfareList(int pre_page, int page);
    }

    interface IGankCategoryView extends BaseView {

        void showGankIoWelfareList(List<GankIoWelfareItem> gankIoWelfareList);

        void showGankIoCustomList(List<GankIoCustomItem> gankIoCustomList);
    }
}
