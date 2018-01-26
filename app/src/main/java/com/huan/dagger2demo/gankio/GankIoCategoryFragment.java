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
import com.huan.common.sdk.api.bean.GankIoCustomItem;
import com.huan.common.sdk.api.bean.GankIoDayItem;
import com.huan.common.sdk.api.bean.GankIoWelfareItem;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseViewPagerFragment;
import com.huan.dagger2demo.gankio.adapter.GankCategoryCustomAdapter;
import com.huan.dagger2demo.gankio.adapter.GankCategoryDayAdapter;
import com.huan.dagger2demo.gankio.adapter.GankCategoryWelAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by H_S on 2018/1/22.
 */

public class GankIoCategoryFragment extends BaseViewPagerFragment implements GankCategoryContract.IGankCategoryView {

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
    GankCategoryContract.IGankCategoryPresenter gankCategoryPresenter;
    private GankCategoryCustomAdapter customAdapter;
    private GankCategoryWelAdapter welAdapter;


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
            initData();
        } else {

        }

    }

    private void initData() {
        if ("每日推荐".equals(category)) {
            /*Calendar calendar = Calendar.getInstance();

            mYear = Integer.toString(calendar.get(Calendar.YEAR));
            int month = calendar.get(Calendar.MONTH) + 1;
            if (month < 10) {
                mMonth = "0" + month;
            } else {
                mMonth = Integer.toString(month);
            }
            mDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            Timber.d("year " + mYear + " month " + mMonth + " day " + mDay);*/

            gankCategoryPresenter.getGankIoDay(mYear, mMonth, mDay);
        } else if ("福利".equals(category)) {
            if (welAdapter == null || welAdapter.getData() == null)
                gankCategoryPresenter.getGankIoWelfareList(10, 0);
        } else {
            if (customAdapter == null || customAdapter.getData() == null) {
                Timber.d("首次加载数据 + category is " + category);
                gankCategoryPresenter.getGankIoCustomList(category, 10, 0);
            }

        }
    }

    @Override
    public void showGankIoDay(List<GankIoDayItem> gankIoDayList) {
        if ("每日推荐".equals(category)) {
            Timber.d("每日推荐：" + gankIoDayList.size());
            GankCategoryDayAdapter dayAdapter = new GankCategoryDayAdapter(gankIoDayList);
        }
    }

    @Override
    public void showGankIoWelfareList(List<GankIoWelfareItem> gankIoWelfareList) {
        if ("福利".equals(category)) {
            Timber.d("福利： " + gankIoWelfareList.size());
            flError.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            welAdapter = new GankCategoryWelAdapter(gankIoWelfareList);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(welAdapter);
        }

    }

    @Override
    public void showGankIoCustomList(List<GankIoCustomItem> gankIoCustomList) {
        if ("每日推荐".equals(category) || "福利".equals(category))
            return;
        Timber.d(category + ": " + gankIoCustomList.size());
        flError.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        customAdapter = new GankCategoryCustomAdapter(gankIoCustomList);
        customAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Timber.d("点击:" + position);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(customAdapter);

    }
}
