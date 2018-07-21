package com.huan.dagger2demo.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseDaggerFragment;
import com.huan.dagger2demo.wanandroid.home.WanAndroidHomeFragment;
import com.huan.dagger2demo.wanandroid.knowledge.WanAndroidKnowledgeFragment;
import com.huan.dagger2demo.zhihu.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by H_S on 2018/7/21.
 */

public class WanAndroidFragment extends BaseDaggerFragment {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabTitle = new ArrayList<>();

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_home)
    ViewPager viewPager;

    private FragmentAdapter fragmentAdapter;

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
        initView();

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

    }

}
