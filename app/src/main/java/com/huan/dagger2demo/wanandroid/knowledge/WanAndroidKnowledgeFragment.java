package com.huan.dagger2demo.wanandroid.knowledge;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ReplacementSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huan.dagger2demo.R;
import com.huan.dagger2demo.base.BaseFragment;
import com.huan.dagger2demo.utils.multitype.MultiTypeAdapter;
import com.huan.dagger2demo.wanandroid.widget.TagSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H_S on 2018/3/25.
 */

public class WanAndroidKnowledgeFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private Context mContext;
    private MultiTypeAdapter mAdapter;

    public static WanAndroidKnowledgeFragment newInstance() {
        WanAndroidKnowledgeFragment fragment = new WanAndroidKnowledgeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wan_android_knowledge_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        if (mAdapter == null) {
            mAdapter = new MultiTypeAdapter(getTest());
            mAdapter.register(String.class, new KnowledgeViewBinder());
            recyclerView.setAdapter(mAdapter);
        }
    }

    private List<String> getTest() {
        ArrayList<String> strings = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            List<ReplacementSpan> spans = new ArrayList<>();
            String content = "Android是一种基于Linux的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑，由Google公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。";
            StringBuilder stringBuilder = new StringBuilder();
            //第一个Span
            stringBuilder.append(" ");
            TagSpan topSpan = new TagSpan(getActivity(), R.color.colorPrimary, "置顶");
            spans.add(topSpan);
            //第二个Span
            stringBuilder.append(" ");
            TagSpan hotSpan = new TagSpan(getActivity(), R.color.colorAccent, "热门");
            hotSpan.setRightMarginDpValue(10);
            spans.add(hotSpan);

            stringBuilder.append(content);
            SpannableString spannableString = new SpannableString(stringBuilder.toString());
            //循环遍历设置Span
            for (int i = 0; i < spans.size(); i++) {
                spannableString.setSpan(spans.get(i), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            strings.add(stringBuilder.toString());
        }
        return strings;

    }
}
