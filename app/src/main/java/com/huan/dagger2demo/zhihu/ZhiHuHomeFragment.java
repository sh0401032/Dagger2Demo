package com.huan.dagger2demo.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huan.common.sdk.api.base.bean.HomeTab;
import com.huan.dagger2demo.OnOpenDrawerLayoutListener;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseDaggerFragment;
import com.huan.dagger2demo.zhihu.adapter.FragmentAdapter;
import com.huan.dagger2demo.zhihu.fragment.ReDianFragment;
import com.huan.dagger2demo.zhihu.fragment.TestFragment;
import com.huan.dagger2demo.zhihu.fragment.WeiXinFragment;
import com.huan.dagger2demo.zhihu.zhihu.ZhiHuFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by H_S on 2018/1/20.
 */

public class ZhiHuHomeFragment extends BaseDaggerFragment implements HomeContract.IHomeView {

    private final static String TAG = "ZhiHuHomeFragment";

    private List<Fragment> fragments = new ArrayList<>();

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_home)
    ViewPager viewPager;

    @Inject
    HomeContract.IHomePresenter homePresenter;

    OnOpenDrawerLayoutListener onOpenDrawerLayoutListener;

    public static ZhiHuHomeFragment newInstance() {
        Bundle arg = new Bundle();
        ZhiHuHomeFragment zhiHuHomeFragment = new ZhiHuHomeFragment();
        zhiHuHomeFragment.setArguments(arg);
        return zhiHuHomeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        homePresenter.detachView();
    }

    private void initView() {
        homePresenter.attachView(this);
        if (getActivity() instanceof OnOpenDrawerLayoutListener) {
            onOpenDrawerLayoutListener = (OnOpenDrawerLayoutListener) getActivity();
        }

        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onOpenDrawerLayoutListener != null) {
                    onOpenDrawerLayoutListener.openDrawer();
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabSelectedListener());

    }

    private void initData() {
        homePresenter.getTabList();
    }

    @Override
    public void showTabList(List<HomeTab> tabList) {
        Log.d(TAG, tabList.toString());
        for (int i = 0; i < tabList.size(); i++) {
            HomeTab homeTab = tabList.get(i);
            tabLayout.addTab(tabLayout.newTab().setText(homeTab.name));
            switch (homeTab.id) {
                case TestFragment.ZHIHUID:
                    fragments.add(new ZhiHuFragment());
                    break;
                case TestFragment.WEIXIN:
                    fragments.add(new WeiXinFragment());
                    break;
                case TestFragment.REDIAN:
                    fragments.add(new ReDianFragment());
                    break;
            }
        }
        viewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager); //　关联ViewPager
        for (int i = 0; i < tabList.size(); i++) {
            tabLayout.getTabAt(i).setText(tabList.get(i).name);
        }
    }


    private class TabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            // 显示
            Log.d(TAG, "tab is " + tab.getText());

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}
