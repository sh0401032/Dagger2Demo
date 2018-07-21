package com.huan.dagger2demo.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.huan.common.sdk.api.gankio.bean.GankIoCustomItem;
import com.huan.common.sdk.api.gankio.bean.GankIoWelfareItem;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseViewPagerDaggerFragment;
import com.huan.dagger2demo.gankio.adapter.GankCategoryCustomAdapter;
import com.huan.dagger2demo.gankio.adapter.GankCategoryWelAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoCategoryFragment extends BaseViewPagerDaggerFragment implements GankCategoryContract.IGankCategoryView, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    public final static String CATEGORY = "category";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.fl_error)
    FrameLayout flError;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String category;

    @Inject
    GankCategoryContract.IGankCategoryPresenter gankCategoryPresenter;
    private GankCategoryCustomAdapter customAdapter;
    private GankCategoryWelAdapter welAdapter;
    private boolean hasInit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gankio_category, container, false);
    }

    public static GankIoCategoryFragment newInstance(String category) {

        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        GankIoCategoryFragment fragment = new GankIoCategoryFragment();
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
        gankCategoryPresenter.attachView(this);
        flError.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        gankCategoryPresenter.detachView();
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

    private int pre_page = 10;
    private int wel_page = 1;
    private int custom_page = 1;

    private void initData() {
        hasInit = true;
        if ("福利".equals(category)) {
            if (welAdapter == null || welAdapter.getData() == null)
                gankCategoryPresenter.getGankIoWelfareList(pre_page, wel_page);
        } else {
            if (customAdapter == null || customAdapter.getData() == null) {
                Timber.d("首次加载数据 + category is " + category);
                gankCategoryPresenter.getGankIoCustomList(category, pre_page, custom_page);
            }
        }
    }

    @Override
    public void showGankIoWelfareList(List<GankIoWelfareItem> gankIoWelfareList) {
        if ("福利".equals(category)) {
            wel_page++;
            flError.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);

            if (welAdapter == null || welAdapter.getData() == null) {
                Timber.d("福利->首次加载数据 ： " + gankIoWelfareList.size());
                welAdapter = new GankCategoryWelAdapter(gankIoWelfareList);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                welAdapter.setOnLoadMoreListener(this, recyclerView);
                recyclerView.setAdapter(welAdapter);
            } else {
                Timber.d("福利->加载更多数据 ： " + gankIoWelfareList.size());
                if (isRefresh) {
                    welAdapter.addData(0, gankIoWelfareList);
                    isRefresh = false;
                } else {
                    welAdapter.addData(gankIoWelfareList);
                }
                welAdapter.loadMoreComplete();
            }

        }

    }

    @Override
    public void showGankIoCustomList(List<GankIoCustomItem> gankIoCustomList) {
        if ("每日推荐".equals(category) || "福利".equals(category))
            return;
        custom_page++;
        flError.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        if (customAdapter == null || customAdapter.getData() == null) {
            Timber.d(category + "->首次加载数据: " + gankIoCustomList.size());
            customAdapter = new GankCategoryCustomAdapter(gankIoCustomList);
            customAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Timber.d("点击:" + position);

                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            customAdapter.setOnLoadMoreListener(this, recyclerView);
            recyclerView.setAdapter(customAdapter);
        } else {
            Timber.d(category + "-> 加载更多数据：" + gankIoCustomList.size());
            if (isRefresh) {
                customAdapter.addData(0, gankIoCustomList);
                isRefresh = false;
            } else {
                customAdapter.addData(gankIoCustomList);
            }

            customAdapter.loadMoreComplete();

        }


    }

    @Override
    public void onLoadMoreRequested() {
        if ("福利".equals(category)) {
            gankCategoryPresenter.getGankIoWelfareList(pre_page, wel_page);
        } else {
            gankCategoryPresenter.getGankIoCustomList(category, pre_page, custom_page);
        }
    }

    private boolean isRefresh = false;

    @Override
    public void onRefresh() {
        isRefresh = true;
        onLoadMoreRequested();
    }
}
