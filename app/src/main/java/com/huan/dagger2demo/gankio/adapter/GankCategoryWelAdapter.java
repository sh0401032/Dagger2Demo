package com.huan.dagger2demo.gankio.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huan.common.sdk.api.gankio.bean.GankIoWelfareItem;
import com.huan.common.sdk.library.imageloader.ImageLoader;
import com.huan.dagger2demo.R;

import java.util.List;

/**
 * Created by H_S on 2018/1/26.
 */

public class GankCategoryWelAdapter extends BaseQuickAdapter<GankIoWelfareItem, BaseViewHolder> {
    public GankCategoryWelAdapter(@Nullable List<GankIoWelfareItem> data) {
        super(R.layout.item_gank_io_welfare, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoWelfareItem item) {
        ImageLoader.with(mContext)
                .load(item.getUrl())
                .placeholder(R.mipmap.img_default_meizi)
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
