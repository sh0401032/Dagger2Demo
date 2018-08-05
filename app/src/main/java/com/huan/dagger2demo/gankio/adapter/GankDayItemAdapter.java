package com.huan.dagger2demo.gankio.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huan.common.sdk.api.gankio.bean.GankIoDayItem;
import com.huan.common.sdk.library.imageloader.ImageLoader;
import com.huan.dagger2demo.R;

import java.util.List;

/**
 * Created by H_S on 2018/1/27.
 */

public class GankDayItemAdapter extends BaseQuickAdapter<GankIoDayItem, BaseViewHolder> {

    //GankIo没有返回ImageList，这里使用固定的图片资源模拟刷新item效果
    private int[] mResAndroid = {
            R.mipmap.gank_io_day_item_android1,
            R.mipmap.gank_io_day_item_android2,
            R.mipmap.gank_io_day_item_android3,
            R.mipmap.gank_io_day_item_android4,
            R.mipmap.gank_io_day_item_android5,
            R.mipmap.gank_io_day_item_android6};

    private int[] mResIOS = {
            R.mipmap.gank_io_day_item_ios1,
            R.mipmap.gank_io_day_item_ios2,
            R.mipmap.gank_io_day_item_ios3};

    public GankDayItemAdapter(@Nullable List<GankIoDayItem> data) {
        super(R.layout.item_banner, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoDayItem item) {
        helper.setText(R.id.tv_item_title, item.getDesc());
        switch (item.getType()) {
            case "福利":
                ImageLoader.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.iv_item_title));
                break;
            case "Android":
                helper.setImageResource(R.id.iv_item_title, mResAndroid[0]);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_item_title, mResIOS[0]);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_web);
                break;
            case "休息视频":
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
                break;
            case "瞎推荐":
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
                break;
            case "拓展资源":
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
                break;
            case "App":
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
                break;
        }
    }
}
