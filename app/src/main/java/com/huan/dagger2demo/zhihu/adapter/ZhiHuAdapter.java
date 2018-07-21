package com.huan.dagger2demo.zhihu.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huan.dagger2demo.R;
import com.huan.common.sdk.api.zhihu.bean.ZhiHuDailyItem;

import java.util.List;

/**
 * Created by H_S on 2018/1/21.
 */

public class ZhiHuAdapter extends BaseQuickAdapter<ZhiHuDailyItem, BaseViewHolder> {


    public ZhiHuAdapter(int layoutResId, @Nullable List<ZhiHuDailyItem> data) {
        super(layoutResId, data);
    }

    public ZhiHuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiHuDailyItem item) {

        helper.setTextColor(R.id.tv_item_title, Color.BLACK);
        helper.setText(R.id.tv_item_title, item.getTitle());
        String[] images = item.getImages();
        if (images != null && images.length > 0) {
            Glide.with(mContext).load(item.getImages()[0]).crossFade().into((ImageView) helper.getView(R.id.iv_item_image));
        }

    }
}
