package com.huan.dagger2demo.wanandroid.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.dagger2demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H_S on 2018/3/30.
 */

public class HomeBannerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Banner> bannerList;

    public HomeBannerAdapter(Context context, List<Banner> list) {
        mContext = context;
        bannerList = new ArrayList<>(list.size() + 2);
        bannerList.add(0, list.get(list.size() - 1));
        bannerList.addAll(list);
        bannerList.add(list.get(0));
    }


    @Override
    public int getCount() {
        return bannerList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.wan_android_home_banner_item, null);
        ImageView ivBanner = view.findViewById(R.id.iv_banner);
        TextView tvDesc = view.findViewById(R.id.tv_banner);
        tvDesc.setText(bannerList.get(position).getDesc());
        Glide.with(mContext).load(bannerList.get(position).getImagePath()).into(ivBanner);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
    }
}
