package com.huan.dagger2demo.base;

import android.content.Context;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by H_S on 2018/1/17.
 */

public class BaseDaggerFragment extends BaseFragment {

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

}
