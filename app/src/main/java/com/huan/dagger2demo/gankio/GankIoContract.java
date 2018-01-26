package com.huan.dagger2demo.gankio;

import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;

import java.util.List;

/**
 * Created by H_S on 2018/1/26.
 */

public interface GankIoContract {
    interface IGankIoPresenter extends BasePresenter {
        void getCategoryList();
    }

    interface IGankIoView extends BaseView {
        void showCategoryList(List<String> categoryList);
    }
}
