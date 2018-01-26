package com.huan.dagger2demo.home.zhihu;

import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;
import com.huan.common.sdk.api.bean.ZhiHuDailyItem;
import com.huan.common.sdk.api.bean.ZhiHuDailyList;

import java.util.List;

/**
 * Created by H_S on 2018/1/21.
 */

public interface ZhiHuFragmentContract {

    interface IZhiHuPresenter extends BasePresenter {
        void getLastDailyList();
        void getMoreDaily();
        void getZhihuDailyDetail(String id);

    }

    interface IZhiHuView extends BaseView {
        void updateDailyList(List<ZhiHuDailyItem> zhiHuDailyList);
        void showZhiHuDailyDetail();
    }
}
