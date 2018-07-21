package com.huan.dagger2demo.wanandroid.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huan.common.sdk.api.wanandroid.bean.Article;
import com.huan.dagger2demo.R;
import com.huan.dagger2demo.utils.multitype.ItemViewBinder;

/**
 * Created by H_S on 2018/7/21.
 */

public class ArticleViewBinder extends ItemViewBinder<Article, ArticleViewBinder.ArticleViewHolder> {

    @Override
    protected ArticleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.wan_android_article_list_item, parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    protected void onBinderViewHolder(@NonNull ArticleViewHolder holder, @NonNull Article item) {
        holder.tvAuthor.setText(item.getAuthor());
        holder.tvTitle.setText(item.getTitle());
        holder.tvType.setText(item.getType() + "");
        holder.tvDate.setText(item.getNiceDate());
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivHead;
        private final TextView tvAuthor;
        private final TextView tvDate;
        private final TextView tvTitle;
        private final TextView tvType;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.homeItemHead);
            tvAuthor = itemView.findViewById(R.id.homeItemAuthor);
            tvDate = itemView.findViewById(R.id.homeItemDate);
            tvTitle = itemView.findViewById(R.id.homeItemTitle);
            tvType = itemView.findViewById(R.id.homeItemType);

        }
    }
}
