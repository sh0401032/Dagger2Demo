package com.huan.dagger2demo.gankio;

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
import com.huan.dagger2demo.base.BaseFragment;
import com.huan.dagger2demo.home.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

import static android.support.design.widget.TabLayout.*;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoFragment extends BaseFragment implements GankIoContract.IGankIoView {

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
    GankIoContract.IGankIoPresenter mGankPresenter;
    private ArrayList<Fragment> m;
    private FragmentAdapter fragmentAdapter;

    public static GankIoFragment newInstance() {
        Bundle arg = new Bundle();
        GankIoFragment gankFragment = new GankIoFragment();
        gankFragment.setArguments(arg);
        return gankFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGankPresenter.attachView(this);
        initView();
        initData();
    }

    private void initView() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                Timber.d("当前tab is " + tab.getText() + "；位置 " + tab.getPosition());

            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }

    private void initData() {
        mGankPresenter.getCategoryList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mGankPresenter.detachView();
    }

    @Override
    public void showCategoryList(List<String> categoryList) {
        // 显示所有分类
        for (int i = 0; i < categoryList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(categoryList.get(i)));
            if ("每日推荐".equals(categoryList.get(i))) {
                fragments.add(GankIoDayFragment.newInstance(categoryList.get(i)));
            } else {
                fragments.add(GankIoCategoryFragment.newInstance(categoryList.get(i)));
            }
        }
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdapter);
        if (fragmentAdapter.getCount() > 0)
            viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        // setupWithViewPager会移除所有的tab，需要重新设置
        for (int i = 0; i < categoryList.size(); i++) {
            tabLayout.getTabAt(i).setText(categoryList.get(i));
        }
    }
}
