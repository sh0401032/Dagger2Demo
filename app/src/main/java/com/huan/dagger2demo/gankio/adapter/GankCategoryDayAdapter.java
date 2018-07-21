package com.huan.dagger2demo.gankio.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huan.common.sdk.api.gankio.bean.GankIoDayItem;
import com.huan.dagger2demo.R;

import java.util.List;

/**
 * Created by H_S on 2018/1/26.
 */

public class GankCategoryDayAdapter extends BaseQuickAdapter<List<GankIoDayItem>, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GankCategoryDayAdapter(List<List<GankIoDayItem>> data) {
        super(R.layout.sub_item_gank_io_day_content, data);

    }


    @Override
    protected void convert(BaseViewHolder helper, List<GankIoDayItem> itemList) {

        GankIoDayItem item = itemList.get(0);
        helper.setText(R.id.tv_type_item_title, item.getType());
        helper.addOnClickListener(R.id.ll_more);

        switch (item.getType()) {
            case "福利":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_welfare);
                // Glide.with(mContext).load(item.getUrl()).crossFade().into((ImageView) helper.getView(R.id.iv_item_title));
                break;
            case "Android":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_android);
                //   helper.setImageResource(R.id.iv_item_title, mResAndroid[0]);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_ios);
                //   helper.setImageResource(R.id.iv_item_title, mResIOS[0]);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_front);
                //   helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_web);
                break;
            case "休息视频":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_video);
                //    helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
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

        View view = helper.getView(R.id.recycler_view);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(new GankDayItemAdapter(itemList));

            // recyclerView.setOnFlingListener(null);// SnapHelper实现了RecycleView.FlingListener接口,下拉刷新后重新创建该接口会报错
            RecyclerView.OnFlingListener onFlingListener = recyclerView.getOnFlingListener();
            if (onFlingListener == null) {
                new LinearSnapHelper().attachToRecyclerView(recyclerView);// 实现类似ViewPager效果

            }
        }

    }
}

