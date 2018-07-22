package com.huan.dagger2demo.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.huan.common.sdk.api.wanandroid.bean.Banner;
import com.huan.common.sdk.service.AndroidService;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseDaggerFragment;
import com.huan.dagger2demo.wanandroid.home.HomeBannerAdapter;
import com.huan.dagger2demo.wanandroid.home.WanAndroidHomeFragment;
import com.huan.dagger2demo.wanandroid.knowledge.WanAndroidKnowledgeFragment;
import com.huan.dagger2demo.wanandroid.widget.AutoHeightViewPager;
import com.huan.dagger2demo.zhihu.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by H_S on 2018/7/21.
 */

public class WanAndroidFragment extends BaseDaggerFragment {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabTitle = new ArrayList<>();

    @BindView(R.id.vp_home_banner)
    ViewPager vpHomeBanner;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_home)
    ViewPager viewPager;

    private HomeBannerAdapter bannerAdapter;

    private FragmentAdapter fragmentAdapter;
    private FragmentActivity mContext;

    public static WanAndroidFragment newInstance() {
        WanAndroidFragment fragment = new WanAndroidFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        if (args != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wan_android_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        initView();
        getHomeBannerList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Timber.d("当前tab is " + tab.getText() + "；位置 " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabTitle.add("首页");
        tabTitle.add("分类");
        for (int i = 0; i < tabTitle.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTitle.get(i)));
        }
        fragments.add(WanAndroidHomeFragment.newInstance());
        fragments.add(WanAndroidKnowledgeFragment.newInstance());
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdapter);
        if (fragmentAdapter.getCount() > 0)
            viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabTitle.size(); i++) {
            tabLayout.getTabAt(i).setText(tabTitle.get(i));
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("huan", "selected " + position + " llcontainer height " + vpHomeBanner.getHeight() + " view pager " + viewPager.getHeight());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                Log.d("huan", "llcontainer height " + vpHomeBanner.getHeight() + " view pager " + viewPager.getHeight());
                int matchSize = ((AutoHeightViewPager) viewPager).getMatchSize();
                ((AutoHeightViewPager) viewPager).resetHeight(matchSize + vpHomeBanner.getHeight());
            }
        });
    }

    public void getHomeBannerList() {

        AndroidService.getInstance().getBannerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Banner> banners) {
                        showHomeBanner(banners);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void showHomeBanner(List<Banner> banners) {
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

}
