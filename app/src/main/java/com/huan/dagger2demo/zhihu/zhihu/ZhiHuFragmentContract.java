package com.huan.dagger2demo.zhihu.zhihu;

import com.huan.dagger2demo.base.BasePresenter;
import com.huan.dagger2demo.base.BaseView;
import com.huan.common.sdk.api.zhihu.bean.ZhiHuDailyItem;

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
