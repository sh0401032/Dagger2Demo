package com.huan.dagger2demo.wanandroid.knowledge;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huan.dagger2demo.R;
import com.huan.dagger2demo.utils.multitype.ItemViewBinder;

/**
 * Created by H_S on 2018/7/22.
 */

public class KnowledgeViewBinder extends ItemViewBinder<String, KnowledgeViewBinder.KnowledgeViewHolder> {


    @Override
    protected KnowledgeViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new KnowledgeViewHolder(inflater.inflate(R.layout.wan_android_knowledge_item_view, parent, false));
    }

    @Override
    protected void onBinderViewHolder(@NonNull KnowledgeViewHolder holder, @NonNull String item) {
        holder.tv.setText(item);
    }

    class KnowledgeViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
