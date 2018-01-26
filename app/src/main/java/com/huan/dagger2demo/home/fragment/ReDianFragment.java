package com.huan.dagger2demo.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseViewPagerFragment;

/**
 * Created by H_S on 2018/1/21.
 */

public class ReDianFragment extends BaseViewPagerFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_redian, container, false);
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
        if (isVisible) {
            //initData();
        } else {

        }
    }
}
