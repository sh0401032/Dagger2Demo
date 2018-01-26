package com.huan.dagger2demo.gankio.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huan.common.sdk.api.bean.GankIoCustomItem;
import com.huan.dagger2demo.R;

import java.util.List;

import timber.log.Timber;

/**
 * Created by H_S on 2018/1/26.
 */

public class GankCategoryCustomAdapter extends BaseMultiItemQuickAdapter<GankIoCustomItem, BaseViewHolder> {

    private String mImageSize = "?imageView2/0/w/200";

    public GankCategoryCustomAdapter(@Nullable List<GankIoCustomItem> data) {
        super(data);
        Timber.d("data : " + data.size());

        addItemType(GankIoCustomItem.GANK_IO_DAY_ITEM_CUSTOM_NORMAL, R.layout
                .item_gank_io_custom_normal);
        addItemType(GankIoCustomItem.GANK_IO_DAY_ITEM_CUSTOM_IMAGE, R.layout
                .item_gank_io_custom_image);
        addItemType(GankIoCustomItem.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE, R.layout
                .item_gank_io_custom_no_image);

    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoCustomItem item) {
        initTypeImage(helper, item);

        helper.setText(R.id.tv_item_who, TextUtils.isEmpty(item.getWho()) ? "佚名" : item
                .getWho());
        helper.setText(R.id.tv_item_type, item.getType());
        helper.setText(R.id.tv_item_time, item.getCreatedAt().substring(0, 10));
        switch (helper.getItemViewType()) {
            case GankIoCustomItem.GANK_IO_DAY_ITEM_CUSTOM_NORMAL:
                helper.setText(R.id.tv_item_title, item.getDesc());
                if (item.getImages() != null) {
                    if (item.getImages().size() > 0 && !TextUtils.isEmpty(item.getImages().get(0)))
                        Glide.with(mContext).load(item.getImages().get(0) + mImageSize)
                                .asBitmap()
                                .into((ImageView) helper.getView(R.id.iv_item_image));
                }
                break;
            case GankIoCustomItem.GANK_IO_DAY_ITEM_CUSTOM_IMAGE:
                Glide.with(mContext)
                        .load(item.getUrl())
                        .asBitmap()
                        .centerCrop()
                        .placeholder(R.mipmap.img_default_meizi)
                        .into((ImageView) helper.getView(R.id.iv_item_image));
                break;
            case GankIoCustomItem.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE:
                helper.setText(R.id.tv_item_title, item.getDesc());
                break;
            default:
                break;
        }
    }


    private void initTypeImage(BaseViewHolder helper, GankIoCustomItem item) {
        switch (item.getType()) {
            case "福利":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_welfare);
                break;
            case "Android":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_android);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_ios);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_front);
                break;
            case "休息视频":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_video);
                break;
            case "瞎推荐":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuijian);
                break;
            case "拓展资源":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuozhan);
                break;
            case "App":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_app);
                break;
        }
    }
}
