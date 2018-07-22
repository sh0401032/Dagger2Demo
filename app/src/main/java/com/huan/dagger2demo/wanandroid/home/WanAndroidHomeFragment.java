package com.huan.dagger2demo.wanandroid.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huan.common.sdk.api.wanandroid.bean.Article;
import com.huan.common.sdk.api.wanandroid.bean.Pager;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseViewPagerDaggerFragment;
import com.huan.dagger2demo.utils.multitype.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by H_S on 2018/3/25.
 */

public class WanAndroidHomeFragment extends BaseViewPagerDaggerFragment implements WanAndroidHomeContract.IAndroidHomeView {

    private final static String TAG = "WanAndroidHomeFragment";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvArticle;
    private Context mContext;

    @Inject
    public WanAndroidHomeContract.IAndroidHomePresenter androidHomePresenter;
    private boolean hasInit;
    private MultiTypeAdapter mAdapter;


    public static WanAndroidHomeFragment newInstance() {
        WanAndroidHomeFragment fragment = new WanAndroidHomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wan_android_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        if (androidHomePresenter != null) {
            androidHomePresenter.attachView(this);
        }
        initView(view);
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        if (isVisible && !hasInit) {
            initData();
        }
    }

    @Override
    public void onDestroyView() {
        if (androidHomePresenter != null) {
            androidHomePresenter.detachView();
        }
        super.onDestroyView();
    }

    private void initView(View view) {
        rvArticle = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvArticle.setLayoutManager(layoutManager);
    }

    private void initData() {
        hasInit = true;
        if (androidHomePresenter != null) {
            androidHomePresenter.getHomeList(0);
        }
    }

    @Override
    public void showHomePagerList(Pager pager) {
        if (pager != null && pager.getDatas() != null) {
            if (mAdapter == null) {
                mAdapter = new MultiTypeAdapter(getTest());
                mAdapter.register(Article.class, new ArticleViewBinder());
                rvArticle.setAdapter(mAdapter);
            } else {
                mAdapter.setItems(pager.getDatas());
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private List<Article> getTest(){
        List<Article> test = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Article article = new Article();
            article.setAuthor("作者");
            article.setTitle("标题内容" + i);
            article.setNiceDate("昨天");
            test.add(article);
        }
        return test;
    }
}
