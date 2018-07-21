package com.huan.dagger2demo.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseDaggerFragment;

/**
 * Created by H_S on 2018/1/21.
 */

public class TestFragment extends BaseDaggerFragment {
    public final static int ZHIHUID = 1;
    public final static int WEIXIN = 2;
    public final static int REDIAN = 3;
    public final static int PICTURE = 4;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }
}
