package com.huan.dagger2demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by H_S on 2018/1/21.
 */

public class BaseViewPagerFragment extends BaseFragment {
    protected View rootView;
    //当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
    private boolean isFragmentVisible;
    //是否是第一次开启网络加载
    public boolean isViewCreated;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        isViewCreated = true;
        if (isViewCreated && isFragmentVisible) {// 针对第一次加载时，setUserVisibleHint在onViewCreated之前调用
            onFragmentVisible(true);
        }
    }


    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作.
     *
     * @param isVisible true  不可见 -> 可见h
     *                  false 可见  -> 不可见
     */
    public void onFragmentVisible(boolean isVisible) {
        Log.d(getClass().getName(), "onFragmentVisible = " + isVisible);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;
        if (rootView == null || !isViewCreated) {
            return;
        }
        onFragmentVisible(isFragmentVisible);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }
}
