package com.huan.dagger2demo.utils.multitype;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by huanshao on 2018/7/4.
 */

public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> {

    MultiTypeAdapter adapter;
    public Context mContext;

    protected abstract VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);


    protected abstract void onBinderViewHolder(@NonNull VH holder, @NonNull T item);

    protected final int getPosition(@NonNull final RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition();
    }

    protected final MultiTypeAdapter getAdapter() {
        if (adapter == null) {
            throw new IllegalStateException("ItemViewBinder " + this + " not attached to MultiTypeAdapter. " +
                    "You should not call the method before registering the binder.");
        }
        return adapter;
    }

}
