package com.huan.dagger2demo.wanandroid.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.common.sdk.service.AndroidService;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseFragment;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by H_S on 2018/3/25.
 */

public class WanAndroidHomeFragment extends BaseFragment {

    private final static String TAG = "WanAndroidHomeFragment";

    private ViewPager vpHomeBanner;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvActicle;
    private Context mContext;
    private HomeBannerAdapter bannerAdapter;


    public static WanAndroidHomeFragment newInstance() {
        WanAndroidHomeFragment fragment = new WanAndroidHomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wan_android_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        initView(view);
        initData();

    }

    private void initView(View view) {
        vpHomeBanner = (ViewPager) view.findViewById(R.id.vp_home_banner);
        rvActicle = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvActicle.setLayoutManager(layoutManager);
    }

    private void initData() {
        AndroidService.getInstance().getBannerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Banner> banners) {
                        if (banners == null || banners.size() == 0) {
                            return;
                        }
                        if (bannerAdapter == null) {
                            bannerAdapter = new HomeBannerAdapter(mContext, banners);
                            vpHomeBanner.setAdapter(bannerAdapter);
                            vpHomeBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                boolean needChange;
                                int changeIndex;

                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                    // 内容大于 1 ,才做轮播处理
                                    if (bannerAdapter == null || bannerAdapter.getCount() <= 1) {
                                        needChange = false;
                                        return;
                                    }
                                    if (position == 0) {// 0 -> 倒数第二个
                                        needChange = true;
                                        changeIndex = bannerAdapter.getCount() - 2;
                                    } else if (position == bannerAdapter.getCount() - 1) { // 最后 -> 下标1
                                        needChange = true;
                                        changeIndex = 1;
                                    } else {
                                        needChange = false;
                                    }
                                }

                                @Override
                                public void onPageSelected(int position) {
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {
                                    if (ViewPager.SCROLL_STATE_IDLE == state && needChange) {
                                        needChange = false;
                                        // 切换时不显示动画
                                        //setCurrentItem(changeIndex, false);
                                        vpHomeBanner.setCurrentItem(changeIndex, false);
                                    }
                                }
                            });
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "获取HomeBanner失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
