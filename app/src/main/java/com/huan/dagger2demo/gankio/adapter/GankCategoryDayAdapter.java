package com.huan.dagger2demo.gankio.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huan.common.sdk.api.bean.GankIoDayItem;

import java.util.List;

/**
 * Created by H_S on 2018/1/26.
 */

public class GankCategoryDayAdapter extends BaseQuickAdapter<GankIoDayItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GankCategoryDayAdapter(List<GankIoDayItem> data) {
        super(data);

    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoDayItem item) {

    }
}
