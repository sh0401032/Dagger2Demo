package com.huan.dagger2demo.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huan.common.sdk.api.gankio.bean.GankIoDayItem;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseViewPagerDaggerFragment;
import com.huan.dagger2demo.gankio.adapter.GankCategoryDayAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoDayFragment extends BaseViewPagerDaggerFragment implements GankIoDayContract.IGankIoDayView, SwipeRefreshLayout.OnRefreshListener {

    public final static String CATEGORY = "category";

    private String mYear = "2017";
    private String mMonth = "07";
    private String mDay = "25";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.fl_error)
    FrameLayout flError;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String category;

    @Inject
    GankIoDayContract.IGankIoDayPresenter gankDayPresenter;

    private GankCategoryDayAdapter dayAdapter;
    private boolean hasInit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_category, container, false);
    }

    public static GankIoDayFragment newInstance(String category) {

        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        GankIoDayFragment fragment = new GankIoDayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            category = arguments.getString(CATEGORY);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gankDayPresenter.attachView(this);
        flError.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        gankDayPresenter.detachView();
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
        if (isVisible) {
            if (hasInit) return;
            initData();
        } else {

        }

    }

    private void initData() {
        hasInit = false;
        if (dayAdapter == null || dayAdapter.getData() == null)
            gankDayPresenter.getGankIoDay(mYear, mMonth, mDay);

    }

    @Override
    public void showGankIoDay(List<List<GankIoDayItem>> gankIoDayList) {

        flError.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        if (dayAdapter == null || dayAdapter.getData() == null) {
            Timber.d("每日推荐->首次获取数据 ：" + gankIoDayList.size());
            dayAdapter = new GankCategoryDayAdapter(gankIoDayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            // dayAdapter.setOnLoadMoreListener(this, recyclerView);
            dayAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Timber.d("点击更多：" + position);
                    // 跳转至相应fragment


                }
            });
            recyclerView.setAdapter(dayAdapter);
        } else {
            Timber.d("每日推荐->更新数据 ：" + gankIoDayList.size());
            dayAdapter.setNewData(gankIoDayList);
            dayAdapter.loadMoreComplete();
        }


    }


    @Override
    public void onRefresh() {
        gankDayPresenter.getGankIoDay(mYear, mMonth, mDay);
    }
}
