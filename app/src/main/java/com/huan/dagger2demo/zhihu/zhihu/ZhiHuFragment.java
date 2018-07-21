package com.huan.dagger2demo.zhihu.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseViewPagerDaggerFragment;
import com.huan.dagger2demo.zhihu.adapter.ZhiHuAdapter;
import com.huan.common.sdk.api.zhihu.bean.ZhiHuDailyItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/21.
 */

public class ZhiHuFragment extends BaseViewPagerDaggerFragment implements ZhiHuFragmentContract.IZhiHuView, BaseQuickAdapter.RequestLoadMoreListener {

    private final static String TAG = "ZhiHuFragment";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    ZhiHuFragmentContract.IZhiHuPresenter zhiHuPresenter;
    private ZhiHuAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zhihu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
        if (isVisible) {
            initData();
        } else {

        }
    }

    private void initView() {
        zhiHuPresenter.attachView(this);
        adapter = new ZhiHuAdapter(R.layout.item_recycle_home);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    private void initData() {
        if (adapter == null || adapter.getData() == null) {
            Log.d(TAG, "第一次获取数据");
            zhiHuPresenter.getLastDailyList();
        }
    }

    @Override
    public void updateDailyList(List<ZhiHuDailyItem> zhiHuDailyList) {

        if (adapter == null) return;
        if (adapter.getData().size() == 0) {
            adapter = new ZhiHuAdapter(R.layout.item_recycle_home, zhiHuDailyList);
            adapter.setOnLoadMoreListener(this, recyclerView);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Timber.d("onClick : " + position);
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            Timber.d("加载更多数据 ：" + zhiHuDailyList.size());
            adapter.loadMoreComplete();
            adapter.addData(zhiHuDailyList);
        }
    }

    @Override
    public void showZhiHuDailyDetail() {

    }

    @Override
    public void onLoadMoreRequested() {
        zhiHuPresenter.getMoreDaily();
    }
}
